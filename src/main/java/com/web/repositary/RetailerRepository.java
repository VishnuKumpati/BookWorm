package com.web.repositary;

import com.web.entity.Retailer;

public interface RetailerRepository {
    Retailer findByEmailAndPassword(String email, String password);

	boolean existsByDetails(String email, Long contactno);

	void save(Retailer retailer);
}
