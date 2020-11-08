/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.shemi.simplebanking.webapi.customer;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
public class AccountController {

    @Autowired
    private AccountService service;

    @GetMapping("/customers/{customerId}/accounts")
    public ResponseEntity<List<Account>> findAll(@PathVariable String customerId) {
        return ResponseEntity.ok(service.findAll(customerId));
    }

    @GetMapping("/customers/{customerId}/accounts/{id}")
    public ResponseEntity<Account> findAll(@PathVariable String customerId, @PathVariable String id) {
        return ResponseEntity.of(service.findById(id));
    }

    @PostMapping("/customers/{customerId}/accounts")
    public void insert(@RequestBody Account account, @PathVariable String customerId) {
        account.setCustomerId(customerId);
        service.insert(account);
    }

    @PutMapping("/customers/{customerId}/accounts/{id}")
    public void update(@RequestBody Account account, @PathVariable String customerId, @PathVariable String id) {
        account.setCustomerId(customerId);
        service.update(account);
    }

    @DeleteMapping("/customers/{customerId}/accounts/{id}")
    public ResponseEntity<Customer> delete(@PathVariable String customerId, @PathVariable String id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}
