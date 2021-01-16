/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.shemi.simplebanking.webapi.transaction;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author shemistone
 */
@RestController
public class TransactionLimitController {

    @Autowired
    private TransactionLimitService service;

    @GetMapping("/transaction-limits/{id}")
    public ResponseEntity<TransactionLimit> get(@PathVariable("id") String id) {
        Optional<TransactionLimit> transactionLimit = service.find(id);
        if (transactionLimit.isPresent()) {
            return ResponseEntity.of(transactionLimit);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
    
}
