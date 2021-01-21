package test;

import models.VoiceSample;
import models.VoiceSampleFactory;
import models.VoiceTalent;
import models.VoiceTalentFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class IvoiceTest extends BaseTest {

    VoiceTalent voiceTalent = VoiceTalentFactory.getVoiceTalent("Afar", "Albania (355)", "Amsterdam", "Active", "June 07 1989"),
            updatedVoiceTalent = VoiceTalentFactory.getVoiceTalent("Afrikaans", "Algeria (213)", "Bangkok", "Inactive", "June 08 1990");
    //String voiceTalentName = updatedVoiceTalent.getFirstName() + " " + updatedVoiceTalent.getLastName();
    String voiceTalentName = "John Doe";

    VoiceSample voiceSample = VoiceSampleFactory.getVoiceSample(voiceTalentName, "Afar", "Action", "0-12", "High-Pitched", "Corporate", "Song", "QA.mp3", "Manual"),
            updatedVoiceSample = VoiceSampleFactory.getVoiceSample(voiceTalentName, "Afrikaans", "Animation", "13-19", "Low-Pitched", "Energetic", "Song", "QA.mp3", "Manual");

    @BeforeClass
    public void login() {
        loginPage
                .openPage(url)
                .clickLoginButton()
                .provideEmailAndPass(id, password)
                .isPageOpened();
    }

    @Test(description = "Voice talent creation/updating/uploading voice sample")
    public void voiceTalentCanBeCreatedAndUpdated() {
        voiceTalentPage
                .openPage(url)
                .createAndSave(voiceTalent)
                .verify(voiceTalent)
                .updateAndSave(updatedVoiceTalent)
                .verify(updatedVoiceTalent)
                .editAndClickUpload()
                .upload(voiceSample)
                .save()
                .verifyUploadedVoiceSample();
    }

    @Test(description = "Voice sample creation/updating")
    public void voiceSampleCanBeCreatedAndUpdated() {
        voiceSamplePage
                .openPage(url)
                .createAndSave(voiceSample)
                .verify(voiceSample)
                .updateAndSave(updatedVoiceSample)
                .verify(updatedVoiceSample);
    }
}
