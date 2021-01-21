package elements;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@AllArgsConstructor
@Data
public class DropDown {
    String id;
    String locatorForClickingDropDown;
    String locatorForSelectionValue;
    String locatorForGettingValue;
    String locatorCrossButton;
    WebDriver driver;
    WebDriverWait wait;

    public DropDown(WebDriver driver, String id) {
        this.id = id;
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public void selectValue(String value) {
        locatorForClickingDropDown = String.format("//input[@id='%s']//..//..", id);
        driver.findElement(By.xpath(locatorForClickingDropDown)).click();
        locatorForSelectionValue = String.format("//div[@title='%s']", value);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorForSelectionValue)));
        driver.findElement(By.xpath(locatorForSelectionValue)).click();
    }

    public void selectMultiValue(String value, WebElement element) {
        selectValue(value);
        element.sendKeys(Keys.ESCAPE);
    }

    public String getValue() {
        locatorForGettingValue = String.format("//input[@id='%s']//..//..//span[@class='ant-select-selection-item']", id);
        return driver.findElement(By.xpath(locatorForGettingValue)).getAttribute("title");
    }

    public String getMultiValue() {
        locatorForGettingValue = String.format("//input[@id='%s']//..//..//span[@class='ant-select-selection-item-content']", id);
        return driver.findElement(By.xpath(locatorForGettingValue)).getText();
    }

    public void updateValue(String value) {
        locatorCrossButton = String.format("//input[@id='%s']//..//..//span[@aria-label='close']", id);
        driver.findElement(By.xpath(locatorCrossButton)).click();
        selectValue(value);
    }
}




