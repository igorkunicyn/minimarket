package com.igorkunicyn.minimarket.controllers;

import com.igorkunicyn.minimarket.entities.User;
import com.igorkunicyn.minimarket.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController implements Controllable{

    private  UserServiceImpl userServiceImpl;

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userServiceImpl = userService;
    }

    @Override
    @GetMapping("/users")
    public String getList(Model model){
        return viewPage(1,model);
//        model.addAttribute("allUsers", userServiceImpl.getList());
//        return "user-list";
    }

    @Override
    @GetMapping("/users/delete/{id}")
    public String delete(@PathVariable(name = "id") long id){
            userServiceImpl.delete(id);
        return "redirect:/admin/users";
    }
    @RequestMapping(value = "/users/page/{pageNum}")
    public String viewPage(@PathVariable(name = "pageNum") int pageNum, Model uiModel) {
        Page<User> userPage = userServiceImpl.findPaginated(pageNum);
//        List<User> userList = userServiceImpl.findPaginated(pageNum).getContent();
        uiModel.addAttribute("currentPage", pageNum);
        uiModel.addAttribute("totalPages", userPage.getTotalPages());
        uiModel.addAttribute("totalItems", userPage.getTotalElements());
        uiModel.addAttribute("listUsers", userPage.getContent());
        return "user-list";
    }

//    @GetMapping("/users/edit/{id}")
//    public String editUser(@PathVariable(name = "id") long id){
//        userServiceImpl.editUser(id);
//        return "redirect:/admin/users";
//    }

}
