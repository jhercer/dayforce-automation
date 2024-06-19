package dayforce.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class SharedDriver extends EventFiringWebDriver  {
    protected static WebDriver DRIVER;

    public SharedDriver(WebDriver driver) {
        super(driver);
    }

    protected static WebDriver getFirefoxDriver(DesiredCapabilities desiredCapabilities) {
        FirefoxOptions options = new FirefoxOptions().merge(desiredCapabilities);
        return new FirefoxDriver(options);
    }

    protected static WebDriver getChromeDriver(DesiredCapabilities desiredCapabilities) {
        ChromeOptions options = new ChromeOptions().merge(desiredCapabilities);
        return new ChromeDriver(options);
    }
}
