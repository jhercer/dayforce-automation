package dayforce.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.logging.Logger;

public class WaitConditions {
    private static final Logger LOGGER = Logger.getAnonymousLogger();
    public static ExpectedCondition<Boolean> urlContains(final String text) {
        return new ExpectedCondition<Boolean>() {
            private String currentUrl = "";

            @Override
            public Boolean apply(WebDriver driver) {
                currentUrl = driver.getCurrentUrl();
                System.out.println("current url " + currentUrl);
                return currentUrl.contains(text);
            }
        };
    }

    public static ExpectedCondition<Boolean> pageContains(final String text) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return driver.getPageSource().contains(text);
            }

            @Override
            public String toString() {
                return String.format("Page contains \"%s\"");
            }
        };
    }

    public static ExpectedCondition<WebElement> elementIsVisible(final By locator) {
        return new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                WebElement element = driver.findElement(locator);
                LOGGER.info(this::toString);
                return element.isDisplayed() ? element:null;
            }

            @Override
            public String toString() {
                return String.format("Element \"%s\" is visible", locator);
            }
        };
    }

    public static ExpectedCondition<WebElement> isEnabled(final By locator) {
        return new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                WebElement element = driver.findElement(locator);
                LOGGER.info(this::toString);
                return element.isEnabled() ? element:null;
            }

            @Override
            public String toString() {
                return String.format("Element \"%s\" is visible", locator);
            }
        };
    }

    public static ExpectedCondition<WebElement> elementIsInteractable(final By locator) {
        return new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                try {
                    WebElement element = driver.findElement(locator);
                    LOGGER.info(this::toString);
                    return element.isDisplayed() && element.isEnabled() ? element : null;
                } catch (Exception e) {
                    LOGGER.warning("Element not found or not interactable: " + locator);
                    return null;
                }
            }

            @Override
            public String toString() {
                return String.format("Element \"%s\" is interactable", locator);
            }
        };
    }
}
