package test;

import models.VoiceSample;
import models.VoiceSampleFactory;
import models.VoiceTalent;
import models.VoiceTalentFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class IvoiceTest extends BaseTest {

    VoiceTalent voiceTalent = VoiceTalentFactory.getVoiceTalent(),
            updatedVoiceTalent = VoiceTalentFactory.getVoiceTalent();
    VoiceSample voiceSample = VoiceSampleFactory.getVoiceSample("QA.mp3"),
            updatedVoiceSample = VoiceSampleFactory.getVoiceSample("QA.mp3");

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
                .create(voiceTalent)
                .verify(voiceTalent)
                .update(updatedVoiceTalent)
                .verify(updatedVoiceTalent)
                .editAndClickUpload()
                .upload(voiceSample)
                .save()
                .verifyUploadedVoiceSample(voiceSample);
    }

    @Ignore
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
