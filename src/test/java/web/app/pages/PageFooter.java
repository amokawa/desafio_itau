package web.app.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PageFooter implements MyPage {
    // FOOTER elements...
    @FindBy(id = "newsletter-input")
    WebElement newsletterInput;

    @FindBy(id = "social_block")
    WebElement socialBlock;

    @FindBy(xpath = "//div[contains(@class, 'category_footer')]/div//ul[not(@style='display: none;')]/li")
    List<WebElement> categories;

    @FindBy(xpath = "//section[@id='block_various_links_footer']//li")
    List<WebElement> informationLinks;

    @FindBy(xpath = "//h4/a[text()='My account']/../..//li")
    List<WebElement> myAccountLinks;

    @FindBy(xpath = "//section[@id='block_contact_infos']//li")
    List<WebElement> storeInformationLinks;

    public PageFooter(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
