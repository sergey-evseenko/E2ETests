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


    @FindBy(id = "firstName")
    WebElement inputFirstName;
    @FindBy(id = "lastName")
    WebElement inputLastName;
    @FindBy(xpath = "//input[@value='Male']")
    WebElement radioButtonMale;
    @FindBy(id = "primaryLanguage")
    WebElement inputPrimaryLanguage;
    @FindBy(id = "dateOfBirth")
    WebElement inputDateOfBirth;
    @FindBy(id = "contact_numbers_0_phoneCountryCode")
    WebElement inputPhoneCountryCode;
    @FindBy(id = "contact_numbers_0_phoneNumber")
    WebElement inputContactNumber;
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
    @FindBy(xpath = "//button[@type='submit']")
    WebElement submitTalentButton;
    @FindBy(xpath = "//span[contains(text(), 'Submit')]")
    WebElement submitSampleButton;


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
        inputDateOfBirth.sendKeys(voiceTalent.getDateOfBirth(), Keys.ENTER);
        inputPhoneCountryCode.sendKeys(voiceTalent.getCountryCode(), Keys.ENTER);
        inputContactNumber.sendKeys(voiceTalent.getContactNumber());
        inputEmail.sendKeys(voiceTalent.getEmail());
        inputOffice.sendKeys(voiceTalent.getOffice(), Keys.ENTER);
        selectValue("status", voiceTalent.getStatus());
        submitTalentButton.click();
        wait.until(ExpectedConditions.visibilityOf(messageSuccessSaving));
        return this;
    }

    public CreateVoiceTalentPage verifySavedData(VoiceTalent voiceTalent) {
        String locatorDropdown = "//input[@id='%s']//..//..//span[@class='ant-select-selection-item']";
        WebElement valuePrimaryLanguage = driver.findElement(By.xpath(String.format(locatorDropdown, "primaryLanguage")));
        WebElement valuePhoneCountryCode = driver.findElement(By.xpath(String.format(locatorDropdown, "contact_numbers_0_phoneCountryCode")));
        WebElement valueOffice = driver.findElement(By.xpath(String.format(locatorDropdown, "office")));
        WebElement valueStatus = driver.findElement(By.xpath(String.format(locatorDropdown, "status")));

        assertEquals(inputFirstName.getAttribute("value"), voiceTalent.getFirstName(), "Invalid first name");
        assertEquals(inputLastName.getAttribute("value"), voiceTalent.getLastName(), "Invalid last name");
        assertTrue(radioButtonMale.isSelected());
        assertEquals(valuePrimaryLanguage.getAttribute("title"), voiceTalent.getPrimaryLanguage(), "Invalid primary language");
        assertEquals(inputDateOfBirth.getAttribute("value"), voiceTalent.getDateOfBirth(), "Invalid date of birthday");
        assertEquals(valuePhoneCountryCode.getAttribute("title"), voiceTalent.getCountryCode(), "Invalid country code");
        assertEquals(inputContactNumber.getAttribute("value"), voiceTalent.getContactNumber(), "Invalid contact number");
        assertEquals(inputEmail.getAttribute("value"), voiceTalent.getEmail(), "Invalid email");
        assertEquals(valueOffice.getAttribute("title"), voiceTalent.getOffice(), "Invalid office");
        assertEquals(valueStatus.getAttribute("title"), voiceTalent.getStatus(), "Invalid status");
        return this;
    }

    public CreateVoiceTalentPage editAndSaveData(VoiceTalent voiceTalent) {
        editButton.click();

        inputFirstName.sendKeys(Keys.chord(Keys.COMMAND, "a"), voiceTalent.getFirstName());
        inputLastName.sendKeys(Keys.chord(Keys.COMMAND, "a"), voiceTalent.getLastName());
        inputPrimaryLanguage.sendKeys(voiceTalent.getPrimaryLanguage(), Keys.ENTER);
        inputDateOfBirth.sendKeys(Keys.chord(Keys.COMMAND, "a"), voiceTalent.getDateOfBirth(), Keys.ENTER);
        inputPhoneCountryCode.sendKeys(voiceTalent.getCountryCode(), Keys.ENTER);
        inputContactNumber.sendKeys(Keys.chord(Keys.COMMAND, "a"), voiceTalent.getContactNumber());
        inputEmail.sendKeys(Keys.chord(Keys.COMMAND, "a"), voiceTalent.getEmail());
        inputOffice.sendKeys(voiceTalent.getOffice(), Keys.ENTER);
        selectValue("status", voiceTalent.getStatus());
        submitTalentButton.click();
        wait.until(ExpectedConditions.visibilityOf(messageSuccessUpdating));
        return this;
    }

    public CreateVoiceTalentPage editAndUploadVoiceSample(VoiceSample voiceSample) {
        editButton.click();
        uploadButton.click();
        inputTitle.sendKeys(voiceSample.getTitle());
        inputLanguage.sendKeys(voiceSample.getLanguage(), Keys.ENTER);
        selectValue("uploadAudioSample_sampleType", voiceSample.getSampleType());
        selectValue("uploadAudioSample_ageRange", voiceSample.getAgeRange());
        inputUploadAudio.sendKeys(voiceSample.getFilePath());
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
