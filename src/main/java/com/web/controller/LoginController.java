package com.web.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.entity.Admin;
import com.web.entity.Buyer;
import com.web.entity.Retailer;
import com.web.service.RegisterService;
import com.web.service.UserRegistrationDTO;


@Controller
public class LoginController {

    @Autowired
    private RegisterService registerService;

    @PostMapping("/login")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session, Model model) {
        Object user = registerService.authenticateUser(email, password);

        if (user == null) {
            model.addAttribute("errorMessage", "Not so fast! Register first if you want to be part of the bookworm family!");
            return "login"; // login.jsp
        }

        if ("blocked".equals(user)) {
            model.addAttribute("errorMessage", "Your account has been blocked. Please contact support.");
            return "login"; // login.jsp
        }

        return handleUserRedirect(user, session);
    }

    private String handleUserRedirect(Object user, HttpSession session) {
        if (user instanceof Admin) {
            Admin admin = (Admin) user;
            setSessionAttributes(session, admin);
            return "adminDashboard"; // adminDashboard.jsp
        } else if (user instanceof Buyer) {
            Buyer buyer = (Buyer) user;
            setSessionAttributes(session, buyer);
            return "redirect:/books"; // getAllBooksServlet
        } else if (user instanceof Retailer) {
            Retailer retailer = (Retailer) user;
            setSessionAttributes(session, retailer);
            return "redirect:/retailerDashboard"; // retailerDashBoard.jsp
        }
        return "login"; // login.jsp
    }

    private void setSessionAttributes(HttpSession session, Object user) {
        session.setAttribute("user", user);
        session.setAttribute("userName", getUserName(user));
        session.setAttribute("userId", getUserId(user));
    }

    private String getUserName(Object user) {
        if (user instanceof Admin) return ((Admin) user).getName();
        if (user instanceof Buyer) return ((Buyer) user).getName();
        if (user instanceof Retailer) return ((Retailer) user).getName();
        return null;
    }

    private Long getUserId(Object user) {
        if (user instanceof Admin) return ((Admin) user).getId();
        if (user instanceof Buyer) return ((Buyer) user).getId();
        if (user instanceof Retailer) return ((Retailer) user).getId();
        return null;
    }
    
    
    
    //register
    
    @PostMapping("/register")
    public String register(UserRegistrationDTO dto, Model model) {
    	System.out.println(dto.getName());
    	System.out.println(dto.getContactno());
    	System.out.println(dto.getEmail());
        String msg = registerService.registerUser(dto);

        if ("success".equals(msg)) {
            model.addAttribute("message", "Registration successful. Please log in to continue.");
            model.addAttribute("nextPage", "/login");
            model.addAttribute("buttonLabel", "Login");
            return "success"; // success.jsp
        } else {
            model.addAttribute("errorMessage", msg);
            return "register"; // register.jsp
        }
    }
}
