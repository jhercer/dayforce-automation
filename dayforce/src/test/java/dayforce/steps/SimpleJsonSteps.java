package dayforce.steps;

import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SimpleJsonSteps {
    private JsonNode jsonNode;

    @Given("Books are defined by a simple json")
    public void books_are_defined_by_json(String jsonString) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        jsonNode = objectMapper.readTree(jsonString);
    }

    @When("Convert the parameter string to a JSON object")
    public void convert_parameter_string_to_json() {
        // no action needed
    }

    @Then("Verify the JSON object")
    public void verify_json_object() {
        if(jsonNode != null) {
            System.out.print("Parsed json object");
            System.out.printf("json: %s", jsonNode.toPrettyString());
        } else {
            System.out.print("Error: json object is null");
        }
    }
}
