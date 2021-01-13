/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.shemi.simplebanking.webapi.customer;

import java.util.List;
import java.util.Optional;
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

    @Override
    @Query("select account_no, account_name, customer_code from vw_accounts where account_no = :id")
    Optional<Account> findById(@Param("id") String id);

    @Query("select account_no, account_name, customer_code from vw_accounts where account_no = :accountNo")
    Optional<Account> findByAccountNo(@Param("accountNo") String accountNo);

    @Query("select account_no, account_name, customer_code from  vw_accounts where customer_code = :customerCode")
    List<Account> findByCustomerCode(@Param("customerCode") String customerCode);

    @Modifying
    @Query("insert into account (id, name, customer_code) values (:id, :name, :customerCode)")
    void insert(@Param("id") String id, @Param("name") String name, @Param("customerCode") String customerCode);

}
