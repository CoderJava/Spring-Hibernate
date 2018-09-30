package com.tokobuku.dao;

import com.tokobuku.model.Customer;

import java.util.List;

public interface CustomerDao {

    void insert(Customer customer);

    Customer findByCustomerId(int custId);

    List<Customer> findAllCustomers();

    void update(Customer customer);

    void delete(int custId);

    void batch(Customer customer);

}
