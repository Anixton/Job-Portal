package com.anixton.jobportal.services;

import com.anixton.jobportal.entity.JobPostActivity;
import com.anixton.jobportal.entity.JobSeekerApply;
import com.anixton.jobportal.entity.JobSeekerProfile;
import java.util.List;

public interface JobSeekerApplyService {
    List<JobSeekerApply> getCandidatesJobs(JobSeekerProfile userAccountId);
    List<JobSeekerApply> getJobCandidates(JobPostActivity job);
    void addNew(JobSeekerApply jobSeekerApply);
}
