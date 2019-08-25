package co.xapuka.demo.apollo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Map;


@Component
public class JobWorker {

    @Autowired
    private JobService jobService;

    @Autowired
    private HermesConnector hermesConnector;


    public void process(Job job) {
        try {
            Map<String, String> payload = new ObjectMapper().readValue(job.getPayload(), Map.class);
            hermesConnector.send(payload, job.getType());
            job.setStatus(JobStatus.DONE);
            jobService.updateJob(job);
        } catch (Exception e) {
            jobService.handleJobError(job, e);
        }
    }
}
