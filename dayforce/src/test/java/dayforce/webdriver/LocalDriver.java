package dayforce.webdriver;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class LocalDriver extends SharedDriver {

    public LocalDriver(String browser, boolean isProxyEnabled, Proxy proxy) {
        super(getDriver(browser, isProxyEnabled, proxy));
    }

    private static WebDriver getDriver(String browser, boolean isProxyEnabled, Proxy proxy) {
        if (DRIVER != null) return DRIVER;
        DesiredCapabilities capabilities = new DesiredCapabilities();
        if (isProxyEnabled && proxy != null)
            capabilities.setCapability(CapabilityType.PROXY, proxy);

        DRIVER = getBrowserDriver(browser, capabilities);

        return DRIVER;
    }


    private static WebDriver getBrowserDriver(String browser, DesiredCapabilities desiredCapabilities) {
        switch (browser) {
            case "FIREFOX":
                return getFirefoxDriver(desiredCapabilities);
            case "GOOGLECHROME":
                return getChromeDriver(desiredCapabilities);
            default:
                throw new RuntimeException(String.format("browser is not supported %s", browser));
        }
    }
}
