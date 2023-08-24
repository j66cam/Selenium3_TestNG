package browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utilities.ConfigFileReader;

import java.io.File;

public class DriverInstance {

    public WebDriver driver;

    @BeforeMethod
    public void iniciateDriverInstance() throws Exception {
        switch (ConfigFileReader.GetConfigReader("BROWSER").toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("–disable-notifications", "disable-infobars");
                chromeOptions.addExtensions(new File(System.getProperty("user.dir") + "\\config\\uBlock-Origin.crx"));
                driver = new ChromeDriver(chromeOptions);
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default:
                throw new Exception("Incorrect Browser");
        }
        driver.manage().window().maximize();
        driver.get(ConfigFileReader.GetConfigReader("URL"));
    }

    @AfterMethod
    public void closeDriverInstance() {
        driver.quit();
    }
}
