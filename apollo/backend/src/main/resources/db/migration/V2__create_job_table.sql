CREATE TABLE job (
  jobId uuid NOT NULL,
  lastModifiedDate TIMESTAMP WITH TIME ZONE NOT NULL,
  status VARCHAR(255)  NOT NULL,
  type  VARCHAR(255) NOT NULL,
  payload VARCHAR(255) NOT NULL,
  retryCount INT4 NOT NULL,
  PRIMARY KEY (jobId)
);

CREATE INDEX idx_job_lastmodified_date ON job (lastModifiedDate);

CREATE INDEX idx_job_status ON job (status);

CREATE INDEX idx_job_retryCount ON job (retryCount);