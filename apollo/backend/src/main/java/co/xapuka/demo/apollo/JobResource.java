package co.xapuka.demo.apollo;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class JobResource {

    @Autowired
    JobService jobservice;


    @GetMapping("/")
    public ResponseEntity getAll() {
        List<Job> jobs = jobservice.getAll();
        return ResponseEntity.ok(jobs);
    }

    @GetMapping("/statitics")
    public ResponseEntity statistics() {
        Map<String, String> statistics = jobservice.statistics();
         return ResponseEntity.ok(statistics);
    }

    @GetMapping("/{jobId}")
    public ResponseEntity getJob(@PathVariable("jobId") UUID jobId) {
        Job job = jobservice.getJob(jobId);
        return ResponseEntity.ok(job);
    }

    @PostMapping("/")
    public ResponseEntity createCompany(@RequestBody Map<String, String> payload) throws JsonProcessingException {
        Job job = jobservice.createCompany(payload);
        return ResponseEntity.ok(job);
    }

    @PutMapping("/{companyId}")
    public ResponseEntity updateCompany(@PathVariable("companyId")UUID companyId,
                                        @RequestBody Map<String, String> payload) throws JsonProcessingException {
        Job job = jobservice.updateCompany(companyId, payload);
        return ResponseEntity.ok(job);
    }
}
