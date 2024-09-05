package com.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class WelcomeController {

    @RequestMapping("/")
    public String welcome() {
        return "home"; // Make sure 'home.jsp' or 'home.html' exists in the templates directory
    }

    @GetMapping("/login")
    public String showLoginPage(HttpSession session, Model model) {
        model.addAttribute("errorMessage", null); // Clear any previous error messages
        return "login"; // Ensure 'login.jsp' or 'login.html' exists
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register"; // Ensure 'register.jsp' or 'register.html' exists
    }
}
