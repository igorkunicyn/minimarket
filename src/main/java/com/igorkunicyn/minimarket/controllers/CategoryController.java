package com.igorkunicyn.minimarket.controllers;

import com.igorkunicyn.minimarket.entities.Category;
import com.igorkunicyn.minimarket.services.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController implements Categoryable {

    private CategoryServiceImpl categoryServiceImpl;

    @Autowired
    public void setCategoryServiceImpl(CategoryServiceImpl categoryServiceImpl) {
        this.categoryServiceImpl = categoryServiceImpl;
    }

    @Override
    @GetMapping("/list")
    public String getList(Model uiModel) {
        uiModel.addAttribute("categories", categoryServiceImpl.getList());
        return "category/list";
    }

    @Override
    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("category", new Category());
        return "category/new";
    }

    @Override
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("category") Category category) {
        categoryServiceImpl.save(category);
        return "redirect:/category/list";
    }

    @Override
    @RequestMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable(name = "id") long id) {
        return new ModelAndView("category/edit")
                .addObject("category", categoryServiceImpl.getById(id));
    }

    @Override
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") long id) {
        categoryServiceImpl.delete(id);
        return "redirect:/category/list";

    }

}
