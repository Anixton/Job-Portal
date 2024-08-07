package com.anixton.jobportal.services;

import com.anixton.jobportal.entity.JobPostActivity;
import com.anixton.jobportal.entity.JobSeekerProfile;
import com.anixton.jobportal.entity.JobSeekerSave;

import java.util.List;

public interface JobSeekerSaveService {
    List<JobSeekerSave> getCandidatesJob(JobSeekerProfile userAccountId);
    List<JobSeekerSave> getJobCandidates(JobPostActivity job);
    void addNew(JobSeekerSave jobSeekerSave);
}