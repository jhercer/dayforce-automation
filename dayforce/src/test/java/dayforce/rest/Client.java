package dayforce.rest;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Data;

import java.util.Base64;
import java.util.logging.Logger;

@Data
public class Client {
    private static final Logger LOGGER = Logger.getAnonymousLogger();
    private final Context CONTEXT = Context.CONTEXT;
    public void post(String path, String username, String password) {
        final String url = CONTEXT.getUrl() + path;
        final RequestSpecification requestSpecification = CONTEXT.getRequest();
        final Object payload = CONTEXT.getPayload();

        if(username != null && password != null) {
            String auth = Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
            requestSpecification.header("Authorization", "Basic " + auth);
        }

        setPayload(requestSpecification, payload);

        Response response = requestSpecification
                .accept(ContentType.JSON)
                .log()
                .all()
                .post(url);

        logResponse(response);

        CONTEXT.setResponse(response);
    }

    private void setPayload(RequestSpecification requestSpecification, Object payload) {
        if(null != payload) {
            requestSpecification
                    .contentType(ContentType.JSON)
                    .body(payload);
        }
    }

    private void logResponse(Response response) {
        response
                .then()
                .log()
                .all();
    }
}
