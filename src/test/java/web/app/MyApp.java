package web.app;

import org.openqa.selenium.WebDriver;
import web.app.pages.HomePage;
import web.app.pages.MyPage;
import web.app.pages.NoPageFoundException;

public class MyApp {
    WebDriver driver;

    public MyApp(WebDriver driver) {
        this.driver = driver;
    }

    public MyPage getPage(String page) {
        if ("home".equalsIgnoreCase(page)) {
            return new HomePage(driver);
        }
        throw new NoPageFoundException(page);
    }
}
