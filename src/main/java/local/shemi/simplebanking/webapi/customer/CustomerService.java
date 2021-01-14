/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.shemi.simplebanking.webapi.customer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author bmg
 */
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        Iterable<Customer> iterator = repository.findAll();
        iterator.forEach(customer -> customers.add(customer));
        return customers;
    }

    public Optional<Customer> find(String id) {
        return repository.findByMobileNo(id);
    }

    public void insert(Customer customer) {
        repository.insert(customer);
    }

    public void update(Customer customer) {
        customer.setTimeUpdated(LocalDateTime.now());
        repository.update(customer);
    }

    public void delete(String id) {
        repository.delete(id);
    }

}
