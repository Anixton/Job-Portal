package com.anixton.jobportal.repository;

import com.anixton.jobportal.entity.JobPostActivity;
import com.anixton.jobportal.entity.JobSeekerProfile;
import com.anixton.jobportal.entity.JobSeekerSave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobSeekerSaveRepository extends JpaRepository<JobSeekerSave, Integer> {

    List<JobSeekerSave> findByUserId(JobSeekerProfile userAccount);

    List<JobSeekerSave> findByJob(JobPostActivity jobPostActivity);
}
