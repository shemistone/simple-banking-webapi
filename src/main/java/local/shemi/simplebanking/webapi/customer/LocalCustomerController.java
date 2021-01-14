/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.shemi.simplebanking.webapi.customer;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author bmg
 */
@RestController
public class LocalCustomerController {

    @Autowired
    private LocalCustomerService service;

    @GetMapping("/local-customers/{mobile-no}")
    public ResponseEntity<Customer> getLocalCustomer(@PathVariable("mobile-no") String mobileNo) {
        Optional<Customer> localCustomer = service.find(mobileNo);
        if (localCustomer.isPresent()) {
            return ResponseEntity.of(localCustomer);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

}
