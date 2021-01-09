package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.*;
import utils.PropertyManager;

import java.io.File;
import java.util.concurrent.TimeUnit;

public abstract class BaseTest {
    PropertyManager props;
    LoginPage loginPage;
    GoogleSignInPage googleSignInPage;
    VoiceTalentPage voiceTalentPage;
    VoiceSamplePage voiceSamplePage;
    UploadPage uploadPage;
    MainPage mainPage;
    String id, password, url;
    String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "QA.mp3";

    private WebDriver driver;

    @BeforeClass(description = "Opening Chrome Driver")
    public void setDriver() {

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(false);
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();


        loginPage = new LoginPage(driver);
        googleSignInPage = new GoogleSignInPage(driver);
        voiceTalentPage = new VoiceTalentPage(driver);
        voiceSamplePage = new VoiceSamplePage(driver);
        uploadPage = new UploadPage(driver);
        mainPage = new MainPage(driver);
        props = new PropertyManager();
        id = props.get("id");
        password = props.get("password");
        url = props.get("url");
    }


    @AfterClass(description = "Closing Chrome Driver", alwaysRun = true)
    public void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

}
