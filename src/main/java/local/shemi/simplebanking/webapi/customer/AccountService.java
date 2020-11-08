/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.shemi.simplebanking.webapi.customer;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author bmg
 */
@Service
public class AccountService {

    @Autowired
    private AccountRespository repository;

    public List<Account> findAll(String customerId) {
        return repository.findByCustomerId(customerId);
    }

    public Optional<Account> findById(String id) {
        return repository.findById(id);
    }

    public void insert(Account account) {
        repository.insert(account.getId(), account.getName(), account.getCustomerId());
    }

    public void update(Account account) {
        repository.save(account);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

}
