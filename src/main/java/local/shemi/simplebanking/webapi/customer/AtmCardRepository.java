/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.shemi.simplebanking.webapi.customer;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author bmg
 */
@Repository
public interface AtmCardRepository extends CrudRepository<AtmCard, String> {

    List<AtmCard> findByAccountId(String accountId);

}
