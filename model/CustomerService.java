/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author HoeZhun
 */
public class CustomerService {

    @PersistenceContext
    EntityManager mgr;
    @Resource
    Query query;

    public CustomerService(EntityManager mgr) {
        this.mgr = mgr;
    }

    public List<Customer> findAll() {
        List customerList = mgr.createNamedQuery("Customer.findAll").getResultList();
        return customerList;
    }

    public boolean addCustomer(Customer customer) {
        mgr.persist(customer);
        return true;
    }
}
