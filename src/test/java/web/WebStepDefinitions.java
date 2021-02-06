package web;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import web.app.MyApp;
import web.app.pages.*;
import web.support.RandomCustomer;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class WebStepDefinitions {
    private WebDriver driver;
    private MyApp app;
    private MyPage appPage;
    private RandomCustomer customer;

    @Before("@web")
    public void setUp() {
        driver = new ChromeDriver();
        app = new MyApp(driver);
        appPage = null;
    }

    @After("@web")
    public void tearDown(Scenario scenario) {
        byte[] screenshotAs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        String imageName = String.join("_", scenario.getName().split("\\s+"));
        scenario.attach(screenshotAs, "image/png", imageName + "_" + scenario.getStatus());

        driver.close();
        driver.quit();
    }

    @Given("um cliente com dados gerados randomicamente")
    public void createRandomCustomer() {
        this.customer = new RandomCustomer();
        System.out.println(customer);
    }

    @Given("um cliente com credenciais {string} e {string}")
    public void createCustomer(String email, String password) {
        this.customer = new RandomCustomer(email, password);
        System.out.println(customer);
    }

    @When("o cadastro de usuário é enviado com todos os campos obrigatórios preenchidos com dados válidos")
    public void registerNewCustomer() {
        if (!(appPage instanceof SignInPage) || customer == null)
            fail("Please, check the objects appPage and customer");
        SignInPage signInPage = (SignInPage) this.appPage;
        SignOnPage signOnPage = signInPage.signOnWithEmail(customer.getEmail());
        appPage = signOnPage.registerCustomer(customer);
        if (!(appPage instanceof MyAccountPage)) fail("There is some invalid value in customer object: " + customer);
    }

    @When("a página {string} é carregada")
    public void accessPage(String page) {
        appPage = app.getPage(page);
    }

    @When("são adicionados alguns itens no carrinho")
    public void addItemsToShoppingCart(List<String> entries) {
        for (String entry : entries) {
            HomePage homePage = (HomePage) this.appPage;
            homePage.getOfferingItems()
                    .stream()
                    .filter(webElement -> webElement.getText().contains(entry))
                    .findAny()
                    .ifPresent(homePage::addItemToShoppingCart);
            homePage.clickOnContinueShopping();
        }
    }

    @When("o item {int} é removido do carrinho")
    public void removeItemsFromShoppingCart(int index) {
        appPage.getHeader().removeItemFromCart(index);
    }

    @When("as credenciais {string} e {string} são enviadas")
    public void sendCredentials(String email, String password) {
        appPage = ((SignInPage) appPage).signInWithCredentials(email, password);
    }

    @When("a quantidade do produto {string} é alterada para {int}")
    public void updateCartItemQuantity(String description, int quantity) {
        OrderPage orderPage = (appPage instanceof OrderPage) ? (OrderPage) appPage : new OrderPage(driver);
        appPage = orderPage;
        orderPage.updateOrder(description, quantity);
    }

    @When("o produto {string} é removido")
    public void removeCartItem(String description) {
        OrderPage orderPage = (appPage instanceof OrderPage) ? (OrderPage) appPage : new OrderPage(driver);
        appPage = orderPage;
        orderPage.removeOrder(description);
    }

    @When("a opção de pagamento via {string}")
    public void proceedInCheckoutFlow(String paymentType) {
        OrderPage orderPage = (appPage instanceof OrderPage) ? (OrderPage) appPage : new OrderPage(driver);
        appPage = orderPage;
        orderPage.checkout(paymentType, customer);
    }

    @Then("todos os elementos são carregados")
    public void assertPageElements() {
        List<WebElement> nonVisibleHeaderElements = appPage.getHeader()
                .getWebElementList(driver)
                .stream()
                .filter(webElement -> !webElement.isDisplayed())
                .collect(Collectors.toList());
        List<WebElement> nonVisiblePageElements = appPage.getWebElementList(driver)
                .stream()
                .filter(webElement -> !webElement.isDisplayed())
                .collect(Collectors.toList());
        List<WebElement> nonVisibleFooterElements = appPage.getFooter()
                .getWebElementList(driver)
                .stream()
                .filter(webElement -> !webElement.isDisplayed())
                .collect(Collectors.toList());
        assertTrue(nonVisibleHeaderElements.size() == 0
                && nonVisiblePageElements.size() == 0
                && nonVisibleFooterElements.size() == 0);
    }

    @Then("o carrinho deve ter {int} produto\\(s)")
    public void assertNumberOfProductsInShoppingCart(int expected) {
        assertTrue(appPage.getHeader().getShoppingCart().getText().contains("Cart " + expected));
    }

    @Then("o nome e sobrenome do cliente deve ser exibido no cabeçalho")
    public void assertFirstNameAndLastName() {
        String expected = customer.getFirstName() + " " + customer.getLastName();
        assertEquals(expected, appPage.getHeader().getAccount().getText());
    }

    @Then("na página de Resumo de compras o valor de {string} deve ser atualizado")
    public void assertOrderPageFooter(String footerText) {
        if (!(appPage instanceof OrderPage)) {
            String message = "Page object is not OrderPage: " + appPage.getClass().getSimpleName();
            fail(message);
        }
        OrderPage orderPage = (OrderPage) this.appPage;
        String actual = String.format(Locale.ENGLISH, "$%.02f", orderPage.getTotalSumOfCart());
        assertEquals(orderPage.getTableFooterRow(footerText).getText(), actual);
    }

    @Then("a página de confirmação de compra é exibida a mensagem de sucesso")
    public void assertSuccessAlertAtPaymentConfirmationPage() {
        if (!(appPage instanceof OrderPage)) {
            String message = "Page object is not OrderPage: " + appPage.getClass().getSimpleName();
            fail(message);
        }
        OrderPage orderPage = (OrderPage) this.appPage;
        assertTrue(orderPage.getSuccessfulPaymentMessage().isDisplayed());
    }
}
