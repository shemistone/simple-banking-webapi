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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author shemistone
 */
@RestController
public class TransactionTotalController {

    @Autowired
    private TransactionTotalService service;

    @GetMapping("/transaction-totals/{mobile-no}")
    public ResponseEntity<TransactionTotal> get(@PathVariable("mobile-no") String mobileNo,
            @RequestParam("processing-code") String processingCode) {
        Optional<TransactionTotal> transactionTotal = service.find(mobileNo, processingCode);
        if (transactionTotal.isPresent()) {
            return ResponseEntity.of(transactionTotal);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

}
