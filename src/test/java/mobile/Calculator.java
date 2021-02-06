package mobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

public class Calculator {
    private final AppiumDriver<MobileElement> driver;

    @AndroidFindBy(id = "com.android.calculator2:id/op_add")
    public MobileElement sumButton;

    @AndroidFindBy(id = "com.android.calculator2:id/op_sub")
    public MobileElement subButton;

    @AndroidFindBy(id = "com.android.calculator2:id/op_mul")
    public MobileElement multiplyButton;

    @AndroidFindBy(id = "com.android.calculator2:id/op_div")
    public MobileElement divideButton;

    @AndroidFindBy(id = "com.android.calculator2:id/eq")
    public MobileElement equalsButton;

    @AndroidFindBy(id = "com.android.calculator2:id/dec_point")
    public MobileElement decimalPointButton;

    @AndroidFindBy(id = "com.android.calculator2:id/result")
    public MobileElement resultTextView;

    public Calculator(AppiumDriver<MobileElement> appiumDriver) {
        PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
        this.driver = appiumDriver;
    }

    public void clickOnNumber(String num) {
        By id = By.id(String.format("com.android.calculator2:id/digit_%s", num));
        driver.findElement(id).click();
    }

    public Float getResult() {
        return Float.valueOf(resultTextView.getText().trim().replaceAll(",", ""));
    }

    public void sum(float a, float b) {
        String numA = String.valueOf(a);
        String numB = String.valueOf(b);

        typeNumber(numA);
        sumButton.click();
        typeNumber(numB);
    }

    private void typeNumber(String aNumber) {
        for (String number : aNumber.split("")) {
            if (number.equals(".")) decimalPointButton.click();
            else clickOnNumber(number);
        }
    }
}
