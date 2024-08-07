package com.anixton.jobportal.services;

import com.anixton.jobportal.entity.RecruiterProfile;

import java.util.Optional;

public interface RecruiterProfileService {
    Optional<RecruiterProfile> getOne(Integer id);
    RecruiterProfile addNew(RecruiterProfile recruiterProfile);
    RecruiterProfile getCurrentRecruiterProfile();
}