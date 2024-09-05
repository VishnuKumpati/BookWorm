package com.web.repositary.impl;
import org.hibernate.Session;


import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.entity.Admin;
import com.web.repositary.AdminRepository;



@Repository
public class AdminRepositoryImpl implements AdminRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Admin findByEmailAndPassword(String email, String password) {
        Session session = sessionFactory.openSession();
        Query<Admin> query = session.createQuery("FROM Admin WHERE email = :email AND password = :password", Admin.class);
        query.setParameter("email", email);
        query.setParameter("password", password);
        return query.uniqueResult();
    }

    @Override
    public boolean existsByDetails(String email, Long contactno) {
     
        Session session = sessionFactory.openSession();
        try {
            String hql = "SELECT COUNT(*) FROM Admin WHERE email = :email OR ContactNo = :contactno";
            Query<Long> query = session.createQuery(hql, Long.class);
            query.setParameter("email", email);
            query.setParameter("contactno", contactno);
            Long count = query.uniqueResult();
            System.out.println("from admin check"+count);
            return count > 0;
        } finally {
            session.close();
        }
    }


	 @Override
	    public void save(Admin admin) {
		 System.out.println(admin.getEmail());
	        Session session = sessionFactory.openSession();
	       Transaction transaction = null;
	        try {
	            transaction = session.beginTransaction();
	            session.persist(admin); // Save the entity
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
