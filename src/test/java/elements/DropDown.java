package elements;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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

    public DropDown(WebDriverWait wait) {
        this.wait = wait;
    }

    public DropDown(WebDriver driver, WebDriverWait wait, String id) {
        this.id = id;
        this.driver = driver;
        this.wait = wait;
    }

    public void selectValue(String value) {
        locatorForClickingDropDown = String.format("//input[@id='%s']//..//..", id);
        driver.findElement(By.xpath(locatorForClickingDropDown)).click();
        locatorForSelectionValue = String.format("//div[@title='%s']", value);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorForSelectionValue)));
        driver.findElement(By.xpath(locatorForSelectionValue)).click();
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




