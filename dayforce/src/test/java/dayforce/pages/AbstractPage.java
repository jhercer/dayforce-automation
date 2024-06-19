package dayforce.pages;

import dayforce.webdriver.LocalDriver;
import dayforce.webdriver.WaitConditions;
import lombok.Data;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Data
public abstract class AbstractPage {
    private final LocalDriver driver;
    private final int waitTimeInSeconds;
    private String path;
    private Actions actions;

    public AbstractPage(String pathArg, LocalDriver driverArg, int waitTimeInSecondsArg) {
        path = pathArg;
        driver = driverArg;
        waitTimeInSeconds = waitTimeInSecondsArg;
        this.actions = new Actions(driver);
    }

    protected <T> T waitUntilTrueOrTimeout(ExpectedCondition<T> isTrue) {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(waitTimeInSeconds))
                .ignoring(StaleElementReferenceException.class);
        try {
            return wait.until(isTrue);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    protected WebElement find(By locator) {
        try {
            return driver.findElement(locator);
        } catch (NoSuchElementException nsex) {
            throw new NoSuchElementException(nsex.getMessage());
        }
    }

    public void goTo() {
        driver.navigate().to(path);
        ensureIsCurrent();
    }

    public void ensureIsCurrent() {
        waitUntilTrueOrTimeout(WaitConditions.urlContains(path));
    }

    public void setText(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    public void submit(WebElement element) {
        element.submit();
    }

    public void setText(By locator, String text) {
       this.actions.moveToElement(find(locator)).click().sendKeys(text).perform();
    }

    public void pressTabAndType(String text) {
        this.actions.sendKeys(Keys.TAB).sendKeys(text).perform();
    }

}
