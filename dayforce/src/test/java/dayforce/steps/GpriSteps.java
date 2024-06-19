package dayforce.steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import dayforce.config.Properties;
import dayforce.gpri.domain.PayrunImportWrapper;
import dayforce.gpri.domain.ProcessResultsWrapper;
import dayforce.pages.DayforceLoginPage;
import dayforce.rest.Client;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class GpriSteps {
    @Autowired
    private Client client;
    @Autowired
    private Properties properties;
    @Autowired
    private DayforceLoginPage loginPage;
    private PayrunImportWrapper payrunImportWrapper;

    @Given("a gpri")
    public void a_gpri(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE);
        payrunImportWrapper = mapper.readValue(json, PayrunImportWrapper.class);
    }

    @When("I call a post")
    public void i_call_a_post() {
        // set the base url
        client.getCONTEXT().setUrl(properties.getKafkaBaseUrl());
        // set the payload
        client.getCONTEXT().setPayload(payrunImportWrapper);
        // invoke a post with basic auth
        client.post("/saas/gpri/qaextx2_1615501", properties.getKafkaUser(), properties.getKafkaPsw(), null);
    }
    @Then("a job must been queued")
    public void a_job_must_been_queued() {
        // extract response
        String backgroundMessageQueued = client.getCONTEXT().getResponse().jsonPath().getObject("$", ProcessResultsWrapper.class)
                .getProcessResults()
                .get(0)
                .getMessage();
        // conduct assertion
        Assert.assertEquals("message differ -",
                "Global Payrun Import background job has been queued for this request.", backgroundMessageQueued);
    }

    @And("I login into DayForce as an admin")
    public void dayForceLogin() throws InterruptedException {
        // navigate to Dayforces
        loginPage.goTo();
        // log into dayforce
        loginPage.loginAs(
                properties.getDayforceCompany(),
                properties.getDayforceUser(),
                properties.getDayforcePassword()
        );
    }
}
