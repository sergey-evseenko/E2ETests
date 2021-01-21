package pages;

import elements.DropDown;
import models.VoiceSample;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UploadModal extends AbstractPage {

    @FindBy(id = "uploadAudioSample_audioSample")
    WebElement inputUploadAudio;
    @FindBy(id = "uploadAudioSample_title")
    WebElement inputTitle;
    @FindBy(id = "uploadAudioSample_language")
    WebElement inputLanguage;
    @FindBy(xpath = "//span[contains(text(), 'Submit')]")
    WebElement submitSampleButton;
    DropDown sampleTypeDropdown = new DropDown(driver, "uploadAudioSample_sampleType");
    DropDown ageRangeDropdown = new DropDown(driver, "uploadAudioSample_ageRange");

    public UploadModal(WebDriver driver) {
        super(driver);
    }

    @Override
    public UploadModal isPageOpened() {
        return this;
    }

    public VoiceTalentPage upload(VoiceSample voiceSample) {
        inputTitle.sendKeys(voiceSample.getTitle());
        inputLanguage.sendKeys(voiceSample.getLanguage(), Keys.ENTER);
        sampleTypeDropdown.selectValue(voiceSample.getSampleType());
        ageRangeDropdown.selectValue(voiceSample.getAgeRange());
        inputUploadAudio.sendKeys(voiceSample.getFilePath());
        submitSampleButton.click();
        return new VoiceTalentPage(driver);
    }
}
