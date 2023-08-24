package testdemoqa;

import browser.DriverInstance;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.demoqa.DemoQAHomePage;
import pages.demoqa.elementstab.TextBoxPage;

public class TC_001_TextBox extends DriverInstance {

    DemoQAHomePage demoQAHomePage;
    TextBoxPage textBoxPage;

    @Test
    public void fillTexBoxInputs() {
        demoQAHomePage = new DemoQAHomePage(driver);
        textBoxPage = new TextBoxPage(driver);

        demoQAHomePage.clickOnElementBtn();
        textBoxPage.clickOnTextBoxTab();

        String fullName = "Leon Ramirez";
        String email = "apodq@hotmail.com";
        String currentAddress = "crra 51b 95 95";
        String permanentAddress = "crra 51b 95 95";

        textBoxPage.fillInputsAndSubmit(fullName, email, currentAddress, permanentAddress);
        Assert.assertEquals(textBoxPage.nameValidationOutputLb(), "Name:" + fullName);
        Assert.assertEquals(textBoxPage.emailValidationOutputLb(), "Email:" + email);
        Assert.assertEquals(textBoxPage.currentAddressValidationOutputLb(), "Current Address :" + currentAddress);
        Assert.assertEquals(textBoxPage.permanentAddressValidationOutputLb(), "Permananet Address :" + permanentAddress);
    }
}
