package com.anixton.jobportal.services.Implementations;

import com.anixton.jobportal.entity.*;
import com.anixton.jobportal.repository.JobPostActivityRepository;
import com.anixton.jobportal.services.JobPostActivityService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class JobPostActivityServiceImpl implements JobPostActivityService {

    private final JobPostActivityRepository jobPostActivityRepository;

    public JobPostActivityServiceImpl(JobPostActivityRepository jobPostActivityRepository) {
        this.jobPostActivityRepository = jobPostActivityRepository;
    }

    @Override
    public JobPostActivity addNew(JobPostActivity jobPostActivity) {
        return jobPostActivityRepository.save(jobPostActivity);
    }

    @Override
    public List<RecruiterJobsDto> getRecruiterJobs(int recruiter) {
        List<IRecruiterJobs> recruiterJobs = jobPostActivityRepository.getRecruiterJobs(recruiter);
        return convertToDto(recruiterJobs);
    }

    private List<RecruiterJobsDto> convertToDto(List<IRecruiterJobs> recruiterJobs) {
        List<RecruiterJobsDto> recruiterJobsDtoList = new ArrayList<>();

        for (IRecruiterJobs job : recruiterJobs) {
            RecruiterJobsDto dto = createRecruiterJobsDto(job);
            recruiterJobsDtoList.add(dto);
        }

        return recruiterJobsDtoList;
    }

    private RecruiterJobsDto createRecruiterJobsDto(IRecruiterJobs job) {
        JobLocation jobLocation = new JobLocation(job.getLocationId(), job.getCity(),
                job.getState(), job.getCountry());
        JobCompany jobCompany = new JobCompany(job.getCompanyId(), job.getName(), "");

        return new RecruiterJobsDto(job.getTotalCandidates(), job.getJob_post_id(),
                job.getJob_title(), jobLocation, jobCompany);
    }

    @Override
    public JobPostActivity getOne(int id) {
        return jobPostActivityRepository.findById(id).
                orElseThrow(()->new RuntimeException("Job not found"));
    }

    @Override
    public List<JobPostActivity> getAll() {
        return jobPostActivityRepository.findAll();
    }

    @Override
    public List<JobPostActivity> search(String job, String location, List<String> type, List<String> remote, LocalDate searchDate) {
        return Objects.isNull(searchDate) ? jobPostActivityRepository.searchWithoutDate(job, location, remote,type) :
                jobPostActivityRepository.search(job, location, remote, type, searchDate);
    }
}