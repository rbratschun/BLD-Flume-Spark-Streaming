package at.rbratschun.mse.bld.generator;

import java.util.HashMap;

public class FlumeEvent {
    private HashMap<String, Object> headers;
    private String body;

    public HashMap<String, Object> getHeaders() {
        return headers;
    }

    public void setHeaders(HashMap<String, Object> headers) {
        this.headers = headers;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public FlumeEvent(HashMap<String, Object> headers, String body) {

        this.headers = headers;
        this.body = body;
    }
}
