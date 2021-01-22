package pages;

import elements.DropDown;
import elements.Input;
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
    WebElement title;
    @FindBy(id = "voiceTalent")
    WebElement inputVoiceTalent;
    @FindBy(xpath = "//input[@value='1']")
    WebElement radioButtonActing;
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

    Input inputTitle = new Input(driver, "title");
    Input inputInternalNote = new Input(driver, "internalNote");
    Input entryType = new Input(driver, "entryType");
    Input inputProjectTitle = new Input(driver, "projectTitle");
    Input inputRoleName = new Input(driver, "roleName");

    DropDown languageDropdown = new DropDown(driver, "language");
    DropDown genresDropdown = new DropDown(driver, "genreEntities");
    DropDown ageRangeDropdown = new DropDown(driver, "ageRange");
    DropDown voiceRangeDropdown = new DropDown(driver, "voiceRangeEntities");
    DropDown characteristicDropdown = new DropDown(driver, "characteristicEntities");

    public VoiceSamplePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public VoiceSamplePage isPageOpened() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        return this;
    }

    @Override
    public VoiceSamplePage openPage(String url) {
        driver.get(url + "voicesamples/sample");
        isPageOpened();
        return this;
    }

    public VoiceSamplePage create(VoiceSample voiceSample) {
        inputTitle.write(voiceSample.getTitle());
        inputVoiceTalent.sendKeys(voiceSample.getVoiceTalent());
        wait.until(ExpectedConditions.invisibilityOf(elementInTheList));
        inputVoiceTalent.sendKeys(Keys.ENTER);
        languageDropdown.selectValue(voiceSample.getLanguage());
        genresDropdown.selectMultiValue(voiceSample.getGenre(), title);
        ageRangeDropdown.selectValue(voiceSample.getAgeRange());
        voiceRangeDropdown.selectMultiValue(voiceSample.getVoiceRange(), title);
        characteristicDropdown.selectMultiValue(voiceSample.getCharacteristic(), title);
        inputInternalNote.write(voiceSample.getInternalNote());
        inputUploadAudio.sendKeys(voiceSample.getFilePath());
        inputProjectTitle.write(voiceSample.getProjectTitle());
        inputRoleName.write(voiceSample.getRoleName());
        submitButton.click();
        wait.until(ExpectedConditions.visibilityOf(messageSuccessSaving));
        return this;
    }

    public VoiceSamplePage verify(VoiceSample voiceSample) {
        String expectedLink = fileUrl + voiceSample.getFileName();

        assertEquals(inputTitle.getValue(), voiceSample.getTitle(), "Invalid title name");
        assertEquals(languageDropdown.getValue(), voiceSample.getLanguage(), "Invalid language");
        assertTrue(radioButtonActing.isSelected());
        assertEquals(genresDropdown.getMultiValue(), voiceSample.getGenre(), "Invalid genre");
        assertEquals(ageRangeDropdown.getValue(), voiceSample.getAgeRange(), "Invalid age range");
        assertEquals(voiceRangeDropdown.getMultiValue(), voiceSample.getVoiceRange(), "Invalid voice range");
        assertEquals(characteristicDropdown.getMultiValue(), voiceSample.getCharacteristic(), "Invalid genre characteristic");
        assertEquals(inputInternalNote.getValue(), voiceSample.getInternalNote(), "Invalid title name");
        assertEquals(previewAudio.getAttribute("src"), expectedLink, "Invalid preview audio");
        assertEquals(inputProjectTitle.getValue(), voiceSample.getProjectTitle(), "Invalid project title");
        assertEquals(inputRoleName.getValue(), voiceSample.getRoleName(), "Invalid role name");
        return this;
    }

    public VoiceSamplePage update(VoiceSample voiceSample) {
        editButton.click();
        wait.until(ExpectedConditions.visibilityOf(submitSampleButton));
        inputTitle.write(voiceSample.getTitle());
        languageDropdown.selectValue(voiceSample.getLanguage());
        genresDropdown.updateValue(voiceSample.getGenre(), title);
        ageRangeDropdown.selectValue(voiceSample.getAgeRange());
        voiceRangeDropdown.updateValue(voiceSample.getVoiceRange(), title);
        characteristicDropdown.updateValue(voiceSample.getCharacteristic(), title);
        inputInternalNote.write(voiceSample.getInternalNote());
        inputProjectTitle.write(voiceSample.getProjectTitle());
        inputRoleName.write(voiceSample.getRoleName());
        submitSampleButton.click();
        wait.until(ExpectedConditions.visibilityOf(messageSuccessUpdating));
        return this;
    }
}
