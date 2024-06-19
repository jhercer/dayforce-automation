package dayforce.steps;

import dayforce.config.Properties;
import dayforce.entities.JsonPlaceholderResponse;
import dayforce.rest.Client;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;

public class ApiSteps {
    @Autowired
    private Client client;
    @Autowired
    private Properties properties;
    private final String PAYLOAD = "{\n" +
            "    \"title\": \"foo\",\n" +
            "    \"body\": \"bar\",\n" +
            "    \"userId\": 1\n" +
            "}";

    @Given("a simple api test")
    public void a_simple_api_test() {
        client.getCONTEXT().setUrl(properties.getJsonPlaceHolderUrl());
        client.getCONTEXT().setPayload(PAYLOAD);
        client.post("/posts", null, null, null);
        JsonPlaceholderResponse response = client.getCONTEXT().getResponse().jsonPath().getObject("$", JsonPlaceholderResponse.class);
        System.out.printf("title on response is \"%s\"\n", response.getTitle());
    }
}
