package com.web.repositary;

import com.web.entity.Admin;

public interface AdminRepository {
    Admin findByEmailAndPassword(String email, String password);

	boolean existsByDetails(String email, Long contactno);

	void save(Admin admin);
}

