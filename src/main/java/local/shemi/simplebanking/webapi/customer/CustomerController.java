/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.shemi.simplebanking.webapi.customer;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author bmg
 */
@RestController
public class CustomerController {

    @Autowired
    private CustomerService service;

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> find(@PathVariable String id) {
        return ResponseEntity.ok(service.find(id));
    }

    @PostMapping("/customers")
    public ResponseEntity<Customer> update(@RequestBody Customer customer) {
        service.insert(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<Customer> update(@RequestBody Customer customer, @PathVariable String id) {
        service.update(customer);
        return ResponseEntity.ok(customer);
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<Customer> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}
