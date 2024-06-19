package dayforce.hooks;

import com.fasterxml.jackson.databind.ObjectMapper;
import dayforce.entities.TestExecution;
import dayforce.rest.Client;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class XrayHooks {

    private static final String BASE_URL = "https://xray.cloud.getxray.app";
    private static final String AUTHENTICATE_PAYLOAD = generateAuthenticatePayload();
    private static final String TEST_EXEC_PAYLOAD = generateTestExecPayload();

    @Autowired
    private Client client;

    @After(value = "@xray", order = 1000)
    @SneakyThrows
    public void publishResults(Scenario scenario) {
        authenticateClient();
        String currentDateTime = getCurrentDateTime();
        TestExecution testExecution = mapTestExecution(currentDateTime, currentDateTime); // Assuming start and end are the same for simplicity
        updateTestExecutionStatus(scenario, testExecution);
        postTestExecution(testExecution);
    }

    private void authenticateClient() {
        client.getCONTEXT().setUrl(BASE_URL);
        client.getCONTEXT().setPayload(AUTHENTICATE_PAYLOAD);
        client.post("/api/v2/authenticate", null, null, null);
    }

    @SneakyThrows
    private TestExecution mapTestExecution(String startDate, String endDate) {
        ObjectMapper mapper = new ObjectMapper();
        TestExecution testExecution = mapper.readValue(TEST_EXEC_PAYLOAD, TestExecution.class);
        testExecution.getInfo().setStartDate(startDate);
        testExecution.getInfo().setFinishDate(endDate);
        testExecution.getTests().get(0).setStart(startDate);
        testExecution.getTests().get(0).setFinish(endDate);
        return testExecution;
    }

    private void updateTestExecutionStatus(Scenario scenario, TestExecution testExecution) {
        String status = scenario.getStatus().equals("PASSED") ?
                TestExecution.Test.Status.PASSED.name() :
                TestExecution.Test.Status.FAILED.name();
        testExecution.getTests().get(0).setStatus(status);
        if ("PASSED".equals(status)) {
            testExecution.getTests().get(0).setFinish(getCurrentDateTime());
        }
    }

    private void postTestExecution(TestExecution testExecution) {
        client.getCONTEXT().setPayload(testExecution);
        client.post("/api/v2/import/execution", client.getCONTEXT().getResponse().asString().replace("\"", ""));
    }

    private static String getCurrentDateTime() {
        return ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX"));
    }

    private static String generateAuthenticatePayload() {
        return "{ \"client_id\": \"494EB21D42A14B79B4A5346B1A6BC89E\", \"client_secret\": \"1d8cfda059d7ee7702cb24c3d6c4e445550a8f14e8bdb7817aea32481f8e7ffb\" }";
    }

    private static String generateTestExecPayload() {
        return "{ \"testExecutionKey\": \"MUP-378\", \"info\": { \"summary\": \"Execution of automated tests for release v1.3\", \"description\": \"main container\", \"startDate\": \"\", \"finishDate\": \"\" }, \"tests\": [ { \"testKey\": \"MUP-377\", \"start\": \"\", \"finish\": \"\", \"comment\": \"Successful execution\", \"status\": \"FAILED\" } ] }";
    }
}
