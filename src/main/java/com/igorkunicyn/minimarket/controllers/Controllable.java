package com.igorkunicyn.minimarket.controllers;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;

public interface Controllable{

    String getList(Model model);
    String delete(long id);

}
