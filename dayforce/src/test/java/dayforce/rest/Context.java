package dayforce.rest;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.given;

public enum Context {
    CONTEXT;
    private final String URL = "URL";
    private final String PAYLOAD = "PAYLOAD";
    private final String REQUEST = "REQUEST";
    private final String RESPONSE = "RESPONSE";
    private final ThreadLocal<Map<String, Object>> threadLocal = ThreadLocal.withInitial(HashMap::new);
    public Map<String, Object> getMap() {
        return threadLocal.get();
    }
    public <T> T  get(String key, Class<T> objectType) {
        return objectType.cast(getMap().get(key));
    }
    public <T> T getPayload(Class<T> objectType) {
        return get(PAYLOAD, objectType);
    }
    public Object getPayload() {
        return getMap().get(PAYLOAD);
    }
    public Response getResponse() {
        return get(RESPONSE, Response.class);
    }
    public RequestSpecification getRequest() {
        RequestSpecification requestSpecification = get(REQUEST, RequestSpecification.class);
        return (null == requestSpecification) ? given() : requestSpecification;
    }

    public String getUrl() {
        return (String) getMap().get(URL);
    }

    private void set(String key, Object value) { getMap().put(key, value); }
    public void setUrl(Object value) { getMap().put(URL, value); }
    public void setPayload(Object value) {
        set(PAYLOAD, value);
    }
    public void setResponse(Response response) {
        getMap().put(RESPONSE, response);
    }
}
