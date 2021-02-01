package web.app.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage extends GeneralPage {
    public static final String SIGN_IN_URL = "http://automationpractice.com/index.php?controller=authentication&back=my-account";

    @FindBy(id = "email")
    WebElement email;

    @FindBy(id = "passwd")
    WebElement passwd;

    @FindBy(xpath = "//p[@class='lost_password form-group']/a")
    WebElement recoverPassword;

    @FindBy(id = "SubmitLogin")
    WebElement submitSignIn;

    @FindBy(id = "email_create")
    WebElement emailSignOn;

    @FindBy(id = "SubmitCreate")
    WebElement submitSignOn;

    public SignInPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        if (!driver.getCurrentUrl().contains(SIGN_IN_URL)) {
            driver.get(SIGN_IN_URL);
        }
    }

    public MyPage signInWithCredentials(String email, String password) {
        this.email.sendKeys(email);
        passwd.sendKeys(password);
        submitSignIn.click();
        if (hasNoErrorAlert()) {
            return new MyAccountPage(driver);
        } else return this;
    }

    private boolean hasNoErrorAlert() {
        return driver.findElements(By.xpath("//div[@class='alert alert-danger']")).size() == 0;
    }

    public SignOnPage signOnWithEmail(String email) {
        emailSignOn.sendKeys(email);
        submitSignOn.click();

        return new SignOnPage(driver);
    }
}
