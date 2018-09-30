package com.tokobuku.bo.impl;

import com.tokobuku.bo.CustomerBo;
import com.tokobuku.dao.CustomerDao;
import com.tokobuku.model.Customer;

import java.util.List;

public class CustomerBoImpl implements CustomerBo {

    private CustomerDao customerDao;

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public CustomerDao getCustomerDao() {
        return customerDao;
    }

    public void insert(Customer customer) {
        customerDao.insert(customer);
    }

    public Customer findByCustomerId(int custId) {
        return customerDao.findByCustomerId(custId);
    }

    public List<Customer> findAllCustomers() {
        return customerDao.findAllCustomers();
    }

    @Override
    public void update(Customer customer) {
        customerDao.update(customer);
    }

    @Override
    public void delete(int custId) {
        customerDao.delete(custId);
    }

    @Override
    public void batch(Customer customer) {
        customerDao.batch(customer);
    }
}
