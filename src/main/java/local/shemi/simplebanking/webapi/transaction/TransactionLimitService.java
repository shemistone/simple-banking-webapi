/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.shemi.simplebanking.webapi.transaction;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author bmg
 */
@Service
public class TransactionLimitService {

    @Autowired
    private TransactionLimitRepository repository;

    public Optional<TransactionLimit> find(String id) {
        return repository.findById(id);
    }

}
