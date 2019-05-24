package com.module.repository;

import com.module.model.Customer;

import java.util.List;

public interface CustomerRepository {
  List<Customer> findAll();

  Customer findById(Long id);

  void save(Customer model);

  void remove(Long id);
}
