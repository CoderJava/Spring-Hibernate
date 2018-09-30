package com.tokobuku.dao.impl;

import com.tokobuku.dao.CustomerDao;
import com.tokobuku.model.Customer;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class CustomerDaoImpl implements CustomerDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Transactional
    @Override
    public void insert(Customer customer) {
        sessionFactory.getCurrentSession()
                .save(customer);
    }

    @Transactional
    @Override
    public Customer findByCustomerId(int custId) {
        return (Customer) sessionFactory.getCurrentSession()
                .createQuery("from Customer where " +
                        "cust_id=:custId")
                .setParameter("custId", custId)
                .list()
                .get(0);
    }

    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public List<Customer> findAllCustomers() {
        return (List<Customer>) sessionFactory.getCurrentSession()
                .createQuery("from Customer")
                .list();
    }

    @Transactional
    @Override
    public void update(Customer customer) {
        sessionFactory.getCurrentSession()
                .update(customer);
    }

    @Transactional
    @Override
    public void delete(int custId) {
        Customer customer = findByCustomerId(custId);
        sessionFactory.getCurrentSession()
                .delete(customer);
    }
}
