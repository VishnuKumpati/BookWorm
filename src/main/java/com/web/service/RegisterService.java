package com.web.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.web.entity.Admin;
import com.web.entity.Buyer;
import com.web.entity.Retailer;
import com.web.repositary.AdminRepository;
import com.web.repositary.BuyerRepository;
import com.web.repositary.RetailerRepository;



@Service
public class RegisterService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private BuyerRepository buyerRepository;

    @Autowired
    private RetailerRepository retailerRepository;

    public Object authenticateUser(String email, String password) {
        // Authenticate Admin
        Admin admin = adminRepository.findByEmailAndPassword(email, password);
        if (admin != null) return admin;

        // Authenticate Buyer
        Buyer buyer = buyerRepository.findByEmailAndPassword(email, password);
        if (buyer != null) {
            if ("blocked".equalsIgnoreCase(buyer.getStatus())){
                return "blocked";
            }
            return buyer;
        }

        // Authenticate Retailer
        Retailer retailer = retailerRepository.findByEmailAndPassword(email, password);
        if (retailer != null) {
            if ("blocked".equalsIgnoreCase(retailer.getStatus())) {
                return "blocked";
            }
            return retailer;
        }

        return null; // User not found
    }

    public String registerUser(UserRegistrationDTO dto) {
    	
        // Check if email or contact number already exists
        if (adminRepository.existsByDetails(dto.getEmail(),dto.getContactno()) || 
            buyerRepository.existsByDetails(dto.getEmail(),dto.getContactno()) ||
            retailerRepository.existsByDetails(dto.getEmail(),dto.getContactno())) {
            return "Email already registered.";
        }
        if ("Admin".equals(dto.getUserType())) {
            if (!"9".equals(dto.getAdminPassword())) {
                return "Invalid admin password.";
            }
            Admin admin = new Admin();
            admin.setName(dto.getName());
            admin.setEmail(dto.getEmail());
            admin.setContactNo(dto.getContactno());
            admin.setPassword(dto.getPassword());
            adminRepository.save(admin);
        } else if ("Buyer".equals(dto.getUserType())) {
        	System.out.println("user buyer");
            Buyer buyer = new Buyer();
            buyer.setName(dto.getName());
            buyer.setEmail(dto.getEmail());
            buyer.setPassword(dto.getPassword());
            buyer.setAge(dto.getAge());
            buyer.setContactNo(dto.getContactno());
            buyer.setCity(dto.getCity());
            buyer.setUserType(dto.getUserType());
            buyerRepository.save(buyer);
        } else if ("Retailer".equals(dto.getUserType())) {
        	System.out.println("user buyer");
            Retailer retailer = new Retailer();
            retailer.setName(dto.getName());
            retailer.setEmail(dto.getEmail());
            retailer.setCity(dto.getCity());
            retailer.setContactNumber(dto.getContactno());
            retailer.setPassword(dto.getPassword());
            retailerRepository.save(retailer);
        } else {
            return "Invalid user type.";
        }

        return "success";
    }
}
