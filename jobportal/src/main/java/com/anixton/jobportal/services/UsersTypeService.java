package com.anixton.jobportal.services;

import com.anixton.jobportal.entity.UsersType;
import com.anixton.jobportal.repository.UsersTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersTypeService {

    private final UsersTypeRepository usersTypeRepository;

    // Constructor Injection
    @Autowired
    public UsersTypeService(UsersTypeRepository usersTypeRepository) {
        this.usersTypeRepository = usersTypeRepository;
    }

    public List<UsersType> getAll() {
        return usersTypeRepository.findAll();
    }
}
