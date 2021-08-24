package com.igorkunicyn.minimarket.services;

import com.igorkunicyn.minimarket.entities.Category;
import com.igorkunicyn.minimarket.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryService {

    private CategoryRepository categoryRepo;

    @Autowired
    public void setCategoryRepo(CategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public List<Category> categoryList() {
        return categoryRepo.findAll();
    }

    public void save(Category category) {
        categoryRepo.save(category);
    }

    public Category getCategoryById(long id) {
        return categoryRepo.findById(id);
    }

    public boolean deleteCategory(long id) {
        Category category = categoryRepo.findById(id);
        if (category != null && category.getProducts().isEmpty()) {
            categoryRepo.delete(category);
            return true;
        }
        return false;
    }
}
