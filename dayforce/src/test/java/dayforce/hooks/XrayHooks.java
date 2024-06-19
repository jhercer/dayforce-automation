package dayforce.hooks;

import dayforce.rest.Client;
import io.cucumber.java.After;
import org.springframework.beans.factory.annotation.Autowired;

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
    public void token() {
        client.getCONTEXT().setUrl(BASE_URL);
        client.getCONTEXT().setPayload(AUTHENTICATE_PAYLOAD);
        client.post("/api/v2/authenticate", null, null);
    }
}
