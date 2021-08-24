package com.igorkunicyn.minimarket.controllers;

import com.igorkunicyn.minimarket.entities.Category;
import com.igorkunicyn.minimarket.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/list")
    public String listCategories(Model uiModel) {
        List<Category> categoryList = categoryService.categoryList();
        uiModel.addAttribute("categories", categoryList);
        return "category/list";
    }

    @GetMapping("/new")
    public String createNewCategory(Model model) {
        Category category = new Category();
        model.addAttribute("category", category);
        return "category/new";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveCategory(@ModelAttribute("category") Category category) {
        categoryService.save(category);
        return "redirect:/category/list";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditCategory(@PathVariable(name = "id") int id) {
        ModelAndView modelAndView = new ModelAndView("category/edit");
        Category category = categoryService.getCategoryById(id);
        modelAndView.addObject("category", category);
        return modelAndView;
    }

    @RequestMapping("/delete/{id}")
    public String deleteCategory(@PathVariable(name = "id") int id) {
        categoryService.deleteCategory(id);
        return "redirect:/category/list";

    }

}
