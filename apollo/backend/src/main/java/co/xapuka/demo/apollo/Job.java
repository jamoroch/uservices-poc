package co.xapuka.demo.apollo;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "job", indexes = {@Index(name="idx_job_lastmodified_date", columnList = "lastModifiedDate"), @Index(name=
        "idx_job_status", columnList = "status"), @Index(name = "idx_job_number_of_retries", columnList =
        "numberOfRetries")})
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID jobId;

    @CreatedDate
    private Date lastModifiedDate;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private JobStatus status;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private JobType type;

    @Column(nullable = false)
    private String payload;

    @Column(nullable = false)
    private Integer numberOfRetries;


    public UUID getJobId() {
        return jobId;
    }

    public void setJobId(UUID jobId) {
        this.jobId = jobId;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public JobStatus getStatus() {
        return status;
    }

    public void setStatus(JobStatus status) {
        this.status = status;
    }

    public JobType getType() {
        return type;
    }

    public void setType(JobType type) {
        this.type = type;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public Integer getNumberOfRetries() {
        return numberOfRetries;
    }

    public void setNumberOfRetries(Integer numberOfRetries) {
        this.numberOfRetries = numberOfRetries;
    }
}
