package com.anixton.jobportal.services;

import com.anixton.jobportal.entity.Users;

public interface UsersService {
    Users addNew(Users users);
    Object getCurrentUserProfile();
    Users getCurrentUser();
    Users findByEmail(String email);
}