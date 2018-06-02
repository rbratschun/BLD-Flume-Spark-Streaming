package at.mse.bld.generator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException{

        final String url = "http://flume:";

        TimeUnit.SECONDS.sleep(5);
        ObjectMapper objectMapper = new ObjectMapper();
        while(true) {
            try {
                // generate event
                WebSiteEvent event = WebSiteEventGenerator.generate();
                String eventJson = objectMapper.writeValueAsString(event);
                // transform to flumeEvent
                final FlumeEvent flumeEvent = new FlumeEvent(new HashMap<>(), eventJson);
                final String flumeJson = objectMapper.writeValueAsString(Arrays.asList(flumeEvent));
                System.out.println(flumeJson);
                // send to different flume source by port depending on event type
                final int port = "view".equals(event.getType()) ? 18000 : 18001;
                // send flumeevent to flume source
                final String response = Unirest.post(url+port)
                        .body(flumeJson)
                        .asString().getStatusText();
                // System.out.println("response: " + response);
                // generate 10 - 100 events per second
                TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(10, 100));
            }
            catch(JsonProcessingException | UnirestException exp) {
                System.out.println(exp);
            }
        }
    }
}
