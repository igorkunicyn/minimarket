package com.igorkunicyn.minimarket.repositories;

import com.igorkunicyn.minimarket.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findByUsername(String username);

    User findById(Long id);

    void deleteById(Long id);

    @Override
    void delete(User user);
}
