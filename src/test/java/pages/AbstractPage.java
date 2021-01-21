package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.PropertyManager;

public abstract class AbstractPage {
    WebDriver driver;
    WebDriverWait wait;
    PropertyManager props;
    String timeout;

    public AbstractPage(WebDriver driver) {
        props = new PropertyManager();
        timeout = props.get("timeout");
        this.driver = driver;
        wait = new WebDriverWait(this.driver, Integer.parseInt(timeout));
        PageFactory.initElements(driver, this);
    }

    public abstract AbstractPage isPageOpened();
}
