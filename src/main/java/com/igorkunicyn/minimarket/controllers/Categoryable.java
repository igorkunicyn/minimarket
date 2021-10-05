package com.igorkunicyn.minimarket.controllers;

import com.igorkunicyn.minimarket.entities.Category;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

public interface Categoryable extends Controllable{
    String create(Model model);
    ModelAndView edit(long id1);
    String save(Category category);
}
