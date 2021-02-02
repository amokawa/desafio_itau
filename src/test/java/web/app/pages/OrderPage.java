package web.app.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import web.support.RandomCustomer;

import java.util.*;

public class OrderPage extends GeneralPage {
    public static final String ORDER_PAGE_URL = "http://automationpractice.com/index.php?controller=order";
    @FindBy(id = "cart_summary")
    WebElement table;

    @FindBy(xpath = "//table[@id='cart_summary']/thead/tr/th")
    List<WebElement> tableHeaders;

    @FindBy(xpath = "//table[@id='cart_summary']/tfoot/tr")
    List<WebElement> tableFooterRows;

    @FindBy(xpath = "//table[@id='cart_summary']/tbody/tr")
    List<WebElement> tableBodyRows;

    @FindBy(xpath = "//a[@class='button-exclusive btn btn-default']")
    WebElement continueShoppingButton;

    @FindBy(xpath = "//a[@class='button btn btn-default standard-checkout button-medium']")
    WebElement proceedToCheckoutButton;

    @FindBy(id = "total_product")
    WebElement totalProduct;

    @FindBy(id = "total_shipping")
    WebElement totalShipping;

    @FindBy(id = "total_price_without_tax")
    WebElement totalPriceWithoutTax;

    @FindBy(id = "total_tax")
    WebElement totalTax;

    @FindBy(id = "total_price")
    WebElement totalPrice;

    public OrderPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        if (!driver.getCurrentUrl().contains(ORDER_PAGE_URL)) {
            driver.get(ORDER_PAGE_URL);
        }
    }

    public void updateOrder(String description, int qty) {
        for (Map<String, WebElement> tableContent : retrieveTableContents()) {
            if (tableContent.get("Description").getText().contains(description)) {
                String totalPriceText = totalPrice.getText();
                if (qty == 0) throw new IllegalArgumentException("The qty must be different than 0. qty: " + qty);
                WebElement qtyInput = tableContent.get("Qty").findElement(By.xpath(".//input[@type='text']"));
                qtyInput.clear();
                qtyInput.sendKeys(String.valueOf(qty) + Keys.TAB);
                wait.until(driver1 -> !totalPriceText.equals(totalPrice.getText()));
                actions.moveToElement(totalPrice).perform();
            }
        }
    }

    public List<Map<String, WebElement>> retrieveTableContents() {
        List<Map<String, WebElement>> tableContents = new ArrayList<>();
        for (WebElement tableRow : tableBodyRows) {
            Map<String, WebElement> rowContent = new HashMap<>();
            List<WebElement> tds = tableRow.findElements(By.xpath("./td"));
            for (int i = 0; i < tableHeaders.size(); i++) {
                String text = tableHeaders.get(i).getText();
                rowContent.put(text.equals(" ") ? "Last" : text, tds.get(i));
            }
            tableContents.add(rowContent);
        }
        return tableContents;
    }

    public void removeOrder(String description) {
        List<Map<String, WebElement>> tableContents = retrieveTableContents();
        for (Map<String, WebElement> tableContent : tableContents) {
            if (tableContent.get("Description").getText().contains(description)) {
                WebElement lastColumn = tableContent.get("Last");
                WebElement trashIcon = lastColumn.findElement(By.xpath(".//a[@class='cart_quantity_delete']"));
                trashIcon.click();
                // wait until the deleted product disappears
                wait.until(driver1 -> driver1.findElements(By.xpath("//a[text()='"+description+"']")).isEmpty());
            }
        }
    }

    public WebElement getTableFooterRow(String text) {
        switch (text) {
            case "Total":
                return totalPriceWithoutTax;
            case "Total products":
                return totalProduct;
            case "Total shipping":
                return totalShipping;
            case "Tax":
                return totalTax;
            case "TOTAL":
                return totalPrice;
            default:
                throw new IllegalArgumentException("Invalid footer field: " + text);
        }
    }

    public double getTotalSumOfCart() {
        double total = 0.00;
        for (Map<String, WebElement> tableContent : retrieveTableContents()) {
            total += Double.parseDouble(tableContent.get("Total").getText().replace("$", ""));
        }
        total += Double.parseDouble(totalShipping.getText().replace("$", ""));
        total += Double.parseDouble(totalTax.getText().replace("$", ""));
        return total;
    }

    public void checkout(String paymentType, RandomCustomer customer) {
        proceedToCheckoutButton.click();
        // sign in - is displayed if the session is in guest mode
        if (!driver.findElements(By.id("email")).isEmpty()) {
            // login with customer credentials
            WebElement email = driver.findElement(By.id("email"));
            WebElement passwd = driver.findElement(By.id("passwd"));
            email.clear();
            passwd.clear();
            email.sendKeys(customer.getEmail());
            passwd.sendKeys(customer.getEmail());
            // proceed to step 1
            driver.findElement(By.id("SubmitLogin")).click();
        }
        // step 1 - http://automationpractice.com/index.php?controller=order&step=1
        checkStepProgress("?controller=order&step=1", "At checkout step 1");
        // proceed to step 2
        driver.findElement(By.xpath("//button[@type='submit' and @name='processAddress']")).click();
        // step 2 - http://automationpractice.com/index.php?controller=order
        checkStepProgress("?controller=order", "At checkout step 2");
        // mark the agreement checkbox
        driver.findElement(By.id("cgv")).click();
        // proceed to step 3
        driver.findElement(By.xpath("//button[@type='submit' and @name='processCarrier']")).click();
        // step 3
        checkStepProgress("?controller=order&multi-shipping=", "At checkout step 3");
        WebElement bankWire = driver.findElement(By.xpath("//a[@class='bankwire']"));
        WebElement check = driver.findElement(By.xpath("//a[@class='cheque']"));
        if (paymentType.equalsIgnoreCase("cheque")) {
            // proceed to step confirmation
            check.click();
            paymentType = "cheque";
        } else {
            // proceed to step confirmation
            bankWire.click();
            paymentType = "bankwire";
        }
        // step confirmation - it does not matter which payment type is selected the elements are the same, only the
        // text displayed is different
        checkStepProgress(String.format("?fc=module&module=%s&controller=payment", paymentType),
                "After clicking on bank wire payment type");
        // confirm payment
        driver.findElement(By.xpath("//button[@class='button btn btn-default button-medium']")).click();
        // step order confirmation
        if (!driver.getCurrentUrl().startsWith("http://automationpractice.com/index.php?controller=order-confirmation&")) {
            throw new PaymentNotSucceededException("The order confirmation page was not displayed");
        }
    }

    private void checkStepProgress(String urlSuffix, String message) {
        if (!driver.getCurrentUrl().endsWith(urlSuffix)) {
            throw new CheckoutException(message);
        }
    }

    public WebElement getSuccessfulPaymentMessage() {
        return driver.findElement(By.xpath("//*[text()='Your order on My Store is complete.']"));
    }
}
