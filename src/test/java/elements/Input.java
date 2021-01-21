package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class Input {
    String id;
    WebDriver driver;

    public Input(WebDriver driver, String id) {
        this.id = id;
        this.driver = driver;
    }

    public void write(String text) {
        driver.findElement(By.id(id)).sendKeys(Keys.chord(Keys.COMMAND, "a"), text);
    }

    public String getValue() {
        return driver.findElement(By.id(id)).getAttribute("value");
    }
}
