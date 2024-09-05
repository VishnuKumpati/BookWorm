package com.web.repositary.impl;
import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.entity.Retailer;
import com.web.repositary.RetailerRepository;


@Repository
public class RetailerRepositoryImpl implements RetailerRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Retailer findByEmailAndPassword(String email, String password) {
        Session session = sessionFactory.openSession();
        Query<Retailer> query = session.createQuery("FROM Retailer WHERE email = :email AND password = :password", Retailer.class);
        query.setParameter("email", email);
        query.setParameter("password", password);
        return query.uniqueResult();
    }

    @Override
	public boolean existsByDetails(String email,Long contactno) {
		 Session session = sessionFactory.openSession();
	        try {
	            String hql = "SELECT COUNT(*) FROM Retailer WHERE email = :email or contactNumber= :contactno";
	            Query<Long> query = session.createQuery(hql, Long.class);
	            query.setParameter("email", email);
	            query.setParameter("contactno", contactno);
	            Long count = query.uniqueResult();
	            System.out.println("from retailer check"+count);
	            return count > 0;
	        } finally {
	            session.close();
	        }
	}

    @Override
    public void save(Retailer retailer) {
        Session session = sessionFactory.openSession(); // Open a new session
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction(); // Begin the transaction
            session.persist(retailer); // Save the Retailer entity
            transaction.commit(); // Commit the transaction
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Rollback in case of an error
            }
            e.printStackTrace(); // Log the error for debugging
        } finally {
            session.close(); // Close the session in the finally block to release resources
        }
    }	
		
	
}
