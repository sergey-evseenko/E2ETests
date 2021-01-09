package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
    WebDriver driver;
    WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver, 10);
        PageFactory.initElements(driver, this);
    }

    public abstract BasePage isPageOpened();

    public abstract BasePage openPage(String url);

    public void selectValue(String id, String value) {

        String locatorForClick = String.format("//input[@id='%s']//..//..", id);
        driver.findElement(By.xpath(locatorForClick)).click();
        //String locatorForSelect = String.format("//div[@label='%s']", value);
        String locatorForSelect = String.format("//div[@title='%s']", value);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorForSelect)));
        driver.findElement(By.xpath(locatorForSelect)).click();
    }
}
