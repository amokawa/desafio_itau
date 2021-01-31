package web.app.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage extends GeneralPage {
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
    }

    public void signInWithCredentials(String email, String password) {
        this.email.sendKeys(email);
        passwd.sendKeys(password);
        submitSignIn.click();
    }

    public void signOnWithEmail(String email) {
        emailSignOn.sendKeys(email);
    }
}
