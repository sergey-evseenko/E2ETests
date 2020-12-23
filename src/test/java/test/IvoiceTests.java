package test;

import models.VoiceSample;
import models.VoiceTalent;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class IvoiceTests extends BaseTest {

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
        VoiceTalent newVoiceTalent = VoiceTalentFactory.getVoiceTalent(),
                updatedVoiceTalent = VoiceTalentFactory.getVoiceTalent();
        VoiceSample voiceSample = VoiceSampleFactory.getVoiceSample();
        createVoiceTalentPage
                .openPage(url)
                .provideDataAndSave(newVoiceTalent)
                .verifySavedData(newVoiceTalent)
                .editAndSaveData(updatedVoiceTalent)
                .verifySavedData(updatedVoiceTalent)
                .editAndUploadVoiceSample(voiceSample)
                .verifyUploadedVoiceSample(voiceSample);
    }

    @Test
    public void voiceSampleTest() {
        VoiceSample newVoiceSample = VoiceSampleFactory.getVoiceSample(),
                updatedVoiceSample = VoiceSampleFactory.getVoiceSample();
        createVoiceSamplePage
                .openPage(url)
                .provideDataAndSave(newVoiceSample)
                .verifySavedData(newVoiceSample)
                .editAndSaveData(updatedVoiceSample)
                .verifySavedData(updatedVoiceSample);

    }


}
