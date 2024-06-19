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

    private static final String AUTHENTICATE_PAYLOAD = "{\n" +
            "    \"client_id\": \"494EB21D42A14B79B4A5346B1A6BC89E\",\n" +
            "    \"client_secret\": \"1d8cfda059d7ee7702cb24c3d6c4e445550a8f14e8bdb7817aea32481f8e7ffb\"\n" +
            "}\n";

    private static final String TEST_EXEC_PAYLOAD = "{\n" +
            "    \"testExecutionKey\": \"MUP-378\",\n" +
            "    \"info\": {\n" +
            "        \"summary\": \"Execution of automated tests for release v1.3\",\n" +
            "        \"description\": \"main container\",\n" +
            "        \"startDate\": \"2014-08-30T11:47:35+01:00\",\n" +
            "        \"finishDate\": \"2014-08-30T11:53:00+01:00\"\n" +
            "    },\n" +
            "    \"tests\": [\n" +
            "        {\n" +
            "            \"testKey\": \"MUP-377\",\n" +
            "            \"start\": \"2014-08-30T11:47:35+01:00\",\n" +
            "            \"finish\": \"2014-08-30T11:50:56+01:00\",\n" +
            "            \"comment\": \"Successful execution\",\n" +
            "            \"status\": \"FAILED\"\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    @Autowired
    private Client client;

    @After(value = "@xray", order = 1000)
    @SneakyThrows
    public void publishResults(Scenario scenario) {
        // get token
        client.getCONTEXT().setUrl(BASE_URL);
        client.getCONTEXT().setPayload(AUTHENTICATE_PAYLOAD);
        client.post("/api/v2/authenticate", null, null, null);
        // map test execution
        ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
        TestExecution testExecution = mapper.readValue(TEST_EXEC_PAYLOAD, TestExecution.class);
        testExecution.getInfo().setStartDate(getCurrentDateTime());
        testExecution.getTests().get(0).setStart(getCurrentDateTime());
        // get status
        if("PASSED".equals(scenario.getStatus())) {
            testExecution.getTests().get(0).setStatus(String.valueOf(TestExecution.Test.Status.PASSED));
            testExecution.getTests().get(0).setFinish(getCurrentDateTime());
        } else {
            testExecution.getTests().get(0).setStatus(String.valueOf(TestExecution.Test.Status.FAILED));
        }

        client.getCONTEXT().setPayload(testExecution);
        client.post("/api/v2/import/execution", client.getCONTEXT().getResponse().asString().replace("\"", ""));
    }

    public static String getCurrentDateTime() {
        ZonedDateTime now = ZonedDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
        return now.format(formatter);
    }
}
