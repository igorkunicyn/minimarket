package com.igorkunicyn.minimarket.services.impl;

import com.igorkunicyn.minimarket.entities.Role;
import com.igorkunicyn.minimarket.repositories.RoleRepository;
import com.igorkunicyn.minimarket.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepo;

    @Autowired
    public void setRoleRepo(RoleRepository roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public Role findRoleBiName(String name){
        return roleRepo.findByName(name);
    }
}
