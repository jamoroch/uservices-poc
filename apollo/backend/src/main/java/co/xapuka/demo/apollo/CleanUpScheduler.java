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
    @SchedulerLock(name = "CleanupScheduler.removeFailedJobs", lockAtLeastForString = "PT10S", lockAtMostForString =
            "PT10S")
    public void removeFailedJobs() {
        jobService.removeFailedJobs();
    }


    @Scheduled(fixedDelay = 5000)
    @SchedulerLock(name = "CleanupScheduler.removeDoneJobs", lockAtLeastForString = "PT5S", lockAtMostForString =
            "PT5S")
    public void removeDoneJobs() {
        jobService.removeDoneJobs();
    }

}
