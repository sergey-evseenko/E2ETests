package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import pages.*;
import utils.PropertyManager;
import utils.TestListener;

import java.util.concurrent.TimeUnit;

@Listeners(TestListener.class)
public abstract class BaseTest {
    PropertyManager props;
    LoginPage loginPage;
    GoogleSignInModal googleSignInModal;
    VoiceTalentPage voiceTalentPage;
    VoiceSamplePage voiceSamplePage;
    UploadModal uploadModal;
    MainPage mainPage;
    String id, password, url, timeout, headless;

    private WebDriver driver;

    @BeforeClass(description = "Opening Chrome Driver")
    public void setDriver(ITestContext testContext) {

        props = new PropertyManager();
        id = props.get("id");
        password = props.get("password");
        url = props.get("url");
        timeout = props.get("timeout");
        headless = props.get("headless");

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(Boolean.parseBoolean(headless));
        options.addArguments("--user-agent=\"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.141 Safari/537.36\"");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(timeout), TimeUnit.SECONDS);
        driver.manage().window().maximize();
        testContext.setAttribute("driver", driver);

        loginPage = new LoginPage(driver);
        googleSignInModal = new GoogleSignInModal(driver);
        voiceTalentPage = new VoiceTalentPage(driver);
        voiceSamplePage = new VoiceSamplePage(driver);
        uploadModal = new UploadModal(driver);
        mainPage = new MainPage(driver);
    }


    @AfterClass(description = "Closing Chrome Driver", alwaysRun = true)
    public void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

}
