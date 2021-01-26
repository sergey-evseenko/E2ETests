package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GoogleSignInModal extends AbstractPage {

    @FindBy(id = "identifierId")
    WebElement emailInput;
    @FindBy(id = "identifierNext")
    WebElement identifierNext;
    @FindBy(name = "password")
    WebElement passwordInput;
    @FindBy(id = "passwordNext")
    WebElement passwordNext;

    public GoogleSignInModal(WebDriver driver) {
        super(driver);
    }

    @Override
    public GoogleSignInModal isPageOpened() {
        wait.until(ExpectedConditions.visibilityOf(emailInput));
        return this;
    }

    public MainPage provideEmailAndPass(String email, String pass) {
        String winHandleBefore = driver.getWindowHandle();
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        isPageOpened();
        emailInput.sendKeys(email);
        identifierNext.click();
        passwordInput.sendKeys(pass);
        passwordNext.click();
        driver.switchTo().window(winHandleBefore);
        return new MainPage(driver);
    }
}
