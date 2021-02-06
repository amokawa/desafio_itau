package mobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class MobileStepDefinitions {
    private AppiumDriver<MobileElement> driver;

    @Before("@mobile and @calculator")
    public void setUp() throws MalformedURLException {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("platform", "Android");
        cap.setCapability("platformVersion", "9.0");
        cap.setCapability("deviceName", "emulator-5554");
        cap.setCapability("appPackage", "com.android.calculator2");
        cap.setCapability("appActivity", "com.android.calculator2.Calculator");
        cap.setCapability("fastReset", "true");
        driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), cap);
    }

    @After("@mobile and @calculator")
    public void tearDown(Scenario scenario) {
        byte[] screenshotAs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        String imageName = String.join("_", scenario.getName().split("\\s+"));
        scenario.attach(screenshotAs, "image/png", imageName);

        driver.closeApp();
        driver.quit();
    }

    @When("somar {int} e {int}")
    public void sumNumbers(int a, int b) {
        driver.findElement(By.id("com.android.calculator2:id/digit_1")).click();
        driver.findElement(By.id("com.android.calculator2:id/op_add")).click();
        driver.findElement(By.id("com.android.calculator2:id/digit_5")).click();
        driver.findElement(By.id("com.android.calculator2:id/eq")).click();
    }

    @Then("o resultado deve ser {int}")
    public void assertResult(int expected) {

    }
}
