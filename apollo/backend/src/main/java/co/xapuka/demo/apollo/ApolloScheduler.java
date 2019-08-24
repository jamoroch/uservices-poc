package co.xapuka.demo.apollo;


import net.javacrumbs.shedlock.core.SchedulerLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApolloScheduler {

    @Autowired
    JobService jobService;

    @Autowired
    JobWorker jobWorker;

@Scheduled(fixedDelay = 1000)
@SchedulerLock(name = "ApolloScheduler.process", lockAtLeastForString = "PT1S")
    public void process(){

    List<Job> jobs = jobService.getJobsToProcess();

    for(Job job: jobs){
        job.setStatus(JobStatus.PROCESSING);
        Job updatedJob = jobService.updateJob(job);
        jobWorker.process(updatedJob);
    }

}


}
