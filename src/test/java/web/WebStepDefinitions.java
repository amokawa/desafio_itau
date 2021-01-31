package web;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import web.app.MyApp;
import web.app.pages.HomePage;
import web.app.pages.MyPage;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WebStepDefinitions {
    private WebDriver driver;
    private MyApp app;
    private MyPage appPage;

    @Before("@web")
    public void setUp() {
        driver = new ChromeDriver();
        app = new MyApp(driver);
        appPage = null;
    }

    @After("@web")
    public void tearDown() {
        driver.close();
        driver.quit();
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
            homePage.getLayerCartContinueButton().click();
        }
    }

    @When("o item {int} é removido do carrinho")
    public void removeItemsFromShoppingCart(int index) {
        appPage.getHeader().removeItemFromCart(index);
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
        assertEquals("Cart " + expected, appPage.getHeader().getShoppingCart().getText());
    }
}
