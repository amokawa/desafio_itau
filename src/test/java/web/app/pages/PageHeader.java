package web.app.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class PageHeader implements MyPage {
    WebDriver driver;

    @FindBy(xpath = "//div[@class='banner']")
    WebElement banner;

    @FindBy(xpath = "//div[@class='nav']")
    WebElement nav;

    @FindBy(id = "contact-link")
    WebElement contactUs;

    @FindBy(xpath = "//a[@class='login']")
    WebElement signIn;

    @FindBy(xpath = "//img[@class='logo img-responsive']")
    WebElement logo;

    @FindBy(id = "search_query_top")
    WebElement searchBox;

    @FindBy(xpath = "//button[@name='submit_search']")
    WebElement submitSearch;

    @FindBy(xpath = "//div[@class='shopping_cart']/a")
    WebElement shoppingCart;

    @FindBy(xpath = "//div[@class='cart_block_list']//dt")
    List<WebElement> cartListItems;

    @FindBy(id = "block_top_menu")
    WebElement topMenu;

    public PageHeader(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement getShoppingCart() {
        return shoppingCart;
    }

    public void removeItemFromCart(int index) {
        String text = shoppingCart.getText();
        Actions actions = new Actions(driver);
        WebElement target = cartListItems.get(index - 1);
        WebElement cartItemRemoveButton = target.findElement(By.xpath(".//a[@class='ajax_cart_block_remove_link']"));
        Actions action1 = actions.moveToElement(shoppingCart);
        Actions action2 = action1.moveToElement(target);
        Actions action3 = action2.moveToElement(cartItemRemoveButton);
        Actions action4 = action3.click();
        action4.perform();
        new WebDriverWait(driver, 3 * 60 * 1000).until(driver -> !text.equals(shoppingCart.getText()));
    }
}
