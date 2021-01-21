package pages;

import elements.DropDown;
import models.VoiceTalent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class VoiceTalentPage extends BasePage {


    @FindBy(id = "firstName")
    WebElement inputFirstName;
    @FindBy(id = "lastName")
    WebElement inputLastName;
    @FindBy(xpath = "//input[@value='Male']")
    WebElement radioButtonMale;
    @FindBy(id = "dateOfBirth")
    WebElement inputDateOfBirth;
    @FindBy(id = "contact_numbers_0_phoneNumber")
    WebElement inputContactNumber;
    @FindBy(id = "email")
    WebElement inputEmail;
    @FindBy(xpath = "//span[contains(text(), 'The record was saved successfully.')]")
    WebElement messageSuccessSaving;
    @FindBy(xpath = "//span[contains(text(), 'The record was updated successfully.')]")
    WebElement messageSuccessUpdating;
    @FindBy(xpath = "//span[contains(text(), 'Edit')]")
    WebElement editButton;
    @FindBy(xpath = "//span[contains(text(), 'Upload')]")
    WebElement uploadButton;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement submitTalentButton;
    DropDown statusDropdown = new DropDown(driver, "status");
    DropDown officeDropdown = new DropDown(driver, "office");
    DropDown primaryLanguageDropdown = new DropDown(driver, "primaryLanguage");
    DropDown countryCodeDropdown = new DropDown(driver, "contact_numbers_0_phoneCountryCode");

    public VoiceTalentPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public VoiceTalentPage isPageOpened() {
        wait.until(ExpectedConditions.elementToBeClickable(submitTalentButton));
        return this;
    }

    @Override
    public VoiceTalentPage openPage(String url) {
        driver.get(url + "voicetalents/talent/");
        isPageOpened();
        return this;
    }

    public VoiceTalentPage createAndSave(VoiceTalent voiceTalent) {
        inputFirstName.sendKeys(voiceTalent.getFirstName());
        inputLastName.sendKeys(voiceTalent.getLastName());
        primaryLanguageDropdown.selectValue(voiceTalent.getPrimaryLanguage());
        inputDateOfBirth.sendKeys(voiceTalent.getDateOfBirth(), Keys.ENTER);
        countryCodeDropdown.selectValue(voiceTalent.getCountryCode());
        inputContactNumber.sendKeys(voiceTalent.getContactNumber());
        inputEmail.sendKeys(voiceTalent.getEmail());
        officeDropdown.selectValue(voiceTalent.getOffice());
        statusDropdown.selectValue(voiceTalent.getStatus());
        submitTalentButton.click();
        wait.until(ExpectedConditions.visibilityOf(messageSuccessSaving));
        return this;
    }

    public VoiceTalentPage verify(VoiceTalent voiceTalent) {
        assertEquals(inputFirstName.getAttribute("value"), voiceTalent.getFirstName(), "Invalid first name");
        assertEquals(inputLastName.getAttribute("value"), voiceTalent.getLastName(), "Invalid last name");
        assertTrue(radioButtonMale.isSelected());
        assertEquals(primaryLanguageDropdown.getValue(), voiceTalent.getPrimaryLanguage(), "Invalid primary language");
        assertEquals(inputDateOfBirth.getAttribute("value"), voiceTalent.getDateOfBirth(), "Invalid date of birthday");
        assertEquals(countryCodeDropdown.getValue(), voiceTalent.getCountryCode(), "Invalid country code");
        assertEquals(inputContactNumber.getAttribute("value"), voiceTalent.getContactNumber(), "Invalid contact number");
        assertEquals(inputEmail.getAttribute("value"), voiceTalent.getEmail(), "Invalid email");
        assertEquals(officeDropdown.getValue(), voiceTalent.getOffice(), "Invalid office");
        assertEquals(statusDropdown.getValue(), voiceTalent.getStatus(), "Invalid status");
        return this;
    }

    public VoiceTalentPage updateAndSave(VoiceTalent voiceTalent) {
        editButton.click();
        inputFirstName.sendKeys(Keys.chord(Keys.COMMAND, "a"), voiceTalent.getFirstName());
        inputLastName.sendKeys(Keys.chord(Keys.COMMAND, "a"), voiceTalent.getLastName());
        primaryLanguageDropdown.selectValue(voiceTalent.getPrimaryLanguage());
        inputDateOfBirth.sendKeys(Keys.chord(Keys.COMMAND, "a"), voiceTalent.getDateOfBirth(), Keys.ENTER);
        countryCodeDropdown.selectValue(voiceTalent.getCountryCode());
        inputContactNumber.sendKeys(Keys.chord(Keys.COMMAND, "a"), voiceTalent.getContactNumber());
        inputEmail.sendKeys(Keys.chord(Keys.COMMAND, "a"), voiceTalent.getEmail());
        officeDropdown.selectValue(voiceTalent.getOffice());
        statusDropdown.selectValue(voiceTalent.getStatus());
        submitTalentButton.click();
        wait.until(ExpectedConditions.visibilityOf(messageSuccessUpdating));
        return this;
    }

    public UploadModal editAndClickUpload() {
        editButton.click();
        uploadButton.click();
        return new UploadModal(driver);
    }

    public VoiceTalentPage save() {
        submitTalentButton.click();
        wait.until(ExpectedConditions.visibilityOf(messageSuccessUpdating));
        return this;
    }

    public VoiceTalentPage verifyUploadedVoiceSample() {
        List<WebElement> listOfVoiceSamples = driver.findElements(By.cssSelector(".ant-table-row.ant-table-row-level-0"));
        assertEquals(listOfVoiceSamples.size(), 1, "Voice sample was uploaded incorrectly");
        return this;
    }

}
