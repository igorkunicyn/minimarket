package com.igorkunicyn.minimarket.services.impl;

import com.igorkunicyn.minimarket.entities.Category;
import com.igorkunicyn.minimarket.repositories.CategoryRepository;
import com.igorkunicyn.minimarket.services.Serviceable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements Serviceable<Category> {

    private CategoryRepository categoryRepo;

    @Autowired
    public void setCategoryRepo(CategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public List<Category> getList() {
        return categoryRepo.findAll();
    }

    @Override
    public boolean save(Category category) {
        categoryRepo.save(category);
        return categoryRepo.existsById(category.getId());
    }

    @Override
    public Category getById(long id) {
        return categoryRepo.findById(id);
    }


    @Override
    public boolean delete(long id) {
        categoryRepo.delete(getById(id));
        return !categoryRepo.existsById(id);
    }

}
