package co.xapuka.demo.apollo;


import net.javacrumbs.shedlock.core.SchedulerLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApolloScheduler {

    private static Logger LOG = LoggerFactory.getLogger(ApolloScheduler.class);

    @Value("${apollo.parallelStreams:true}")
    private boolean parallelStreams;


    @Autowired
    JobService jobService;

    @Autowired
    JobWorker jobWorker;

@Scheduled(fixedDelay = 80)
@SchedulerLock(name = "ApolloScheduler.process", lockAtLeastForString = "PT1S")
    public void process(){
    LOG.info("Start Scheduler");

    List<Job> jobs = jobService.getJobsToProcess();

    if (parallelStreams) {

        jobs.parallelStream().forEach(j-> processOneJob(j));
    } else {
        jobs.forEach(j -> processOneJob(j));
    }

    LOG.info("End Scheduler");
}

private void processOneJob(Job job){
    LOG.info("Start process of Job {}", job.getJobId());
    job.setStatus(JobStatus.PROCESSING);
    Job updatedJob = jobService.updateJob(job);
    jobWorker.process(updatedJob);
    LOG.info("End process of Job {}", job.getJobId());
}


}
