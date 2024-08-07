package com.anixton.jobportal.services.Implementations;

import com.anixton.jobportal.entity.UsersType;
import com.anixton.jobportal.repository.UsersTypeRepository;
import com.anixton.jobportal.services.UsersTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersTypeServiceImpl implements UsersTypeService {

    private final UsersTypeRepository usersTypeRepository;

    @Autowired
    public UsersTypeServiceImpl(UsersTypeRepository usersTypeRepository) {
        this.usersTypeRepository = usersTypeRepository;
    }

    @Override
    public List<UsersType> getAll() {
        return usersTypeRepository.findAll();
    }
}
