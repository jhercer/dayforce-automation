package dayforce.pages;

import dayforce.webdriver.LocalDriver;
import dayforce.webdriver.WaitConditions;
import org.openqa.selenium.By;

public class Google extends AbstractPage {
    private final By searchTextBox = By.name("q");

    public Google(String pathArg, LocalDriver driverArg, int waitTimeInSecondsArg) {
        super(pathArg, driverArg, waitTimeInSecondsArg);
    }

    public void search(String text) {
        waitUntilTrueOrTimeout(WaitConditions.elementIsVisible(searchTextBox));
        setText(find(searchTextBox), text);
        submit(find(searchTextBox));
    }

    public void quit() {
        getDriver().quit();
    }
}
