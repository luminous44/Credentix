package com.Credentix.Usermanagement.Controller;

import com.Credentix.Usermanagement.Entity.User;
import com.Credentix.Usermanagement.Repo.UserRepository;
import com.Credentix.Usermanagement.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private  AdminService service;
    @GetMapping("/")

    public String admin(){
        return "admin";
    }

    @GetMapping("/availableUser")
    public ModelAndView findAllUser() {
      List<User> allUsers = service.findAllUser();
        return new ModelAndView("admin", "allUser", allUsers);
    }



}
