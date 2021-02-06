package platform.web.app.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPage extends GeneralPage{
    @FindBy(xpath = "//h1[text()='My account']")
    WebElement pageHeading;

    @FindBy(xpath = "//a[@tilte='Orders']")
    WebElement ordersLink;

    @FindBy(xpath = "//a[@tilte='Credit slips']")
    WebElement credutSlipsLink;

    @FindBy(xpath = "//a[@tilte='Addresses']")
    WebElement addressesLink;

    @FindBy(xpath = "//a[@tilte='Information']")
    WebElement informationLink;

    @FindBy(xpath = "//a[@tilte='My wishlists']")
    WebElement myWishListsLink;

    public MyAccountPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
}
