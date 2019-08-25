package co.xapuka.demo.apollo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class HermesConnector {

    private static Logger LOG = LoggerFactory.getLogger(HermesConnector.class);

    @Value("${apollo.hermes-base-url:http://localhost:1000}")
    private String baseUrl;

    private RestTemplate client = new RestTemplate();

    public void send(Map<String, String> payload, JobType type) {


        switch (type) {
            case CREATE_COMPANY:
                client.postForLocation(baseUrl, payload, Map.class);

                break;
            case UPDATE_COMPANY:
                String companyId = payload.get("companyId");
                String tags = payload.get("tags");
                client.put(String.format("%s/%s/%s", baseUrl, companyId, tags), null);
                break;
        }

    }
}
