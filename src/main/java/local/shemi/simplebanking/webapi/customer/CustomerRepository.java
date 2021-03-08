/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.shemi.simplebanking.webapi.customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author bmg
 */
@Repository
public class CustomerRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Customer> findAll() {
        final String SQL = "select "
                + "id, "
                + "first_name, "
                + "middle_name, "
                + "last_name, "
                + "pin, "
                + "lang, "
                + "active, "
                + "mobile_no, "
                + "imsi, "
                + "failed_logins, "
                + "time_created, "
                + "time_updated "
                + "from customer";
        return jdbcTemplate.query(SQL, new CustomerRowMapper());
    }

    public Optional<Customer> findByMobileNo(String id) {
        final String SQL = "select "
                + "id, "
                + "first_name, "
                + "middle_name, "
                + "last_name, "
                + "pin, "
                + "lang, "
                + "active, "
                + "mobile_no, "
                + "imsi, failed_logins, "
                + "time_created, "
                + "time_updated "
                + "from customer "
                + "where mobile_no = ?";
        return jdbcTemplate.query(SQL, new CustomerRowMapper(), id).stream().findFirst();
    }

    public int update(Customer customer) {
        final String SQL = "update customer set "
                + "first_name = ?, "
                + "middle_name = ?, "
                + "last_name = ?, "
                + "lang = ?, "
                + "active = ?, "
                + "imsi = ?, "
                + "failed_logins = ?, "
                + "time_updated = ? "
                + "where mobile_no = ?";
        return jdbcTemplate.update(SQL, customer.getFirstName(),
                customer.getMiddleName(), customer.getLastName(), customer.getLang(),
                customer.isActive(), customer.getImsi(), customer.getFailedLogins(),
                Timestamp.valueOf(customer.getTimeUpdated()), customer.getMobileNo());
    }

    public void insert(Customer customer) {
        final String SQL = "insert into customer(id, "
                + "mobile_no, "
                + "first_name, "
                + "middle_name, "
                + "last_name, "
                + "pin, "
                + "lang, "
                + "active, "
                + "imsi, "
                + "failed_logins) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(SQL, UUID.randomUUID(), customer.getMobileNo(),
                customer.getFirstName(), customer.getMiddleName(), customer.getLastName(),
                customer.getPin(), customer.getLang(), customer.isActive(), customer.getImsi(),
                customer.getFailedLogins());
    }

    public void delete(String id) {
        final String SQL = "delete from customer where mobile_no = ?";
        jdbcTemplate.update(SQL, id);
    }

    private static class CustomerRowMapper implements RowMapper<Customer> {

        @Override
        public Customer mapRow(ResultSet resultSet, int i) throws SQLException {
            Customer customer = new Customer();
            customer.setId(resultSet.getString("id"));
            customer.setFirstName(resultSet.getString("first_name"));
            customer.setMiddleName(resultSet.getString("middle_name"));
            customer.setLastName(resultSet.getString("last_name"));
            customer.setPin(resultSet.getString("pin"));
            customer.setMobileNo(resultSet.getString("mobile_no"));
            customer.setImsi(resultSet.getString("imsi"));
            customer.setActive(resultSet.getBoolean("active"));
            customer.setFailedLogins(resultSet.getInt("failed_logins"));
            customer.setTimeCreated(resultSet.getTimestamp("time_created").toLocalDateTime());
            customer.setTimeUpdated(resultSet.getTimestamp("time_updated").toLocalDateTime());
            return customer;
        }

    }
}
