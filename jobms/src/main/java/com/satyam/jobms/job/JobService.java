package com.satyam.jobms.job;

import com.satyam.jobms.job.dto.JobDTO;

import java.util.List;

public interface JobService {
    List<JobDTO> findAll();
    JobDTO findOne(Long id);
    void createJob(Job job);
    Boolean deleteJob(Long id);

    Boolean updateJob(Long id, Job job);
}
