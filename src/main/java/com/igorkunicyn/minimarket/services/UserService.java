package com.igorkunicyn.minimarket.services;

import com.igorkunicyn.minimarket.entities.User;
import com.igorkunicyn.minimarket.repositories.RoleRepository;
import com.igorkunicyn.minimarket.repositories.UserRepository;
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

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepo;

    @Autowired
    public void setUserRepo(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    private RoleRepository roleRepo;

    @Autowired
    public void setRoleRepo(RoleRepository roleRepo) {
        this.roleRepo = roleRepo;
    }

    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Page<User> findPaginated(int pageNum) {
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
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

    public User findUserById(Long userId) {
        Optional<User> userFromDb = Optional.ofNullable(userRepo.findById(userId));
        return userFromDb.orElse(new User());
    }

    public List<User> allUsers() {
        return userRepo.findAll();
    }

    public boolean saveUser(User user) {
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

    public boolean deleteUser(Long userId) {
        User user = userRepo.findById(userId);
        if (user != null) {
            userRepo.delete(user);
            return true;
        }
        return false;
    }

    public boolean editUser(Long userId) {
        User user = userRepo.findById(userId);
        if (user != null) {
            user.addRoles(roleRepo.findByName("ROLE_ADMIN"));
            userRepo.save(user);
            return true;
        }
        return false;
    }

}
