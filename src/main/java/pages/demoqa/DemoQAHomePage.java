package pages.demoqa;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Waits;

public class DemoQAHomePage {

    Waits waits;

    public DemoQAHomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        waits = new Waits(driver);
    }

    @FindBy(xpath = "//h5[text() = 'Elements']")
    WebElement elementBtn;
    @FindBy(xpath = "//h5[text() = 'Forms']")
    WebElement formsBtn;
    @FindBy(xpath = "//h5[text() = 'Alerts, Frame & Windows']")
    WebElement alertFrameWindowsBtn;

    public void clickOnElementBtn() {
        waits.click(elementBtn, 10);
    }

    public void clickOnFormsBtn() {
        waits.click(formsBtn, 10);
    }

    public void clickOnAlertFrameWindowsBtn() { waits.click(alertFrameWindowsBtn, 10); }
}
