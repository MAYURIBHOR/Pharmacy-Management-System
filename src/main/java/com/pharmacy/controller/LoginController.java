package com.pharmacy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    // Hardcoded users with strong passwords
    private final String adminUsername = "admin";
    private final String adminPassword = "Admin@123";

    private final String userUsername = "user";
    private final String userPassword = "User@123";

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String loginVerify(@RequestParam String username,
            @RequestParam String password,
            Model model) {

        // Password validation: min 8 chars, 1 uppercase, 1 lowercase, 1 number, 1
        // special char
        String pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}$";
        if (!password.matches(pattern)) {
            model.addAttribute("error",
                    "Password must be at least 8 characters, include 1 uppercase, 1 lowercase, 1 number, and 1 special character.");
            return "login";
        }

        // Check credentials
        if (username.equals(adminUsername) && password.equals(adminPassword)) {
            return "redirect:/admin/dashboard";
        } else if (username.equals(userUsername) && password.equals(userPassword)) {
            return "redirect:/user/dashboard";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }
}
