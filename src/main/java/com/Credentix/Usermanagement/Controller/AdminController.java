package com.Credentix.Usermanagement.Controller;

import com.Credentix.Usermanagement.Entity.User;
import com.Credentix.Usermanagement.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController{

    @Autowired
    private AdminService service;

    @GetMapping("/")
    public String admin(Model model) {
        List<User> allUsers = service.findAllUser();
        if (!allUsers.isEmpty()) {
            model.addAttribute("success", "Data found");
            model.addAttribute("allUser", allUsers);
        } else {
            model.addAttribute("error", "No user found.");
        }
        return "admin";
    }
    @GetMapping("/lock")
    public void lock(){

    }

}
