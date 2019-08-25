package co.xapuka.demo.apollo;

import net.javacrumbs.shedlock.core.SchedulerLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CleanUpScheduler {

@Autowired
    private JobService jobService;



@Scheduled(fixedDelay = 10000)
@SchedulerLock(name = "CleanupScheduler.cleanUp", lockAtLeastForString = "PT10S", lockAtMostForString = "PT10S")
    public void cleanUp(){

    jobService.removeFailedJobs();
}

}
