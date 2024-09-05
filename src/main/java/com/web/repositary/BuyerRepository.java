package com.web.repositary;

import com.web.entity.Buyer;

public interface BuyerRepository {
    Buyer findByEmailAndPassword(String email, String password);

	boolean existsByDetails(String email, Long contactno);

	void save(Buyer buyer);
}
