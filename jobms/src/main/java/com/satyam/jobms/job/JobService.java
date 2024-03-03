package com.satyam.jobms.job;

import com.satyam.jobms.job.dto.JobWithCompanyDTO;

import java.util.List;

public interface JobService {
    List<JobWithCompanyDTO> findAll();
    JobWithCompanyDTO findOne(Long id);
    void createJob(Job job);
    Boolean deleteJob(Long id);

    Boolean updateJob(Long id, Job job);
}
