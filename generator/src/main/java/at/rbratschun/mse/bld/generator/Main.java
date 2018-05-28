package at.rbratschun.mse.bld.generator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException{

        final String url = "http://flume:";

        TimeUnit.SECONDS.sleep(5);

        while(true) {
            try {
                WebSiteEvent event = WebSiteEventGenerator.generate();
                ObjectMapper objectMapper = new ObjectMapper();
                String eventJson =objectMapper.writeValueAsString(event);
                final FlumeEvent flumeEvent = new FlumeEvent(new HashMap<>(), eventJson);
                final String flumeJson = objectMapper.writeValueAsString(Arrays.asList(flumeEvent));
                // System.out.println(flumeJson);
                final int port = "view".equals(event.getType()) ? 18000 : 18001;
                final String response = Unirest.post(url+port)
                        .body(flumeJson)
                        .asString().getStatusText();
                // System.out.println("response: " + response);
                TimeUnit.MILLISECONDS.sleep(150);
            }
            catch(JsonProcessingException | UnirestException exp) {
                System.out.println(exp);
            }
        }
    }
}
