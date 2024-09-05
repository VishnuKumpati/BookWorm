package com.web.repositary.impl;

import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.entity.Buyer;
import com.web.repositary.BuyerRepository;

@Repository
public class BuyerRepositoryImpl implements BuyerRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Buyer findByEmailAndPassword(String email, String password) {
        Session session = sessionFactory.openSession();
        Query<Buyer> query = session.createQuery("FROM Buyer WHERE email = :email AND password = :password", Buyer.class);
        query.setParameter("email", email);
        query.setParameter("password", password);
        return query.uniqueResult();
    }

    @Override
	public boolean existsByDetails(String email, Long contactno) {
    	
		 Session session = sessionFactory.openSession();
	        try {
	            String hql = "SELECT COUNT(*) FROM Buyer WHERE email = :email or contactNo = :contactno";
	            Query<Long> query = session.createQuery(hql, Long.class);
	            query.setParameter("email", email);
	            query.setParameter("contactno", contactno);
	            Long count = query.uniqueResult();
	            System.out.println("from buyer check"+count);
	            return count > 0;
	        } finally {
	            session.close();
	        }

    }

	@Override
	public void save(Buyer buyer) {
		Session session=sessionFactory.openSession();
		Transaction transaction=null;
		try {
            transaction = session.beginTransaction();
            session.persist(buyer); // Save the entity
            transaction.commit(); // Commit the transaction
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Rollback in case of an error
            }
            e.printStackTrace(); // Log or handle the exception as needed
        } finally {
            session.close(); // Always close the session
        }
		
	}
	
}
