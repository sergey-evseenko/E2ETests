package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//button[@type='button']")
    WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public LoginPage isPageOpened() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        return this;
    }

    @Override
    public LoginPage openPage(String url) {
        driver.get(url);
        isPageOpened();
        return this;
    }

    public GoogleSignInPage clickLoginButton() {
        loginButton.click();
        return new GoogleSignInPage(driver);
    }


}
