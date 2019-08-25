package co.xapuka.demo.apollo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class HermesConnector {
    private static Logger LOG = LoggerFactory.getLogger(HermesConnector.class);
    public void send(Map<String, String> payload, JobType type) {

        if(JobType.CREATE_COMPANY.equals(type)) {
            RuntimeException e = new RuntimeException("For test purposes! Throwing on CREATED_COMPANY" );
            LOG.warn("For test purpose only! CREATE_COMPANY jobs lead to this exception");
            throw e;
        }

        String result = payload.entrySet()
                .stream()
                .map(entry -> entry.getKey() + " = " + entry.getValue())
                .collect(Collectors.joining(" | "));
        LOG.info("Job Type " + type + "payload" +result);
    }
}
