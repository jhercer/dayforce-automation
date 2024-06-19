package dayforce.steps;

import dayforce.config.Properties;
import dayforce.pages.Bing;
import dayforce.pages.Google;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;

public class SeleniumSteps {

    @Autowired
    private Bing bingPage;
    @Autowired
    private Properties properties;

    @Given("selenium is called")
    public void selenium_is_called() {
       bingPage.goTo();
    }
}
