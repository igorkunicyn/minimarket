package com.igorkunicyn.minimarket.repositories;

import com.igorkunicyn.minimarket.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAll();

    Category findById(long id);

    Category save(Category category);

}
