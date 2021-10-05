package com.igorkunicyn.minimarket.services.impl;

import com.igorkunicyn.minimarket.entities.User;
import com.igorkunicyn.minimarket.repositories.RoleRepository;
import com.igorkunicyn.minimarket.repositories.UserRepository;
import com.igorkunicyn.minimarket.services.Serviceable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

//@Service
public class UserServiceImpl implements Serviceable<User>, UserDetailsService {

    private UserRepository userRepo;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private RoleRepository roleRepo;
    private final int PAGE_SIZE = 5;

    @Autowired
    public void setUserRepo(UserRepository userRepo) {
        this.userRepo = userRepo;
    }


    @Autowired
    public void setRoleRepo(RoleRepository roleRepo) {
        this.roleRepo = roleRepo;
    }


    @Autowired
    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Page<User> findPaginated(int pageNum) {
        Pageable pageable = PageRequest.of(pageNum - 1, PAGE_SIZE);
        return userRepo.findAll(pageable);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User myUser = userRepo.findByUsername(username);
        if (myUser == null) {
            throw new UsernameNotFoundException("User not found");

        }
        return myUser;
    }

    @Override
    public User getById(long userId) {
        Optional<User> userFromDb = Optional.ofNullable(userRepo.findById(userId));
        return userFromDb.orElse(new User());
    }

    @Override
    public List<User> getList() {
        return userRepo.findAll();
    }

    @Override
    public boolean save(User user) {
        User userFromDb = userRepo.findByUsername(user.getUsername());
        if (userFromDb != null) {
            return false;
        }
        user.addRoles(roleRepo.findByName("ROLE_USER"));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        try{
            userRepo.saveAndFlush(user);
        }catch (ConstraintViolationException e){
            System.out.println(e.getMessage());
        }
        return true;
    }

    @Override
    public boolean delete(long userId) {
        User user = userRepo.findById(userId);
        if (user != null) {
            userRepo.delete(userRepo.findById(userId));
        }
        return userRepo.findById(userId) == null;
    }

}
