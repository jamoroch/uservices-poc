package co.xapuka.demo.apollo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JobRepository  extends JpaRepository<Job, UUID> {


    List<Job> findTop10ByStatusInOrderByLastModifiedDateAsc(List<JobStatus> asList);
    List<Job> findTop1000ByStatusInOrderByLastModifiedDateAsc(List<JobStatus> statuses);
    List<Job> findByStatus(JobStatus status);

}
