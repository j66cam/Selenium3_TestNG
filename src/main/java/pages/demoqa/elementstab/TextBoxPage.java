package pages.demoqa.elementstab;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Waits;

public class TextBoxPage {

    Waits waits;

    public TextBoxPage(WebDriver driver) {
        waits = new Waits(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "main-header")
    WebElement elementsLb;
    @FindBy(xpath = "//span[text() = 'Text Box']")
    WebElement textBoxTab;
    @FindBy(id = "userName")
    WebElement fullNameTxtBx;
    @FindBy(id = "userEmail")
    WebElement emailTxtBx;
    @FindBy(id = "currentAddress")
    WebElement currentAddressTxtBx;
    @FindBy(id = "permanentAddress")
    WebElement permanentAddressTxtBx;
    @FindBy(xpath = "//button[@id='submit']")
    WebElement submitBtn;
    @FindBy(id = "name")
    WebElement nameLb;
    @FindBy(id = "email")
    WebElement emailLb;
    @FindBy(xpath = "//p[@id='currentAddress']")
    WebElement currentAddressLb;
    @FindBy(xpath = "//p[@id='permanentAddress']")
    WebElement permanentAddressLb;

    public void clickOnTextBoxTab() {
        waits.visibilityOf(elementsLb, 10);
        waits.click(textBoxTab, 10);
    }

    public void fillInputsAndSubmit(String fullName, String email, String currentAddress, String permanentAddress) {
        waits.putText(fullNameTxtBx, fullName, 30);
        waits.putText(emailTxtBx, email, 30);
        waits.putText(currentAddressTxtBx, currentAddress, 30);
        waits.putText(permanentAddressTxtBx, permanentAddress, 30);
        waits.click(submitBtn, 30);
    }

    public String nameValidationOutputLb() {
        return nameLb.getText();
    }

    public String emailValidationOutputLb() {
        return emailLb.getText();
    }

    public String currentAddressValidationOutputLb() {
        return currentAddressLb.getText();
    }

    public String permanentAddressValidationOutputLb() {
        return permanentAddressLb.getText();
    }
}
