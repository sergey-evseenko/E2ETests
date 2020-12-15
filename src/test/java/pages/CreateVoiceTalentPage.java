package pages;

import models.VoiceTalent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CreateVoiceTalentPage extends BasePage {

    @FindBy(xpath = "//button[@type='submit']")
    WebElement submitButton;
    @FindBy(id = "firstName")
    WebElement inputFirstName;
    @FindBy(id = "lastName")
    WebElement inputLastName;
    @FindBy(id = "email")
    WebElement inputEmail;
    @FindBy(id = "primaryLanguage")
    WebElement inputPrimaryLanguage;
    @FindBy(id = "status")
    WebElement inputStatus;
    @FindBy(id = "monthOfBirthday")
    WebElement inputMonthOfBirthday;
    @FindBy(id = "dayOfBirthday")
    WebElement inputDayOfBirthday;
    @FindBy(id = "yearOfBirthday")
    WebElement inputYearOfBirthday;
    @FindBy(id = "contact_numbers_0_phoneCountryCode")
    WebElement inputPhoneCountryCode;
    @FindBy(id = "contact_numbers_0_phoneNumber")
    WebElement inputContactNumber;
    @FindBy(id = "messengers_0_messengerType")
    WebElement inputMessengerType;
    @FindBy(id = "messengers_0_messengerId")
    WebElement inputMessengerId;
    @FindBy(id = "office")
    WebElement inputOffice;
    @FindBy(xpath = "//span[contains(text(), 'The record was saved successfully.')]")
    WebElement messageSuccess;
    @FindBy(xpath = "//input[@value='Male']")
    WebElement radioButtonMale;
    @FindBy(xpath = "//input[@value='Female']")
    WebElement radioButtonFemale;
    @FindBy(xpath = "//span[contains(text(), 'Edit')]")
    WebElement editButton;

    String locatorDropdown = "//input[@id='%s']//..//..//span[@class='ant-select-selection-item']";

    public CreateVoiceTalentPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public BasePage isPageOpened() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        return null;
    }

    @Override
    public CreateVoiceTalentPage openPage(String url) {
        driver.get(url + "voicetalents/talent/");
        isPageOpened();
        return this;
    }

    public CreateVoiceTalentPage provideDataAndSave(VoiceTalent voiceTalent) {
        inputFirstName.sendKeys(voiceTalent.getFirstName());
        inputLastName.sendKeys(voiceTalent.getLastName());
        inputPrimaryLanguage.sendKeys(voiceTalent.getPrimaryLanguage(), Keys.ENTER);
        inputMonthOfBirthday.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
        inputDayOfBirthday.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
        inputYearOfBirthday.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
        inputPhoneCountryCode.sendKeys(voiceTalent.getCountryCode(), Keys.ENTER);
        inputContactNumber.sendKeys(voiceTalent.getContactNumber());
        inputMessengerType.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
        inputMessengerId.sendKeys(voiceTalent.getMessengerId());
        inputStatus.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
        inputEmail.sendKeys(voiceTalent.getEmail());
        inputOffice.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
        submitButton.click();
        wait.until(ExpectedConditions.visibilityOf(messageSuccess));
        return this;
    }

    public CreateVoiceTalentPage verifySavedData(VoiceTalent voiceTalent) {
        WebElement valuePrimaryLanguage = driver.findElement(By.xpath(String.format(locatorDropdown, "primaryLanguage")));
        WebElement valuePhoneCountryCode = driver.findElement(By.xpath(String.format(locatorDropdown, "contact_numbers_0_phoneCountryCode")));

        assertEquals(inputFirstName.getAttribute("value"), voiceTalent.getFirstName(), "Invalid first name");
        assertEquals(inputLastName.getAttribute("value"), voiceTalent.getLastName(), "Invalid last name");
        assertTrue(radioButtonMale.isSelected());
        assertEquals(valuePrimaryLanguage.getAttribute("title"), voiceTalent.getPrimaryLanguage(), "Invalid primary language");
        assertEquals(valuePhoneCountryCode.getAttribute("title"), voiceTalent.getCountryCode(), "Invalid country code");
        assertEquals(inputContactNumber.getAttribute("value"), voiceTalent.getContactNumber(), "Invalid contact number");
        assertEquals(inputMessengerId.getAttribute("value"), voiceTalent.getMessengerId(), "Invalid messenger id");
        assertEquals(inputEmail.getAttribute("value"), voiceTalent.getEmail(), "Invalid email");
        return this;
    }

    public CreateVoiceTalentPage editAndSaveData(VoiceTalent voiceTalent) {
        editButton.click();
        String param = "arguments[0].value='%s';";

        inputLastName.clear();

        /*


        ((JavascriptExecutor) driver).executeScript(String.format(param, "test"), inputFirstName);
        inputLastName.click();
        ((JavascriptExecutor) driver).executeScript(String.format(param, "test"), inputLastName);
        radioButtonFemale.click();
        inputPrimaryLanguage.sendKeys(voiceTalent.getPrimaryLanguage(), Keys.ENTER);
        inputPhoneCountryCode.sendKeys(voiceTalent.getCountryCode(), Keys.ENTER);
        ((JavascriptExecutor) driver).executeScript(String.format(param, voiceTalent.getContactNumber()), inputContactNumber);
        ((JavascriptExecutor) driver).executeScript(String.format(param, "test"), inputMessengerId);
        ((JavascriptExecutor) driver).executeScript(String.format(param, "test@gmail.com"), inputEmail);
        submitButton.click();


         */


        return this;
    }
}
