package at.mse.bld.generator;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class WebSiteEventGenerator {

    private final static List<String> eventTypes = Arrays.asList("view", "purchase");

    public static WebSiteEvent generate() {
        return new WebSiteEvent(
                eventTypes.get(ThreadLocalRandom.current().nextInt(eventTypes.size())),
                ThreadLocalRandom.current().nextInt(100_000),
                ThreadLocalRandom.current().nextInt(100),
                System.currentTimeMillis(),
                ThreadLocalRandom.current().nextInt(100) + 0.99f
        );
    }
}
