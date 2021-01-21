package pages;

import elements.DropDown;
import elements.Input;
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

    @FindBy(xpath = "//input[@value='Male']")
    WebElement radioButtonMale;
    @FindBy(id = "dateOfBirth")
    WebElement fieldDateOfBirth;
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

    Input inputFirstName = new Input(driver, "firstName");
    Input inputLastName = new Input(driver, "lastName");
    Input inputContactNumber = new Input(driver, "contact_numbers_0_phoneNumber");
    Input inputEmail = new Input(driver, "email");
    Input inputDateOfBirth = new Input(driver, "dateOfBirth");

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

    public void fillFieldsAndSubmit(VoiceTalent voiceTalent) {
        wait.until(ExpectedConditions.visibilityOf(submitTalentButton));
        inputFirstName.write(voiceTalent.getFirstName());
        inputLastName.write(voiceTalent.getLastName());
        primaryLanguageDropdown.selectValue(voiceTalent.getPrimaryLanguage());
        inputDateOfBirth.write(voiceTalent.getDateOfBirth());
        fieldDateOfBirth.sendKeys(Keys.ENTER);
        countryCodeDropdown.selectValue(voiceTalent.getCountryCode());
        inputContactNumber.write(voiceTalent.getContactNumber());
        inputEmail.write(voiceTalent.getEmail());
        officeDropdown.selectValue(voiceTalent.getOffice());
        statusDropdown.selectValue(voiceTalent.getStatus());
        submitTalentButton.click();
    }

    public VoiceTalentPage create(VoiceTalent voiceTalent) {
        fillFieldsAndSubmit(voiceTalent);
        wait.until(ExpectedConditions.visibilityOf(messageSuccessSaving));
        return this;
    }

    public VoiceTalentPage update(VoiceTalent voiceTalent) {
        editButton.click();
        fillFieldsAndSubmit(voiceTalent);
        wait.until(ExpectedConditions.visibilityOf(messageSuccessUpdating));
        return this;
    }

    public VoiceTalentPage verify(VoiceTalent voiceTalent) {
        assertEquals(inputFirstName.getValue(), voiceTalent.getFirstName(), "Invalid first name");
        assertEquals(inputLastName.getValue(), voiceTalent.getLastName(), "Invalid last name");
        assertTrue(radioButtonMale.isSelected());
        assertEquals(primaryLanguageDropdown.getValue(), voiceTalent.getPrimaryLanguage(), "Invalid primary language");
        assertEquals(inputDateOfBirth.getValue(), voiceTalent.getDateOfBirth(), "Invalid date of birthday");
        assertEquals(countryCodeDropdown.getValue(), voiceTalent.getCountryCode(), "Invalid country code");
        assertEquals(inputContactNumber.getValue(), voiceTalent.getContactNumber(), "Invalid contact number");
        assertEquals(inputEmail.getValue(), voiceTalent.getEmail(), "Invalid email");
        assertEquals(officeDropdown.getValue(), voiceTalent.getOffice(), "Invalid office");
        assertEquals(statusDropdown.getValue(), voiceTalent.getStatus(), "Invalid status");
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
