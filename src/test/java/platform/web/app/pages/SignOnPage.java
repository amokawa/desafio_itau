package platform.web.app.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;
import platform.web.support.RandomCustomer;

public class SignOnPage extends GeneralPage {
    @FindBy(id = "customer_firstname")
    WebElement firstNameInput;

    @FindBy(id = "customer_lastname")
    WebElement lastNameInput;

    @FindBy(id = "email")
    WebElement emailInput;

    @FindBy(id = "passwd")
    WebElement passwordInput;

    @FindBy(id = "address1")
    WebElement address1Input;

    @FindBy(id = "city")
    WebElement cityInput;

    @FindBy(id = "id_state")
    WebElement stateSelect;

    @FindBy(id = "postcode")
    WebElement postcodeInput;

    @FindBy(id = "id_country")
    WebElement countrySelect;

    @FindBy(id = "phone_mobile")
    WebElement mobileInput;

    @FindBy(id = "alias")
    WebElement addressAliasInput;

    @FindBy(id = "submitAccount")
    WebElement submitAccountButton;

    public SignOnPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public MyPage registerCustomer(RandomCustomer customer) {
        wait.until(ExpectedConditions.visibilityOf(firstNameInput));
        fill(firstNameInput, customer.getFirstName());
        fill(lastNameInput, customer.getLastName());
        fill(emailInput, customer.getEmail());
        fill(passwordInput, customer.getPassword());
        fill(address1Input, customer.getAddress());
        fill(cityInput, customer.getCity());
        fill(stateSelect, customer.getState());
        fill(postcodeInput, customer.getPostalCode());
        fill(countrySelect, customer.getCountry());
        fill(mobileInput, customer.getMobilePhone());
        fill(addressAliasInput, customer.getAddressAlias());
        submitAccountButton.click();

        if (hasNoValidationErrors()) {
            return new MyAccountPage(driver);
        } else return this;
    }

    private boolean hasNoValidationErrors() {
        return driver.findElements(By.xpath("//div[@class='alert alert-danger']")).size() == 0;
    }

    public void fill(WebElement webElement, Object value) {
        switch (webElement.getTagName()) {
            case "input":
                webElement.clear();
                webElement.sendKeys((String) value);
                break;
            case "select":
                Select select = new Select(webElement);
                if (value instanceof String) {
                    select.selectByVisibleText((String) value);
                } else select.selectByValue(String.valueOf(value));
                break;
            default:
                throw new UnexpectedTagNameException("", webElement.getTagName());
        }
    }
}
