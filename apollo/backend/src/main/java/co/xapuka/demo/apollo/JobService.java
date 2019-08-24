package co.xapuka.demo.apollo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class JobService {

    private static final Integer MAX_JOB_RETRY = 5;
    @Autowired
    JobRepository jobRepository;

    public List<Job> getAll() {
        return jobRepository.findAll();
    }

    public Map<String, String> statistics() {
        throw new NotYetImplementedException();
        //return null;
    }

    public Job getJob(UUID jobId) {
        return jobRepository.getOne(jobId);
    }

    public Job createCompany(Map<String, String> payload) throws JsonProcessingException {
       Job job = new Job();
       job.setStatus(JobStatus.CREATED);
       job.setType(JobType.CREATE_COMPANY);
        String value = new ObjectMapper().writeValueAsString(payload);
        job.setPayload(value);
        Job savedJob = jobRepository.save(job);
        return savedJob;
    }

    public Job updateCompany(UUID companyId, Map<String, String> payload) throws JsonProcessingException {
        Job job = new Job();
        job.setStatus(JobStatus.CREATED);
        job.setType(JobType.UPDATE_COMPANY);
        if(null == payload.get("companyId")) {
            payload.put("companyId", companyId.toString());
        }
        String value = new ObjectMapper().writeValueAsString(payload);
        job.setPayload(value);
        Job savedJob = jobRepository.save(job);
        return savedJob;
    }

    public List<Job> getJobsToProcess(){
        return jobRepository.findAll();
    }

    public Job updateJob(Job job) {
        return jobRepository.saveAndFlush(job);
    }

    public void handleJobError(Job job, Exception e) {
        if(job.getRetryCount() >= MAX_JOB_RETRY) {
           job.setStatus(JobStatus.FAILED);
        } else {
            job.setStatus(JobStatus.WAITING);
        }
        updateJob(job);
    }
}
