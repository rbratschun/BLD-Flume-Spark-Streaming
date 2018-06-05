package at.mse.bld.generator;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

class WebSiteEventGenerator {

    private final static List<String> eventTypes = Arrays.asList("view", "purchase");

    static WebSiteEvent generate() {
        String type = eventTypes.get(ThreadLocalRandom.current().nextInt(eventTypes.size()));
        return new WebSiteEvent.WebSiteEventBuilder()
                .customer(ThreadLocalRandom.current().nextInt(100_000))
                .product(ThreadLocalRandom.current().nextInt(100))
                .timestamp(System.currentTimeMillis())
                .type(type)
                .revenue(type.equals("view") ? 0 : ThreadLocalRandom.current().nextInt(100) + 0.99f)
                .build();
    }
}
