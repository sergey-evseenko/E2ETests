package test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class IvoiceTests extends BaseTest {

    @BeforeClass
    @Test(description = "Login with valid login/pass")
    public void validLogin() {
        loginPage
                .openPage(url)
                .clickLoginButton()
                .provideEmailAndPass(id, password)
                .isPageOpened();
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
