package com.module.repository;

import com.module.model.Customer;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepository {

  //lay ve cac entity
  @PersistenceContext
  private EntityManager em;

  @Override
  public List<Customer> findAll() {
    //tao truy van dong
    TypedQuery<Customer> query = em.createQuery("select c from Customer c", Customer.class);
    return query.getResultList();
  }

  @Override
  public Customer findById(Long id) {
    TypedQuery<Customer> query = em.createQuery("select c from Customer c where  c.id=:id", Customer.class);
    query.setParameter("id", id);
    try {
      return query.getSingleResult();
    }catch (NoResultException e){
      return null;
    }
  }

  @Transactional
  @Override
  public void save(Customer customer) {
    if(customer.getId() != null){
      //merge: update object to db
      em.merge(customer);
    } else {
      //persist: add object in db
      em.persist(customer);
    }
  }

  @Transactional
  @Override
  public void remove(Long id) {
    Customer customer = findById(id);
    if(customer != null){
      em.remove(customer);
    }
  }
}