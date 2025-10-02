package com.Credentix.Usermanagement.Controller;

import com.Credentix.Usermanagement.Entity.User;
import com.Credentix.Usermanagement.Service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Controller
public class HomeController {

    @Autowired
    private UserService service;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @GetMapping("/")
    public String index(){
        return "index";
    }
    @GetMapping("/signIn")
    public String login(){
        return "login";
    }
    @GetMapping("/register")
    public String register(Model model,HttpSession session){
        model.addAttribute("user", new User());

        return "register";
    }
    @PostMapping("/createUser")
    public String createUser(@ModelAttribute User user, HttpSession session){
       if (service.isExistUser(user.getEmail())){
           session.setAttribute("msg","Already an account is associated with this email!! Try again");
       }else {
           user.setPassword(passwordEncoder.encode(user.getPassword()));
           user.setRole("USER");
           User createdUser = service.createUser(user);
           if(createdUser!=null){
               session.setAttribute("msg","Registration successful");
           }
           else {
               session.setAttribute("msg","Something went wrong!! Try again");
           }
       }
        return ("redirect:/register");
    }
}
