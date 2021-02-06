package platform.mobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class MobileStepDefinitions {
    private AppiumDriver<MobileElement> driver;
    private Calculator calculator;

    @Before("@mobile and @calculator")
    public void setUp() throws MalformedURLException {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.0");
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        cap.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.android.calculator2");
        cap.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.android.calculator2.Calculator");
        driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), cap);

        calculator = new Calculator(driver);
    }

    @After("@mobile and @calculator")
    public void tearDown(Scenario scenario) {
        byte[] screenshotAs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        String imageName = String.join("_", scenario.getName().split("\\s+"));
        scenario.attach(screenshotAs, "image/png", imageName);

        driver.closeApp();
        driver.quit();
    }

    @When("somar {float} e {float}")
    public void sumNumbers(float a, float b) {
        calculator.sum(a, b);
    }

    @Then("o resultado deve ser {float}")
    public void assertResult(float expected) {
        Assert.assertEquals(String.valueOf(expected), calculator.getResult().toString());
    }
}
