package test;

import models.VoiceTalent;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.UUID;

public class IvoiceTests extends BaseTest {


    VoiceTalent voiceTalent = new VoiceTalent(
            UUID.randomUUID().toString(),
            UUID.randomUUID().toString(),
            UUID.randomUUID().toString() + "@gmail.com",
            UUID.randomUUID().toString(),
            "1",
            "AD 376",
            "Afar"
    );

    VoiceTalent voiceTalentUpdated = new VoiceTalent(
            UUID.randomUUID().toString(),
            UUID.randomUUID().toString(),
            UUID.randomUUID().toString() + "@gmail.com",
            UUID.randomUUID().toString(),
            "2",
            "RU 7",
            "Abkhazian"
    );

    @BeforeClass
    public void validLogin() {
        loginPage
                .openPage(url)
                .clickLoginButton()
                .provideEmailAndPass(id, password)
                .isPageOpened();
    }

    @Test
    public void createVoiceTalent() {
        createVoiceTalentPage
                .openPage(url)
                .provideDataAndSave(voiceTalent)
                .verifySavedData(voiceTalent)
                .editAndSaveData(voiceTalentUpdated);
        //.verifySavedData(voiceTalentUpdated)
        //.editAndUploadVoiceSamle(path)
        //.verifyUloadedVoiceSample;


    }

    @Test
    public void addVoiceTalents() {
        voiceTalentsPage
                .openPage(url);
    }

    @Test(dependsOnMethods = "addVoiceTalents")
    public void searchVoiceTalents() {
        voiceTalentsPage
                .openPage(url);
    }

    @Test
    public void filterVoiceTalents() {
        voiceTalentsPage
                .openPage(url);
    }


}
