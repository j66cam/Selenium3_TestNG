package utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Objects;

public class Waits {

    WebDriver driver;
    Wait<WebDriver> fWaits;
    WebDriverWait wait;
    Select select;

    public Waits(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//kendo-calendar-header/span[@class = 'k-button k-bare k-title']")
    WebElement datePickerHeaderLabel;

    private Wait<WebDriver> fluentWait(int waitingTime) {
        fWaits = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(waitingTime))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class)
                .ignoring(InvalidArgumentException.class)
                .ignoring(NoSuchWindowException.class);
        return fWaits;
    }

    public void click(WebElement webElement, int waitingTime) {
        Wait<WebDriver> fWait = fluentWait(waitingTime);
        fWait.until(ExpectedConditions.visibilityOf(webElement));
        fWait.until(ExpectedConditions.elementToBeClickable(webElement));
        webElement.click();
    }

    public void clickJavaScriptInjection(WebElement webElement) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", webElement);
    }

    public void putText(WebElement webElement, String text, int waitingTime) {
        Wait<WebDriver> fWait = fluentWait(waitingTime);
        fWait.until(ExpectedConditions.visibilityOf(webElement));
        fWait.until(ExpectedConditions.elementToBeClickable(webElement));
        webElement.clear();
        webElement.sendKeys(text);
    }

    public String getText(WebElement webElement, int waitingTime) {
        Wait<WebDriver> fWait = fluentWait(waitingTime);
        fWait.until(ExpectedConditions.visibilityOf(webElement));
        return webElement.getText();
    }

    public void visibilityOf(WebElement webElement, int waitingTime) {
        Wait<WebDriver> fWait = fluentWait(waitingTime);
        fWait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public boolean webElementIsDisplayed(WebElement webElement, int waitingTime) {
        Wait<WebDriver> fWait = fluentWait(waitingTime);
        fWait.until(ExpectedConditions.visibilityOf(webElement));
        return webElement.isDisplayed();
    }

    public void invisibilityOf(WebElement webElement, int waitingTime) {
        Wait<WebDriver> fWait = fluentWait(waitingTime);
        fWait.until(ExpectedConditions.invisibilityOf(webElement));
    }

    public void elementToDisappear(WebElement webElement) {
        while (webElement.isDisplayed()) {
            if (!webElement.isDisplayed()) {
                break;
            }
        }
    }

    public void webElementToBeDisabled(WebElement webElement, int waitingTime) {
        Wait<WebDriver> fWait = fluentWait(waitingTime);
        fWait.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(webElement)));
    }

    public boolean webElementIsDisabled(WebElement webElement, int waitingTime) {
        Wait<WebDriver> fWait = fluentWait(waitingTime);
        fWait.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(webElement)));
        return webElement.isEnabled();
    }

    public void pickSelectDropDownOptionByValue(WebElement webElement, String dropDownOption, int waitingTime) {
        Wait<WebDriver> fWait = fluentWait(waitingTime);
        fWait.until(ExpectedConditions.elementToBeClickable(webElement));
        select = new Select(webElement);
        select.selectByVisibleText(dropDownOption);
    }

    public void pickSelectDropDownOptionByIndex(WebElement webElement, int optionIndex, int waitingTime) {
        Wait<WebDriver> fWait = fluentWait(waitingTime);
        fWait.until(ExpectedConditions.elementToBeClickable(webElement));
        select = new Select(webElement);
        select.selectByIndex(optionIndex);
    }

    public void pickMultiSelectDropDownWithTypeHereAndSeveralOptions(WebElement webElement, String[] dropDownOptions, int waitingTime) {
        Wait<WebDriver> fWait = fluentWait(waitingTime);
        fWait.until(ExpectedConditions.elementToBeClickable(webElement));
        click(webElement, 30);
        if (Objects.equals(dropDownOptions[0], "Select all")) {
            click(driver.findElement(By.xpath("//button[contains(text(), 'Select all')]")), 30);
        } else {
            WebElement searchTxtBx = driver.findElement(By.xpath("//input[@placeholder = 'Type here to search...']"));
            for (int i = 0; i < dropDownOptions.length - 1; i++) {
                putText(searchTxtBx, dropDownOptions[i], 30);
                WebElement firstOption = driver.findElement(By.xpath("(//div[@class= 'multiSelectItem ng-scope vertical'])[1]"));
                click(firstOption, 30);
            }
        }
    }

    public void pickMultiSelectDropDownWithTypeHereAndOneOption(WebElement webElement, String dropDownOptions, int waitingTime) {
        Wait<WebDriver> fWait = fluentWait(waitingTime);
        fWait.until(ExpectedConditions.elementToBeClickable(webElement));
        click(webElement, 30);
        WebElement searchTxtBx = driver.findElement(By.xpath("//input[@placeholder = 'Type here to search...']"));
        putText(searchTxtBx, dropDownOptions, 30);
        WebElement firstOption = driver.findElement(By.xpath("(//div[@class= 'multiSelectItem ng-scope vertical'])[1]"));
        click(firstOption, 30);
    }

    public void pickMultiSelectDropDownWithSearch(WebElement webElement, String dropDownOptions, int waitingTime) {
        Wait<WebDriver> fWait = fluentWait(waitingTime);
        fWait.until(ExpectedConditions.elementToBeClickable(webElement));
        click(webElement, 30);
        WebElement searchTxtBx = driver.findElement(By.xpath("//input[@placeholder = 'Search...']"));
        putText(searchTxtBx, dropDownOptions, 30);
        WebElement firstOption = driver.findElement(By.xpath("(//div[@class= 'multiSelectItem ng-scope vertical'])[1]"));
        click(firstOption, 30);
    }

    public void pickKendoDropDown(WebElement kendoDDWebElement, String dropDownText, int waitingTime) {
        Wait<WebDriver> fWait = fluentWait(waitingTime);
        fWait.until(ExpectedConditions.elementToBeClickable(kendoDDWebElement));
        click(kendoDDWebElement, 30);
        click(driver.findElement(By.xpath("//li[@kendodropdownsselectable and text() = '" + dropDownText + "']")), 30);
    }

    public void pickMultipleKendoDropDown(WebElement kendoDDWebElement, String[] dropDownText, int waitingTime) {
        Wait<WebDriver> fWait = fluentWait(waitingTime);
        fWait.until(ExpectedConditions.elementToBeClickable(kendoDDWebElement));
        for (String s : dropDownText) {
            click(kendoDDWebElement, 30);
            click(driver.findElement(By.xpath("//li[@kendodropdownsselectable and text() = '" + s + "']")), 30);
        }
    }

    public void pickKendoDropDownWithAllOption(WebElement kendoDDWebElement, String dropDownText, int waitingTime) {
        Wait<WebDriver> fWait = fluentWait(waitingTime);
        fWait.until(ExpectedConditions.elementToBeClickable(kendoDDWebElement));
        click(kendoDDWebElement, 30);
        click(driver.findElement(By.xpath("//li[text() = 'All']")), 30);
        click(driver.findElement(By.xpath("//li[text() = '" + dropDownText + "']")), 30);
    }

    public void pickKendoDropDownWithNAOption(WebElement kendoDDWebElement, String dropDownText, int waitingTime) {
        Wait<WebDriver> fWait = fluentWait(waitingTime);
        fWait.until(ExpectedConditions.elementToBeClickable(kendoDDWebElement));
        click(kendoDDWebElement, 30);
        click(driver.findElement(By.xpath("//li[text() = '" + dropDownText + "']")), 30);
    }

    public void setDatePickerOnTextBox(WebElement webElement, String date, int waitingTime) {
        Wait<WebDriver> fWait = fluentWait(waitingTime);
        fWait.until(ExpectedConditions.visibilityOf(webElement));
        fWait.until(ExpectedConditions.elementToBeClickable(webElement));
        click(webElement, 30);
        webElement.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        webElement.sendKeys(Keys.DELETE);
        webElement.sendKeys(date);
    }

    public void setKendoDatePicker(WebElement webElement, String date, int waitingTime) {
        String[] myDate = date.split("/");
        String day = myDate[0];
        String month = myDate[1];
        String year = myDate[2];

        click(webElement, waitingTime);
        click(datePickerHeaderLabel, waitingTime);
        click(datePickerHeaderLabel, waitingTime);

        WebElement selectYear = driver.findElement(By.xpath("//span[text() = '" + year + "']"));
        click(selectYear, waitingTime);

        WebElement selectMonth = driver.findElement(By.xpath("//td[@title = '" + year + " " + month + "']"));
        click(selectMonth, waitingTime);

        WebElement selectDay = driver.findElement(By.xpath("//td[contains(@title, '" + month + "')]/span[text()='" + day + "']"));
        click(selectDay, waitingTime);
    }

    public void removeReadOnlyForInputDateAndSetDate(WebElement element, String date) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].removeAttribute('readonly','readonly');", element);
        putText(element, date, 30);
    }
}
