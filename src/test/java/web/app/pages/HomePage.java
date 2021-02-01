package web.app.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class HomePage extends GeneralPage {
    public static final String HOMEPAGE_URL = "http://automationpractice.com/index.php";

    @FindBy(id = "homepage-slider")
    WebElement homeSlider;

    @FindBy(xpath = "//div[@id='homepage-slider']//li[@class='homeslider-container']")
    WebElement homeSliderItems;

    @FindBy(id = "htmlcontent_top")
    WebElement otherOffPrices;

    @FindBy(id = "center_column")
    WebElement offerings;

    @FindBy(xpath = "//ul[@id='home-page-tabs']//li")
    List<WebElement> offeringTabs;

    @FindBy(xpath = "//ul[contains(@class,'active')]//li[contains(@class, 'ajax_block_product')]")
    List<WebElement> offeringItems;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        if (!driver.getCurrentUrl().equals(HOMEPAGE_URL))
            driver.get(HOMEPAGE_URL);
    }

    public List<WebElement> getOfferingItems() {
        return offeringItems;
    }

    public WebElement getLayerCartContinueButton() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("layer_cart"))));
        return driver.findElement(By.xpath("//div[@id='layer_cart']//span[contains(@class,'continue')]"));
    }

    public void addItemToShoppingCart(WebElement element) {
        WebElement addToCartLink = element.findElement(By.xpath(".//a[contains(@class,'add_to_cart')]"));
        addToCartLink.click();
    }
}
