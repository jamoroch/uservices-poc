package co.xapuka.demo.apollo;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "job", indexes = {@Index(name="idx_job_lastmodified_date", columnList = "lastModifiedDate"), @Index(name=
        "idx_job_status", columnList = "status"), @Index(name = "idx_job_retryCount", columnList =
        "retryCount")})
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID jobId;

    @CreatedDate
    private Date lastModifiedDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private JobStatus status;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private JobType type;

    @Column(nullable = false)
    private String payload;

    @Column(nullable = false)
    private Integer retryCount;


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

    public Integer getRetryCount() {
        return retryCount;
    }

    public void setNumberOfRetries(Integer retryCount) {
        this.retryCount = retryCount;
    }


    @PrePersist
    public void prePersist() {
        if(retryCount == null) {
            retryCount = Integer.valueOf("0");
        }
    }

    @PreUpdate
    public void preUpdate() {
        if(JobStatus.WAITING.equals(status)) {
            retryCount++;
        }
    }
}
