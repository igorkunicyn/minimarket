package com.igorkunicyn.minimarket.controllers;

import com.igorkunicyn.minimarket.entities.User;
import com.igorkunicyn.minimarket.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("")
    public String viewHomePage(Model model) {
        return viewPage(1, model);
    }

    @RequestMapping(value = "/page/{pageNum}")
    public String viewPage(@PathVariable(name = "pageNum") int pageNum, Model uiModel) {
        Page<User> userPage = userService.findPaginated(pageNum);
        List<User> userList = userPage.getContent();
        uiModel.addAttribute("currentPage", pageNum);
        uiModel.addAttribute("totalPages", userPage.getTotalPages());
        uiModel.addAttribute("totalItems", userPage.getTotalElements());
        uiModel.addAttribute("listUsers", userList);
        return "user/user-list";
    }

}
