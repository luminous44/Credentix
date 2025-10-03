package com.Credentix.Usermanagement.Controller;

import com.Credentix.Usermanagement.Entity.User;
import com.Credentix.Usermanagement.Repo.UserRepository;
import com.Credentix.Usermanagement.Service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {

    @Autowired
    private UserService service;

    @Autowired
    private UserRepository repository;

    // ✅ Do not make this static
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/signIn")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model, HttpSession session) {
        model.addAttribute("user", new User());
        return "register";
    }

    @GetMapping("/loadForgetPassword")
    public String loadForgetPassword() {
        return "forget_password";
    }

    @PostMapping("/createUser")
    public String createUser(@ModelAttribute User user, HttpSession session) {
        if (service.isExistUser(user.getEmail())) {
            session.setAttribute("msg", "Already an account is associated with this email!! Try again");
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRole("USER");
            User createdUser = service.createUser(user);
            if (createdUser != null) {
                session.setAttribute("msg", "Registration successful");
            } else {
                session.setAttribute("msg", "Something went wrong!! Try again");
            }
        }
        return "redirect:/register";
    }

    @PostMapping("/forgetPassword")
    public String forgetPassword(@RequestParam String email,
                                 @RequestParam String phone,
                                 RedirectAttributes attributes) {

        User user = repository.findByEmailAndPhone(email, phone);

        if (user != null) {
            return "redirect:/loadResetPassword?uid=" + user.getId();
        }

        attributes.addFlashAttribute("error", "Invalid email or phone");
        return "redirect:/loadForgetPassword";
    }

    @GetMapping("/loadResetPassword")
    public String loadResetPassword(@RequestParam Integer uid, Model model) {
        model.addAttribute("uid", uid);
        return "reset_password";
    }

    @PostMapping("/resetPassword")
    public String resetPassword(@RequestParam Integer uid,
                                @RequestParam("newPass") String newPassword,
                                @RequestParam("confPass") String confirmPassword,
                                RedirectAttributes attributes) {

        User user = repository.findById(uid).orElse(null);

        if (user == null) {
            attributes.addFlashAttribute("error", "User not found");
            return "redirect:/loadForgetPassword";
        }

        if (!newPassword.equals(confirmPassword)) {
            attributes.addFlashAttribute("error", "Passwords do not match");
            return "redirect:/loadResetPassword?uid=" + uid;
        }

        user.setPassword(passwordEncoder.encode(confirmPassword));
        repository.save(user);

        attributes.addFlashAttribute("success", "Password changed successfully. Please login again.");
        return "redirect:/signIn";
    }
}
