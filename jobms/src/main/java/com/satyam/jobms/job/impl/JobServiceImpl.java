package com.satyam.jobms.job.impl;

import com.satyam.jobms.job.Job;
import com.satyam.jobms.job.JobRepository;
import com.satyam.jobms.job.JobService;
import com.satyam.jobms.job.external.Company;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
    //private final List<Job> jobs = new ArrayList<>();
    JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    private Long id = 1L;
    @Override
    public List<Job> findAll() {
        RestTemplate restTemplate = new RestTemplate();
        Company company = restTemplate.getForObject("localhost:8082/companies/1", Company.class);
        System.out.println("Company name: " + company.getName());
        return jobRepository.findAll();
    }
    @Override
    public Job findOne(Long id) {
//        for(Job j: jobs){
//            if(Objects.equals(j.getId(), id)){
//                return j;
//            }
//        }
        return jobRepository.findById(id).orElse(null);
    }
    @Override
    public void createJob(Job job) {
        job.setId(id);
        jobRepository.save(job);
        id++;
    }
    @Override
    public Boolean deleteJob(Long id) {
//        for(int i=0;i<jobs.size();i++){
//            if(Objects.equals(jobs.get(i).getId(), id)){
//                jobs.remove(i);
//                return true;
//            }
//        }
        try{
            jobRepository.deleteById(id);
            return true;
        }catch (Exception ex){
            return false;
        }
    }
    @Override
    public Boolean updateJob(Long id, Job updatedJob) {
//        for(int i=0;i<jobs.size();i++){
//            if(Objects.equals(jobs.get(i).getId(), id)){
//                job.setId(id);
//                jobs.set(i, job);
//                return true;
//            }
//        }
        Optional<Job> optionalJob = jobRepository.findById(id);
        if(optionalJob.isPresent()){
            Job job = optionalJob.get();
            job.setDescription(updatedJob.getDescription());
            job.setLocation(updatedJob.getLocation());
            job.setTitle(updatedJob.getTitle());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setMinSalary(updatedJob.getMinSalary());
            jobRepository.save(job);
            return true;
        }
        return false;
    }
}
