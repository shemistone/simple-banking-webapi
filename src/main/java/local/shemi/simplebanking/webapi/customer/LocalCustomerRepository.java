/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.shemi.simplebanking.webapi.customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author bmg
 */
@Repository
public class LocalCustomerRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Optional<Customer> findByMobileNo(String id) {
        final String SQL = ""
                + "select "
                + "id, "
                + "accountnumber, "
                + "phonenumber "
                + "from "
                + "tb_acc_mobile_map "
                + "where "
                + "phonenumber = ? "
                + "and "
                + "authorized = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(SQL, new CustomerRowMapper(), id, true));
    }

    private static class CustomerRowMapper implements RowMapper<Customer> {

        @Override
        public Customer mapRow(ResultSet resultSet, int i) throws SQLException {
            Customer customer = new Customer();
            customer.setId(resultSet.getString("id"));
            customer.setMobileNo(resultSet.getString("phonenumber"));
            List<Account> accounts = new ArrayList<>();
            Account account = new Account();
            account.setId(resultSet.getString("accountnumber"));
            accounts.add(account);
            customer.setAccounts(accounts);
            return customer;
        }

    }

}
