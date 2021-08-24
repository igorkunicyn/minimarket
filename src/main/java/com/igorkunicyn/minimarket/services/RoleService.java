package com.igorkunicyn.minimarket.services;

import com.igorkunicyn.minimarket.entities.Role;
import com.igorkunicyn.minimarket.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private RoleRepository roleRepo;

    @Autowired
    public void setRoleRepo(RoleRepository roleRepo) {
        this.roleRepo = roleRepo;
    }

    public Role findRoleBiName(String name){
        return roleRepo.findByName(name);
    }
}
