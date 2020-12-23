package pages;

import models.VoiceSample;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.testng.Assert.assertEquals;

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
        inputLanguage.sendKeys(voiceSample.getLanguage(), Keys.ENTER);
        radioButtonActing.click();
        inputGenres.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
        inputAgeRange.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
        inputVoiceRange.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
        inputCharacteristics.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
        inputInternalNote.sendKeys(voiceSample.getInternalNote());
        inputUploadAudio.sendKeys("/Users/sergeyevseenko/IdeaProjects/Ivoice/src/test/resources/QA.mp3");

        inputVoiceTalent.sendKeys(voiceSample.getVoiceTalent());
        wait.until(ExpectedConditions.invisibilityOf(elementInTheList));
        inputVoiceTalent.sendKeys(Keys.ENTER);

        submitButton.click();
        wait.until(ExpectedConditions.visibilityOf(messageSuccessSaving));
        return this;
    }

    public CreateVoiceSamplePage verifySavedData(VoiceSample voiceSample) {
        String locator = "//input[@id='%s']/../..//span[@class='ant-select-selection-item']";
        assertEquals(inputTitle.getAttribute("value"), voiceSample.getTitle(), "Invalid title name");
        assertEquals(driver.findElement(By.xpath(String.format(locator, "voiceTalent"))).getText(), voiceSample.getVoiceTalent(), "Invalid voice talent");
        assertEquals(driver.findElement(By.xpath(String.format(locator, "language"))).getText(), voiceSample.getLanguage(), "Invalid voice talent");
        assertEquals(inputInternalNote.getText(), voiceSample.getInternalNote(), "Invalid title name");
        return this;
    }

    public CreateVoiceSamplePage editAndSaveData(VoiceSample voiceSample) {
        editButton.click();
        inputTitle.sendKeys(Keys.chord(Keys.COMMAND, "a"), voiceSample.getTitle());
        inputInternalNote.sendKeys(Keys.chord(Keys.COMMAND, "a"), voiceSample.getInternalNote());
        submitSampleButton.click();
        wait.until(ExpectedConditions.visibilityOf(messageSuccessUpdating));
        return this;
    }

}
