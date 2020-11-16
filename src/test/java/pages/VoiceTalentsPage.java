package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class VoiceTalentsPage extends BasePage {

    By selectorsLocator = By.cssSelector(".ant-select-selector");

    @FindBy(css = ".ant-btn.ant-btn-primary")
    WebElement addNewButton;
    @FindBy(css = ".ant-input")
    WebElement searchInput;
    @FindBy(css = ".anticon.anticon-close")
    WebElement clearAllButton;


    public VoiceTalentsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public BasePage isPageOpened() {
        wait.until(ExpectedConditions.numberOfElementsToBe(selectorsLocator, 8));
        return null;
    }

    @Override
    public VoiceTalentsPage openPage(String url) {
        driver.get(url + "voicetalents");
        isPageOpened();
        return this;
    }


}
