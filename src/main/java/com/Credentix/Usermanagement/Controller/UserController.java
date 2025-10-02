package com.Credentix.Usermanagement.Controller;

import com.Credentix.Usermanagement.Entity.User;
import com.Credentix.Usermanagement.Repo.UserRepository;
import com.Credentix.Usermanagement.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@ControllerAdvice
@RequestMapping("/user")
public class UserController {
     @Autowired
     private UserRepository repo;
     @Autowired
     private UserService service;
    @GetMapping("/")
    public String home(){
        return "user/home";
    }

    @ModelAttribute
    public void userDetails(Model model, Principal principal) {
        if (principal != null) {
            String email = principal.getName();
            User user = repo.findByEmail(email);
            model.addAttribute("loggedUser", user);
        }
    }

}
