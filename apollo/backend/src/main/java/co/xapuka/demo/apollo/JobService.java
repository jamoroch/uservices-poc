package co.xapuka.demo.apollo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.cfg.NotYetImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JobService {

    private static Logger LOG = LoggerFactory.getLogger(JobService.class);
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
        return jobRepository.findTop10ByStatusInOrderByLastModifiedDateAsc(Arrays.asList(JobStatus.CREATED,
                JobStatus.WAITING));
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

    public void removeFailedJobs() {
        List<Job> jobs = jobRepository.findByStatus(JobStatus.FAILED);
        LOG.info(" FAILED jobs to be deleted: {}", jobs.size());
        jobs.forEach(j -> LOG.info("FAILED job to be deleted {}", j.getJobId() ));
        jobRepository.deleteAll(jobs);
    }
    public void removeDoneJobs() {
        List<Job> jobs = jobRepository.findByStatus(JobStatus.DONE);
        LOG.info(" DONE jobs to be deleted: {}", jobs.size());
        jobs.forEach(this::removeDoneJob);
    }

    public void removeDoneJob(Job job) {
        if(JobStatus.DONE.equals(job.getStatus())) {
            jobRepository.delete(job);
            LOG.info("DONE job to be deleted {}", job.getJobId());
        } else {
            LOG.warn("Tried to remove not successful Job {} with status {}", job.getJobId(), job.getStatus());
        }

    }

}