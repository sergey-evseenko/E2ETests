package pages;

import models.VoiceSample;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CreateVoiceSamplePage extends BasePage {

    @FindBy(id = "title")
    WebElement inputTitle;
    @FindBy(id = "voiceTalent")
    WebElement inputVoiceTalent;
    @FindBy(id = "language")
    WebElement inputLanguage;
    @FindBy(xpath = "//input[@value='1']")
    WebElement radioButtonActing;
    @FindBy(id = "genreEntities")
    WebElement inputGenres;
    @FindBy(id = "ageRange")
    WebElement inputAgeRange;
    @FindBy(id = "voiceRangeEntities")
    WebElement inputVoiceRange;
    @FindBy(id = "characteristicEntities")
    WebElement inputCharacteristics;
    @FindBy(id = "internalNote")
    WebElement inputInternalNote;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement submitButton;
    @FindBy(xpath = "//span[contains(text(), 'The record was saved successfully.')]")
    WebElement messageSuccessSaving;
    @FindBy(xpath = "//span[contains(text(), 'The record was updated successfully.')]")
    WebElement messageSuccessUpdating;
    @FindBy(xpath = "//input[@type='file']")
    WebElement inputUploadAudio;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement submitSampleButton;
    @FindBy(xpath = "//span[contains(text(), 'Edit')]")
    WebElement editButton;
    @FindBy(id = "voiceTalent_list_0")
    WebElement elementInTheList;
    @FindBy(id = "previewAudio")
    WebElement previewAudio;
    @FindBy(id = "projectTitle")
    WebElement inputProjectTitle;
    @FindBy(id = "roleName")
    WebElement inputRoleName;

    public CreateVoiceSamplePage(WebDriver driver) {
        super(driver);
    }


    @Override
    public BasePage isPageOpened() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        return null;
    }

    @Override
    public CreateVoiceSamplePage openPage(String url) {
        driver.get(url + "voicesamples/sample");
        isPageOpened();
        return this;
    }

    public CreateVoiceSamplePage provideDataAndSave(VoiceSample voiceSample) {

        inputTitle.sendKeys(voiceSample.getTitle());
        inputVoiceTalent.sendKeys(voiceSample.getVoiceTalent());
        wait.until(ExpectedConditions.invisibilityOf(elementInTheList));
        inputVoiceTalent.sendKeys(Keys.ENTER);
        inputLanguage.sendKeys(voiceSample.getLanguage(), Keys.ENTER);
        inputGenres.sendKeys(voiceSample.getGenre(), Keys.ENTER);
        selectValue("ageRange", voiceSample.getAgeRange());
        inputVoiceRange.sendKeys(voiceSample.getVoiceRange(), Keys.ENTER);
        inputCharacteristics.sendKeys(voiceSample.getCharacteristic(), Keys.ENTER);
        inputInternalNote.sendKeys(voiceSample.getInternalNote());
        inputUploadAudio.sendKeys(voiceSample.getFilePath());
        selectValue("entryType", voiceSample.getEntryType());
        inputProjectTitle.sendKeys(voiceSample.getProjectTitle());
        inputRoleName.sendKeys(voiceSample.getRoleName());
        submitButton.click();
        wait.until(ExpectedConditions.visibilityOf(messageSuccessSaving));
        return this;
    }

    public CreateVoiceSamplePage verifySavedData(VoiceSample voiceSample) {
        String expectedLink = "https://ivoice-voice-samples.s3.us-east-2.amazonaws.com/QA.mp3";

        String locatorDropdown = "//input[@id='%s']//..//..//span[@class='ant-select-selection-item']";
        String locatorMultiDropdown = "//input[@id='%s']//..//..//span[@class='ant-select-selection-item-content']";

        WebElement valueVoiceTalent = driver.findElement(By.xpath(String.format(locatorDropdown, "voiceTalent")));
        WebElement valueLanguage = driver.findElement(By.xpath(String.format(locatorDropdown, "language")));
        WebElement valueGenre = driver.findElement(By.xpath(String.format(locatorMultiDropdown, "genreEntities")));
        WebElement valueAgeRange = driver.findElement(By.xpath(String.format(locatorDropdown, "ageRange")));
        WebElement valueVoiceRange = driver.findElement(By.xpath(String.format(locatorMultiDropdown, "voiceRangeEntities")));
        WebElement valueCharacteristic = driver.findElement(By.xpath(String.format(locatorMultiDropdown, "characteristicEntities")));


        assertEquals(inputTitle.getAttribute("value"), voiceSample.getTitle(), "Invalid title name");
        //ToDo implement validation for Voice Talent Name
        //assertEquals(valueVoiceTalent.getAttribute("title"), voiceSample.getVoiceTalent(), "Invalid voice Talent");
        assertEquals(valueLanguage.getAttribute("title"), voiceSample.getLanguage(), "Invalid language");
        assertTrue(radioButtonActing.isSelected());
        assertEquals(valueGenre.getText(), voiceSample.getGenre(), "Invalid genre");
        assertEquals(valueAgeRange.getAttribute("title"), voiceSample.getAgeRange(), "Invalid age range");
        assertEquals(valueVoiceRange.getText(), voiceSample.getVoiceRange(), "Invalid voice range");
        assertEquals(valueCharacteristic.getText(), voiceSample.getCharacteristic(), "Invalid genre characteristic");
        assertEquals(inputInternalNote.getText(), voiceSample.getInternalNote(), "Invalid title name");
        assertEquals(previewAudio.getAttribute("src"), expectedLink, "Invalid preview audio");
        assertEquals(inputProjectTitle.getAttribute("value"), voiceSample.getProjectTitle(), "Invalid project title");
        assertEquals(inputRoleName.getAttribute("value"), voiceSample.getRoleName(), "Invalid role name");
        return this;
    }

    public CreateVoiceSamplePage editAndSaveData(VoiceSample voiceSample) {
        editButton.click();
        String locatorCrossButton = "//input[@id='%s']//..//..//span[@aria-label='close']";

        WebElement crossButtonForGenre = driver.findElement(By.xpath(String.format(locatorCrossButton, "genreEntities")));
        WebElement crossButtonForVoiceRange = driver.findElement(By.xpath(String.format(locatorCrossButton, "voiceRangeEntities")));
        WebElement crossButtonForCharacteristic = driver.findElement(By.xpath(String.format(locatorCrossButton, "characteristicEntities")));

        inputTitle.sendKeys(Keys.chord(Keys.COMMAND, "a"), voiceSample.getTitle());
        inputLanguage.sendKeys(voiceSample.getLanguage(), Keys.ENTER);
        crossButtonForGenre.click();
        inputGenres.sendKeys(voiceSample.getGenre(), Keys.ENTER);
        selectValue("ageRange", voiceSample.getAgeRange());
        crossButtonForVoiceRange.click();
        inputVoiceRange.sendKeys(voiceSample.getVoiceRange(), Keys.ENTER);
        crossButtonForCharacteristic.click();
        inputCharacteristics.sendKeys(voiceSample.getCharacteristic(), Keys.ENTER);
        inputInternalNote.sendKeys(Keys.chord(Keys.COMMAND, "a"), voiceSample.getInternalNote());
        inputProjectTitle.sendKeys(Keys.chord(Keys.COMMAND, "a"), voiceSample.getProjectTitle());
        inputRoleName.sendKeys(Keys.chord(Keys.COMMAND, "a"), voiceSample.getRoleName());
        submitSampleButton.click();
        wait.until(ExpectedConditions.visibilityOf(messageSuccessUpdating));
        return this;

    }

}
