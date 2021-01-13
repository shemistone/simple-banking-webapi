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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author bmg
 */
@RestController
public class AccountController {

    @Autowired
    private AccountService service;

    @GetMapping("/accounts/{account-no}")
    public ResponseEntity<Account> findOne(@PathVariable("account-no") String accountNo) {
        return ResponseEntity.of(service.findByAccountNo(accountNo));
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> find(@RequestParam("customer-code") String customerCode) {
        return ResponseEntity.ok(service.findByCustomerCode(customerCode));
    }

    @GetMapping("/customers/{customerCode}/accounts")
    public ResponseEntity<List<Account>> findCustomerAccounts(@PathVariable String customerCode) {
        return ResponseEntity.ok(service.findByCustomerCode(customerCode));
    }

    @GetMapping("/customers/{customerCode}/accounts/{id}")
    public ResponseEntity<Account> findCustomerAccount(@PathVariable String customerCode, @PathVariable String id) {
        return ResponseEntity.of(service.findById(id));
    }

    @PostMapping("/customers/{customerCode}/accounts")
    public void insert(@RequestBody Account account, @PathVariable String customerCode) {
        account.setCustomerCode(customerCode);
        service.insert(account);
    }

    @PutMapping("/customers/{customerCode}/accounts/{id}")
    public void update(@RequestBody Account account, @PathVariable String customerCode, @PathVariable String id) {
        account.setCustomerCode(customerCode);
        service.update(account);
    }

    @DeleteMapping("/customers/{customerCode}/accounts/{id}")
    public ResponseEntity<Customer> delete(@PathVariable String customerCode, @PathVariable String id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}
