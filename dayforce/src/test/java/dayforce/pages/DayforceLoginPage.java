package dayforce.pages;

import dayforce.webdriver.LocalDriver;
import dayforce.webdriver.WaitConditions;
import org.openqa.selenium.By;

public class DayforceLoginPage extends AbstractPage {
    private final By inputCompany = By.id("txtCompanyName");
    private final By buttonLogin = By.cssSelector("#MainContent_loginUI_cmdLogin");
    private final By buttonSystemAdmin = By.xpath("//*[@id=\"Framework_UI_Form_RadioButton_0\"]");
    private final By buttonNext = By.xpath("//*[text()='Next']");

    public DayforceLoginPage(String pathArg, LocalDriver driverArg, int waitTimeInSecondsArg) {
        super(pathArg, driverArg, waitTimeInSecondsArg);
    }

    public void loginAs(String company, String user, String password) throws InterruptedException {
        find(inputCompany).sendKeys(company);
        pressTabAndType(user);
        pressTabAndType(password);
        find(buttonLogin).click();
        waitUntilTrueOrTimeout(WaitConditions.elementIsVisible(buttonSystemAdmin)).click();
        find(buttonNext).click();
    }
}
