package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.AllureUtils;

public class GoogleSignInPage extends BasePage {

    @FindBy(id = "identifierId")
    WebElement emailInput;
    @FindBy(id = "identifierNext")
    WebElement identifierNext;
    @FindBy(name = "password")
    WebElement passwordInput;
    @FindBy(id = "passwordNext")
    WebElement passwordNext;

    public GoogleSignInPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public BasePage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOf(emailInput));
        return null;
    }

    @Override
    public LoginPage openPage(String url) {
        return null;
    }

    public MainPage provideEmailAndPass(String email, String pass) {
        String winHandleBefore = driver.getWindowHandle();
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        isPageOpened();
        AllureUtils.takeScreenshot(driver);
        emailInput.sendKeys(email);
        AllureUtils.takeScreenshot(driver);
        identifierNext.click();
        AllureUtils.takeScreenshot(driver);
        passwordInput.sendKeys(pass);
        AllureUtils.takeScreenshot(driver);
        passwordNext.click();
        AllureUtils.takeScreenshot(driver);
        driver.switchTo().window(winHandleBefore);
        return new MainPage(driver);
    }
}
