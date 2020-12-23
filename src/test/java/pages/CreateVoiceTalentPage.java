package pages;

import models.VoiceSample;
import models.VoiceTalent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CreateVoiceTalentPage extends BasePage {

    @FindBy(xpath = "//button[@type='submit']")
    WebElement submitTalentButton;
    @FindBy(xpath = "//span[contains(text(), 'Submit')]")
    WebElement submitSampleButton;
    @FindBy(id = "firstName")
    WebElement inputFirstName;
    @FindBy(id = "lastName")
    WebElement inputLastName;
    @FindBy(xpath = "//input[@value='Male']")
    WebElement radioButtonMale;
    @FindBy(id = "primaryLanguage")
    WebElement inputPrimaryLanguage;
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
    @FindBy(id = "email")
    WebElement inputEmail;
    @FindBy(id = "status")
    WebElement inputStatus;
    @FindBy(id = "office")
    WebElement inputOffice;
    @FindBy(xpath = "//span[contains(text(), 'The record was saved successfully.')]")
    WebElement messageSuccessSaving;
    @FindBy(xpath = "//span[contains(text(), 'The record was updated successfully.')]")
    WebElement messageSuccessUpdating;
    @FindBy(xpath = "//span[contains(text(), 'Edit')]")
    WebElement editButton;
    @FindBy(xpath = "//span[contains(text(), 'Upload')]")
    WebElement uploadButton;
    @FindBy(id = "uploadAudioSample_audioSample")
    WebElement inputUploadAudio;
    @FindBy(id = "uploadAudioSample_title")
    WebElement inputTitle;
    @FindBy(id = "uploadAudioSample_language")
    WebElement inputLanguage;
    @FindBy(id = "uploadAudioSample_sampleType")
    WebElement inputSampleType;
    @FindBy(id = "uploadAudioSample_ageRange")
    WebElement inputAgeRange;
    @FindBy(css = ".ant-table-row.ant-table-row-level-0")
    WebElement voiceSamplesTable;


    public CreateVoiceTalentPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public BasePage isPageOpened() {
        wait.until(ExpectedConditions.elementToBeClickable(submitTalentButton));
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
        submitTalentButton.click();
        wait.until(ExpectedConditions.visibilityOf(messageSuccessSaving));
        return this;
    }

    public CreateVoiceTalentPage verifySavedData(VoiceTalent voiceTalent) {
        String locatorDropdown = "//input[@id='%s']//..//..//span[@class='ant-select-selection-item']";

        WebElement valuePrimaryLanguage = driver.findElement(By.xpath(String.format(locatorDropdown, "primaryLanguage")));
        WebElement valuePhoneCountryCode = driver.findElement(By.xpath(String.format(locatorDropdown, "contact_numbers_0_phoneCountryCode")));

        assertEquals(inputFirstName.getAttribute("value"), voiceTalent.getFirstName(), "Invalid first name");
        assertEquals(inputLastName.getAttribute("value"), voiceTalent.getLastName(), "Invalid last name");
        assertEquals(inputEmail.getAttribute("value"), voiceTalent.getEmail(), "Invalid email");
        assertEquals(inputMessengerId.getAttribute("value"), voiceTalent.getMessengerId(), "Invalid messenger id");
        assertEquals(inputContactNumber.getAttribute("value"), voiceTalent.getContactNumber(), "Invalid contact number");
        assertTrue(radioButtonMale.isSelected());
        assertEquals(valuePrimaryLanguage.getAttribute("title"), voiceTalent.getPrimaryLanguage(), "Invalid primary language");
        assertEquals(valuePhoneCountryCode.getAttribute("title"), voiceTalent.getCountryCode(), "Invalid country code");
        return this;
    }

    public CreateVoiceTalentPage editAndSaveData(VoiceTalent voiceTalent) {
        editButton.click();
        inputFirstName.sendKeys(Keys.chord(Keys.COMMAND, "a"), voiceTalent.getFirstName());
        inputLastName.sendKeys(Keys.chord(Keys.COMMAND, "a"), voiceTalent.getLastName());
        inputEmail.sendKeys(Keys.chord(Keys.COMMAND, "a"), voiceTalent.getEmail());
        inputMessengerId.sendKeys(Keys.chord(Keys.COMMAND, "a"), voiceTalent.getMessengerId());
        inputContactNumber.sendKeys(Keys.chord(Keys.COMMAND, "a"), voiceTalent.getContactNumber());
        submitTalentButton.click();
        wait.until(ExpectedConditions.visibilityOf(messageSuccessUpdating));
        return this;
    }

    public CreateVoiceTalentPage editAndUploadVoiceSample(VoiceSample voiceSample) {
        editButton.click();
        uploadButton.click();
        inputTitle.sendKeys(voiceSample.getTitle());
        inputLanguage.sendKeys(voiceSample.getLanguage(), Keys.ENTER);
        inputSampleType.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
        inputAgeRange.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
        inputUploadAudio.sendKeys("/Users/sergeyevseenko/IdeaProjects/Ivoice/src/test/resources/QA.mp3");
        submitSampleButton.click();
        submitTalentButton.click();
        wait.until(ExpectedConditions.visibilityOf(messageSuccessUpdating));
        return this;
    }

    public CreateVoiceTalentPage verifyUploadedVoiceSample(VoiceSample voiceSample) {
        List<WebElement> listOfVoiceSamples = driver.findElements(By.cssSelector(".ant-table-row.ant-table-row-level-0"));
        assertEquals(listOfVoiceSamples.size(), 1, "Voice sample was uploaded incorrectly");
        return this;
    }
}
