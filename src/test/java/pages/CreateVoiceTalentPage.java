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
        inputContactNumber.sendKeys(voiceTalent.getContactNumber());
        inputMessengerId.sendKeys(voiceTalent.getMessengerId());
        inputEmail.sendKeys(voiceTalent.getEmail());
        inputPrimaryLanguage.sendKeys(voiceTalent.getPrimaryLanguage(), Keys.ENTER);
        inputPhoneCountryCode.sendKeys(voiceTalent.getCountryCode(), Keys.ENTER);
        inputYearOfBirthday.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
        inputDayOfBirthday.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
        selectValue("monthOfBirthday", voiceTalent.getDateOfBirthMonth());
        selectValue("messengers_0_messengerType", voiceTalent.getMessengerType());
        selectValue("office", voiceTalent.getOffice());
        selectValue("status", voiceTalent.getStatus());
        submitTalentButton.click();
        wait.until(ExpectedConditions.visibilityOf(messageSuccessSaving));
        return this;
    }

    public void selectValue(String id, String value) {

        String locatorForClick = String.format("//input[@id='%s']//..//..", id);
        driver.findElement(By.xpath(locatorForClick)).click();
        String locatorForSelect = String.format("//div[@label='%s']", value);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorForSelect)));
        driver.findElement(By.xpath(locatorForSelect)).click();
    }

    public CreateVoiceTalentPage verifySavedData(VoiceTalent voiceTalent) {
        String locatorDropdown = "//input[@id='%s']//..//..//span[@class='ant-select-selection-item']";
        WebElement valuePrimaryLanguage = driver.findElement(By.xpath(String.format(locatorDropdown, "primaryLanguage")));
        WebElement valueMonthOfBirthday = driver.findElement(By.xpath(String.format(locatorDropdown, "monthOfBirthday")));
        WebElement valuePhoneCountryCode = driver.findElement(By.xpath(String.format(locatorDropdown, "contact_numbers_0_phoneCountryCode")));
        WebElement valueMessengerType = driver.findElement(By.xpath(String.format(locatorDropdown, "messengers_0_messengerType")));
        WebElement valueOffice = driver.findElement(By.xpath(String.format(locatorDropdown, "office")));
        WebElement valueStatus = driver.findElement(By.xpath(String.format(locatorDropdown, "status")));

        assertEquals(inputFirstName.getAttribute("value"), voiceTalent.getFirstName(), "Invalid first name");
        assertEquals(inputLastName.getAttribute("value"), voiceTalent.getLastName(), "Invalid last name");
        assertTrue(radioButtonMale.isSelected());
        assertEquals(valuePrimaryLanguage.getAttribute("title"), voiceTalent.getPrimaryLanguage(), "Invalid primary language");
        //assertEquals(valueMonthOfBirthday.getAttribute("title"), voiceTalent.getDateOfBirthMonth(), "Invalid month of birthday");
        assertEquals(valuePhoneCountryCode.getAttribute("title"), voiceTalent.getCountryCode(), "Invalid country code");
        assertEquals(inputContactNumber.getAttribute("value"), voiceTalent.getContactNumber(), "Invalid contact number");
        assertEquals(valueMessengerType.getAttribute("title"), voiceTalent.getMessengerType(), "Invalid messenger type");
        assertEquals(inputMessengerId.getAttribute("value"), voiceTalent.getMessengerId(), "Invalid messenger id");
        assertEquals(inputEmail.getAttribute("value"), voiceTalent.getEmail(), "Invalid email");
        assertEquals(valueOffice.getAttribute("title"), voiceTalent.getOffice(), "Invalid office");
        assertEquals(valueStatus.getAttribute("title"), voiceTalent.getStatus(), "Invalid status");
        return this;
    }

    public CreateVoiceTalentPage editAndSaveData(VoiceTalent voiceTalent) {
        editButton.click();

        inputFirstName.sendKeys(Keys.chord(Keys.COMMAND, "a"), voiceTalent.getFirstName());
        inputLastName.sendKeys(Keys.chord(Keys.COMMAND, "a"), voiceTalent.getLastName());
        inputContactNumber.sendKeys(Keys.chord(Keys.COMMAND, "a"), voiceTalent.getContactNumber());
        inputMessengerId.sendKeys(Keys.chord(Keys.COMMAND, "a"), voiceTalent.getMessengerId());
        inputEmail.sendKeys(Keys.chord(Keys.COMMAND, "a"), voiceTalent.getEmail());
        inputPrimaryLanguage.sendKeys(voiceTalent.getPrimaryLanguage(), Keys.ENTER);
        inputPhoneCountryCode.sendKeys(voiceTalent.getCountryCode(), Keys.ENTER);
        selectValue("monthOfBirthday", voiceTalent.getDateOfBirthMonth());
        selectValue("messengers_0_messengerType", voiceTalent.getMessengerType());
        selectValue("office", voiceTalent.getOffice());
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
