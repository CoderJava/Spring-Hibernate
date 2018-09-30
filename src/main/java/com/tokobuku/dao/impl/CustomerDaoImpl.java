package com.tokobuku.dao.impl;

import com.tokobuku.dao.CustomerDao;
import com.tokobuku.model.Customer;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void batch(Customer customer) {
        /* Operasi Batch maksudnya adalah kita ada melakukan 2 operasi sql dimana, semuanya harus sukses atau jika salah
        * satu gagal operasinya maka, operasinya sebelumnya harus di-rollback agar menjaga konsistensi data. Contohnya,
        * pertama input data user kemudian, edit data user namun, gagal ketika edit data user maka, data tersebut
        * diharuskan rollback dan datanya yang sudah di-insert tadi jadi di-rollback
        * */
        try {
            sessionFactory.getCurrentSession().save(customer);
            customer.setFullName("user batch");
            sessionFactory.getCurrentSession().update(customer);
            /*customer.setCustId(900);
            sessionFactory.getCurrentSession().delete(customer);*/

            // Coba buat error dan apakah rollback akan terjadi
            Customer customerError = (Customer) sessionFactory.getCurrentSession()
                    .createQuery("from Customer " +
                            "where cust_id=:custId")
                    .setParameter("custId", 900)
                    .list()
                    .get(0);
        } catch (Exception e) {
            e.printStackTrace();
            // Trigger rollback programatically
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }
}
