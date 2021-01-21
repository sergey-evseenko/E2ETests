package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends BasePage {

    @FindBy(css = ".ant-input")
    WebElement searchInput;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public MainPage isPageOpened() {
        wait.until(ExpectedConditions.elementToBeClickable(searchInput));
        return this;
    }

    @Override
    public MainPage openPage(String url) {
        return this;
    }


}
