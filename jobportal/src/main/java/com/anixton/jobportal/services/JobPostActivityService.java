package com.anixton.jobportal.services;

import com.anixton.jobportal.entity.JobPostActivity;
import com.anixton.jobportal.entity.RecruiterJobsDto;
import java.time.LocalDate;
import java.util.List;

public interface JobPostActivityService {
    JobPostActivity addNew(JobPostActivity jobPostActivity);
    List<RecruiterJobsDto> getRecruiterJobs(int recruiter);
    JobPostActivity getOne(int id);
    List<JobPostActivity> getAll();
    List<JobPostActivity> search(String job, String location, List<String> type, List<String> remote, LocalDate searchDate);
}
