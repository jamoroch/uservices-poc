package co.xapuka.demo.apollo;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class HermesConnector {
    public void send(Map<String, String> payload, JobType type) {

        String result = payload.entrySet()
                .stream()
                .map(entry -> entry.getKey() + " - " + entry.getValue())
                .collect(Collectors.joining(", "));
        System.out.println("Job Type " + type + "payload" +result);
    }
}
