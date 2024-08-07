package com.anixton.jobportal.services;

import com.anixton.jobportal.entity.JobSeekerProfile;

import java.util.Optional;

public interface JobSeekerProfileService {
    Optional<JobSeekerProfile> getOne(Integer id);
    JobSeekerProfile addNew(JobSeekerProfile jobSeekerProfile);
    JobSeekerProfile getCurrentSeekerProfile();
}