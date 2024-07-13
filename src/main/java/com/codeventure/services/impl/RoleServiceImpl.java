package com.codeventure.services.impl;

import com.codeventure.entities.Role;
import com.codeventure.repo.RoleRepository;
import com.codeventure.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl  implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Override
    public Role getRole(long id) {
        return this.roleRepository.findById(id).get();
    }
}
