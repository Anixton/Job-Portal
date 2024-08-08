package com.anixton.jobportal.services.Implementations;


import com.anixton.jobportal.entity.RecruiterProfile;
import com.anixton.jobportal.entity.Users;
import com.anixton.jobportal.repository.RecruiterProfileRepository;
import com.anixton.jobportal.repository.UsersRepository;
import com.anixton.jobportal.services.RecruiterProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecruiterProfileServiceImpl implements RecruiterProfileService {

    private final RecruiterProfileRepository recruiterRepository;
    private final UsersRepository usersRepository;

    @Autowired
    public RecruiterProfileServiceImpl(RecruiterProfileRepository recruiterRepository, UsersRepository usersRepository) {
        this.recruiterRepository = recruiterRepository;
        this.usersRepository = usersRepository;
    }

    @Override
    public Optional<RecruiterProfile> getOne(Integer id) {
        return recruiterRepository.findById(id);
    }

    @Override
    public RecruiterProfile addNew(RecruiterProfile recruiterProfile) {
        return recruiterRepository.save(recruiterProfile);
    }

    @Override
    public RecruiterProfile getCurrentRecruiterProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (isAuthenticated(authentication)) {
            String currentUsername = authentication.getName();
            Users users = findUserByEmail(currentUsername);
            return getRecruiterProfileForUser(users);
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

    public RecruiterProfile getRecruiterProfileForUser(Users user) {
        return getOne(user.getUserId())
                .orElse(null);
    }
}