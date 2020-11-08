/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.shemi.simplebanking.webapi.customer;

import java.util.List;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author bmg
 */
@Repository
public interface AccountRespository extends CrudRepository<Account, String> {

    List<Account> findByCustomerId(String customerId);

    @Modifying
    @Query("insert into account (id, name, customer_id) values (:id, :name, :customerCode)")
    void insert(@Param("id") String id, @Param("name") String name, @Param("customerCode") String customerCode);

}
