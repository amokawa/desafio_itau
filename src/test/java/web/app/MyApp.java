package web.app;

import org.openqa.selenium.WebDriver;
import web.app.pages.*;

public class MyApp {
    WebDriver driver;

    public MyApp(WebDriver driver) {
        this.driver = driver;
    }

    public MyPage getPage(String page) {
        switch (page.toLowerCase()) {
            case "home":
                return new HomePage(driver);
            case "sign in":
                return new SignInPage(driver);
            case "order":
                return new OrderPage(driver);
            default:
                throw new PageObjectNotFoundException(page);
        }
    }
}
