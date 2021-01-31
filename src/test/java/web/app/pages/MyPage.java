package web.app.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface MyPage {

    default List<WebElement> getWebElementList(WebDriver driver) {
        List<Object> objects = new ArrayList<>();
        for (Field field : this.getClass().getDeclaredFields()) {
            try{
                Object o = field.get(this.getClass().getConstructor(WebDriver.class).newInstance(driver));
                if (field.getType().equals(WebElement.class)) {
                    objects.add(o);
                } else if (field.getType().equals(List.class)) {
                    objects.addAll((Collection<?>) o);
                }
            } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return (List<WebElement>)(Object) objects;
    }

    default PageHeader getHeader() {
        return null;
    }

    default MyPage getFooter(){
        return null;
    }
}
