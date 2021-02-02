package web.app.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GeneralPage implements MyPage {
    public static final int TIME_OUT_IN_SECONDS = 3 * 60 * 1000;
    Actions actions;
    WebDriverWait wait;
    PageHeader pageHeader;
    PageFooter pageFooter;
    WebDriver driver;

    public GeneralPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, TIME_OUT_IN_SECONDS);
        this.actions = new Actions(driver);
        this.pageHeader = new PageHeader(driver);
        pageFooter = new PageFooter(driver);
    }

    public WebElement getTopMenuItem(String menu) {
        return pageHeader.topMenu.findElement(By.xpath(String.format("./ul/li[text()='%s']", menu)));
    }

    public SignInPage loadSignInPage() {
        pageHeader.signIn.click();
        return new SignInPage(driver);
    }

    public WebElement getSocialLink(String socialMedia) {
        return pageFooter.socialBlock.findElement(By.xpath(String.format(".//li[@class='%s']", socialMedia)));
    }

    @Override
    public PageHeader getHeader() {
        return pageHeader;
    }

    @Override
    public MyPage getFooter() {
        return pageFooter;
    }
}
