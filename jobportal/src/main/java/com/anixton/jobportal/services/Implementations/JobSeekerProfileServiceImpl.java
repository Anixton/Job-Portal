package com.anixton.jobportal.services.Implementations;

import com.anixton.jobportal.entity.JobSeekerProfile;
import com.anixton.jobportal.entity.Users;
import com.anixton.jobportal.repository.JobSeekerProfileRepository;
import com.anixton.jobportal.repository.UsersRepository;
import com.anixton.jobportal.services.JobSeekerProfileService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JobSeekerProfileServiceImpl implements JobSeekerProfileService {

    private final JobSeekerProfileRepository jobSeekerProfileRepository;
    private final UsersRepository usersRepository;

    public JobSeekerProfileServiceImpl(JobSeekerProfileRepository jobSeekerProfileRepository, UsersRepository usersRepository) {
        this.jobSeekerProfileRepository = jobSeekerProfileRepository;
        this.usersRepository = usersRepository;
    }

    @Override
    public Optional<JobSeekerProfile> getOne(Integer id) {
        return jobSeekerProfileRepository.findById(id);
    }

    @Override
    public JobSeekerProfile addNew(JobSeekerProfile jobSeekerProfile) {
        return jobSeekerProfileRepository.save(jobSeekerProfile);
    }

    @Override
    public JobSeekerProfile getCurrentSeekerProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (isAuthenticated(authentication)) {
            String currentUsername = authentication.getName();
            Users users = findUserByEmail(currentUsername);
            return getJobSeekerProfileForUser(users);
        }

        return null;
    }

    private boolean isAuthenticated(Authentication authentication) {
        return !(authentication instanceof AnonymousAuthenticationToken);
    }

    private Users findUserByEmail(String email) {
        return usersRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public JobSeekerProfile getJobSeekerProfileForUser(Users user) {
        return getOne(user.getUserId())
                .orElse(null);
    }
}
