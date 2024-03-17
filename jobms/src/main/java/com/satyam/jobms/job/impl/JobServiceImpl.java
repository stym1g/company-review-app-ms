package com.satyam.jobms.job.impl;

import com.satyam.jobms.job.Job;
import com.satyam.jobms.job.JobRepository;
import com.satyam.jobms.job.JobService;
import com.satyam.jobms.job.clients.CompanyClient;
import com.satyam.jobms.job.clients.ReviewClient;
import com.satyam.jobms.job.dto.JobDTO;
import com.satyam.jobms.job.external.Company;
import com.satyam.jobms.job.external.Review;
import com.satyam.jobms.job.mapper.JobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {
    //private final List<Job> jobs = new ArrayList<>();
    JobRepository jobRepository;

    //@Autowired
    RestTemplate restTemplate;
    private final CompanyClient companyClient;
    private final ReviewClient reviewClient;

    public JobServiceImpl(JobRepository jobRepository, CompanyClient companyClient, ReviewClient reviewClient) {
        this.jobRepository = jobRepository;
        this.companyClient = companyClient;
        this.reviewClient = reviewClient;
    }

    private Long id = 1L;
    @Override
    public List<JobDTO> findAll() {
        List<Job> jobs = jobRepository.findAll();
        return jobs.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
    @Override
    public JobDTO findOne(Long id) {
        return convertToDTO(jobRepository.findById(id).orElse(null));
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
    private JobDTO convertToDTO(Job job){
        if(job == null){
            return null;
        }
        restTemplate = new RestTemplate();
        Company company = restTemplate.getForObject("http://localhost:8081/companies/" + job.getCompanyId(), Company.class);
        // Company company = companyClient.getCompany(job.getCompanyId());
        ResponseEntity<List<Review>> reviewResponse = restTemplate.exchange(
                "http://localhost:8083/reviews?companyId=" + job.getCompanyId(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Review>>(){});
        List<Review> reviews = reviewResponse.getBody();
        // List<Review> reviews = reviewClient.getReviews(job.getCompanyId());
        // jobDTO.setCompany(company);
        return JobMapper.mapToJobWithCompanyDTO(job, company, reviews);
    }
}
