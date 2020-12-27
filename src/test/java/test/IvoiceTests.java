package test;

import models.VoiceSample;
import models.VoiceTalent;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class IvoiceTests extends BaseTest {

    String file = "/Users/sergeyevseenko/IdeaProjects/Ivoice/src/test/resources/QA.mp3";

    VoiceTalent voiceTalent = VoiceTalentFactory.getVoiceTalent("Russian", "BELARUS (375)", "Berlin", "Active", "June 07 1989"),
            updatedVoiceTalent = VoiceTalentFactory.getVoiceTalent("English", "ALBANIA (355)", "Amasterdam", "Inactive", "June 08 1990");
    //String voiceTalentName = updatedVoiceTalent.getFirstName() + " " + updatedVoiceTalent.getLastName();
    String voiceTalentName = "John Doe";

    VoiceSample voiceSample = VoiceSampleFactory.getVoiceSample(voiceTalentName, "Russian", "Comedy", "21-30", "Low-Pitched", "Energetic", "Song", file),
            updatedVoiceSample = VoiceSampleFactory.getVoiceSample(voiceTalentName, "English", "Animation", "31-50", "High-Pitched", "Corporate", "Song", file);

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
        createVoiceTalentPage
                .openPage(url)
                .provideDataAndSave(voiceTalent)
                .verifySavedData(voiceTalent)
                .editAndSaveData(updatedVoiceTalent)
                .verifySavedData(updatedVoiceTalent)
                .editAndUploadVoiceSample(voiceSample)
                .verifyUploadedVoiceSample(voiceSample);
    }

    @Test
    public void voiceSampleTest() {
        createVoiceSamplePage
                .openPage(url)
                .provideDataAndSave(voiceSample)
                .verifySavedData(voiceSample)
                .editAndSaveData(updatedVoiceSample)
                .verifySavedData(updatedVoiceSample);

    }


}
