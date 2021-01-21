package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.PropertyManager;

public abstract class AbstractPage {
    WebDriver driver;
    WebDriverWait wait;
    PropertyManager props;
    String timeout, fileUrl;

    public AbstractPage(WebDriver driver) {
        props = new PropertyManager();
        timeout = props.get("timeout");
        fileUrl = props.get("fileUrl");
        this.driver = driver;
        wait = new WebDriverWait(this.driver, Integer.parseInt(timeout));
        PageFactory.initElements(driver, this);
    }

    public abstract AbstractPage isPageOpened();
}
