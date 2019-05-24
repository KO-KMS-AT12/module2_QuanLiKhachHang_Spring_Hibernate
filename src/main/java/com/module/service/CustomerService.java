package com.module.service;

import com.module.model.Customer;

import java.util.List;

public interface CustomerService {
  List<Customer> findAll();

  Customer findById(Long id);

  void save(Customer customer);

  void remove(Long id);
}
