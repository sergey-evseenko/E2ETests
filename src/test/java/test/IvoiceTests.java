package test;

import models.VoiceSample;
import models.VoiceTalent;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;


public class IvoiceTests extends BaseTest {

    VoiceTalent voiceTalent = VoiceTalentFactory.getVoiceTalent("Afar", "ANGOLA (244)", "Amsterdam", "Active", "June 07 1989"),
            updatedVoiceTalent = VoiceTalentFactory.getVoiceTalent("Afrikaans", "ALGERIA (213)", "Bangkok", "Inactive", "June 08 1990");
    //String voiceTalentName = updatedVoiceTalent.getFirstName() + " " + updatedVoiceTalent.getLastName();
    String voiceTalentName = "John Doe";

    VoiceSample voiceSample = VoiceSampleFactory.getVoiceSample(voiceTalentName, "Afar", "Action", "0-12", "High-Pitched", "Corporate", "Song", filePath, "Manual"),
            updatedVoiceSample = VoiceSampleFactory.getVoiceSample(voiceTalentName, "Afrikaans", "Animation", "13-19", "Low-Pitched", "Energetic", "Song", filePath, "Manual");

    @BeforeClass
    public void validLogin() {
        loginPage
                .openPage(url)
                .clickLoginButton()
                .provideEmailAndPass(id, password)
                .isPageOpened();
    }

    @Test
    public void voiceTalentTest() {
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

    @Ignore
    @Test
    public void voiceSampleTest() {
        voiceSamplePage
                .openPage(url)
                .createAndSave(voiceSample)
                .verify(voiceSample)
                .updateAndSave(updatedVoiceSample)
                .verify(updatedVoiceSample);
    }

}
