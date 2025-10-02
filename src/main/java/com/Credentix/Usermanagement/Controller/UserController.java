package com.Credentix.Usermanagement.Controller;

import com.Credentix.Usermanagement.Entity.User;
import com.Credentix.Usermanagement.Repo.UserRepository;
import com.Credentix.Usermanagement.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@ControllerAdvice
@RequestMapping("/user")
public class UserController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository repo;

    @Autowired
    private UserService service;

    @GetMapping("/")
    public String home() {
        return "user/home";
    }

    @GetMapping("/loadChangePass")
    public String loadChangePassword() {
        return "user/change_password";
    }

    @PostMapping("/changePassw")
    public String processPasswordChange(@RequestParam("oldPass") String oldPassword,
                                        @RequestParam("newPass") String newPassword,
                                        @RequestParam("confPass") String confirmPassword,
                                        Principal principal,
                                        RedirectAttributes redirectAttributes) {

        // Get logged-in user
        String email = principal.getName();
        User user = repo.findByEmail(email);

        if (user == null) {
            redirectAttributes.addFlashAttribute("error", "User not found!");
            return "redirect:/user/loadChangePass";
        }

        // Check old password
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            redirectAttributes.addFlashAttribute("error", "Old password is incorrect!");
            return "redirect:/user/loadChangePass";
        }

        // Check new password confirmation
        if (!newPassword.equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("error", "New password and Confirm password do not match!");
            return "redirect:/user/loadChangePass";
        }

        // Encode and save new password
        user.setPassword(passwordEncoder.encode(newPassword));
        repo.save(user);

        redirectAttributes.addFlashAttribute("success", "Password changed successfully!");
        return "redirect:/user/loadChangePass";
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
