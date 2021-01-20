package pages;

import elements.DropDown;
import models.VoiceSample;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class VoiceSamplePage extends BasePage {

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
    @FindBy(id = "entryType")
    WebElement entryType;
    DropDown languageDropdown = new DropDown(driver, wait, "language");
    DropDown genresDropdown = new DropDown(driver, wait, "genreEntities");
    DropDown ageRangeDropdown = new DropDown(driver, wait, "ageRange");
    DropDown voiceRangeDropdown = new DropDown(driver, wait, "voiceRangeEntities");
    DropDown characteristicDropdown = new DropDown(driver, wait, "characteristicEntities");

    public VoiceSamplePage(WebDriver driver) {
        super(driver);
    }


    @Override
    public BasePage isPageOpened() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        return null;
    }

    @Override
    public VoiceSamplePage openPage(String url) {
        driver.get(url + "voicesamples/sample");
        isPageOpened();
        return this;
    }

    public VoiceSamplePage createAndSave(VoiceSample voiceSample) {

        inputTitle.sendKeys(voiceSample.getTitle());
        inputVoiceTalent.sendKeys(voiceSample.getVoiceTalent());
        wait.until(ExpectedConditions.invisibilityOf(elementInTheList));
        inputVoiceTalent.sendKeys(Keys.ENTER);
        languageDropdown.selectValue(voiceSample.getLanguage());
        genresDropdown.selectValue(voiceSample.getGenre());
        inputTitle.sendKeys(Keys.ESCAPE);
        ageRangeDropdown.selectValue(voiceSample.getAgeRange());
        voiceRangeDropdown.selectValue(voiceSample.getVoiceRange());
        inputTitle.sendKeys(Keys.ESCAPE);
        characteristicDropdown.selectValue(voiceSample.getCharacteristic());
        inputInternalNote.sendKeys(voiceSample.getInternalNote());
        inputUploadAudio.sendKeys(voiceSample.getFilePath());
        entryType.sendKeys(voiceSample.getEntryType());
        inputProjectTitle.sendKeys(voiceSample.getProjectTitle());
        inputRoleName.sendKeys(voiceSample.getRoleName());
        submitButton.click();
        wait.until(ExpectedConditions.visibilityOf(messageSuccessSaving));
        return this;
    }

    public VoiceSamplePage verify(VoiceSample voiceSample) {
        String expectedLink = "https://ivoice-voice-samples.s3.us-east-2.amazonaws.com/QA.mp3";

        assertEquals(inputTitle.getAttribute("value"), voiceSample.getTitle(), "Invalid title name");
        assertEquals(languageDropdown.getValue(), voiceSample.getLanguage(), "Invalid language");
        assertTrue(radioButtonActing.isSelected());
        assertEquals(genresDropdown.getMultiValue(), voiceSample.getGenre(), "Invalid genre");
        assertEquals(ageRangeDropdown.getValue(), voiceSample.getAgeRange(), "Invalid age range");
        assertEquals(voiceRangeDropdown.getMultiValue(), voiceSample.getVoiceRange(), "Invalid voice range");
        assertEquals(characteristicDropdown.getMultiValue(), voiceSample.getCharacteristic(), "Invalid genre characteristic");
        assertEquals(inputInternalNote.getText(), voiceSample.getInternalNote(), "Invalid title name");
        assertEquals(previewAudio.getAttribute("src"), expectedLink, "Invalid preview audio");
        //assertEquals(entryType.getAttribute("value"), voiceSample.getEntryType(), "Invalid entry type");
        assertEquals(inputProjectTitle.getAttribute("value"), voiceSample.getProjectTitle(), "Invalid project title");
        assertEquals(inputRoleName.getAttribute("value"), voiceSample.getRoleName(), "Invalid role name");
        return this;
    }

    public VoiceSamplePage updateAndSave(VoiceSample voiceSample) {

        editButton.click();

        inputTitle.sendKeys(Keys.chord(Keys.COMMAND, "a"), voiceSample.getTitle());
        languageDropdown.selectValue(voiceSample.getLanguage());
        genresDropdown.updateValue(voiceSample.getGenre());
        inputTitle.sendKeys(Keys.ESCAPE);
        ageRangeDropdown.selectValue(voiceSample.getAgeRange());
        voiceRangeDropdown.updateValue(voiceSample.getVoiceRange());
        inputTitle.sendKeys(Keys.ESCAPE);
        characteristicDropdown.updateValue(voiceSample.getCharacteristic());
        inputTitle.sendKeys(Keys.ESCAPE);
        inputInternalNote.sendKeys(Keys.chord(Keys.COMMAND, "a"), voiceSample.getInternalNote());
        entryType.sendKeys(Keys.chord(Keys.COMMAND, "a"), voiceSample.getEntryType());
        inputProjectTitle.sendKeys(Keys.chord(Keys.COMMAND, "a"), voiceSample.getProjectTitle());
        inputRoleName.sendKeys(Keys.chord(Keys.COMMAND, "a"), voiceSample.getRoleName());
        submitSampleButton.click();
        wait.until(ExpectedConditions.visibilityOf(messageSuccessUpdating));
        return this;

    }

}
