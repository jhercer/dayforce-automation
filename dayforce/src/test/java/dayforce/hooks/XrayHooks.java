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

    @Autowired
    private Client client;

    @After(value = "@xray", order = 1000)
    public void token() {
        client.getCONTEXT().setUrl(BASE_URL);
        client.getCONTEXT().setPayload(AUTHENTICATE_PAYLOAD);
        client.post("/api/v2/authenticate", null, null);
    }
}
