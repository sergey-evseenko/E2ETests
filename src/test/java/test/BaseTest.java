package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.*;
import utils.CapabilitiesGenerator;
import utils.PropertyManager;

import java.util.concurrent.TimeUnit;

public abstract class BaseTest {
    PropertyManager props;
    LoginPage loginPage;
    GoogleSignInPage googleSignInPage;
    VoiceTalentsPage voiceTalentsPage;
    CreateVoiceTalentPage createVoiceTalentPage;
    CreateVoiceSamplePage createVoiceSamplePage;
    MainPage mainPage;
    String id;
    String password;
    String url;

    private WebDriver driver;

    @BeforeClass(description = "Opening Chrome Driver")
    public void setDriver() {
        driver = new ChromeDriver(CapabilitiesGenerator.getChromeOptions());
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        googleSignInPage = new GoogleSignInPage(driver);
        voiceTalentsPage = new VoiceTalentsPage(driver);
        createVoiceTalentPage = new CreateVoiceTalentPage(driver);
        createVoiceSamplePage = new CreateVoiceSamplePage(driver);
        mainPage = new MainPage(driver);
        props = new PropertyManager();
        id = props.get("id");
        password = props.get("password");
        url = props.get("url");
    }

    @AfterClass(description = "Closing Chrome Driver", alwaysRun = true)
    public void closeDriver() {
        driver.quit();
    }

}
