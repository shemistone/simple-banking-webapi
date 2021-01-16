/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.shemi.simplebanking.webapi.transaction;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import local.shemi.simplebanking.webapi.setting.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author shemistone
 */
@Repository
public class TransactionLimitRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Optional<TransactionLimit> findById(String id) {
        final String SQL = ""
                + "select "
                + "id, "
                + "min_value, "
                + "max_value "
                + "from "
                + "tb_transaction_limits "
                + "where "
                + "id = ? ";
        TransactionLimit limit = jdbcTemplate.queryForObject(SQL, new TransactionLimitRowMapper(), id);
        if (limit != null) {
            return Optional.of(limit);
        } else {
            TransactionLimit transactionLimit = new TransactionLimit();
            transactionLimit.setId(id);
            transactionLimit.setMin(new BigDecimal("1000"));
            transactionLimit.setMax(new BigDecimal("400000"));
            return Optional.of(transactionLimit);
        }
    }

    private static class TransactionLimitRowMapper implements RowMapper<TransactionLimit> {

        @Override
        public TransactionLimit mapRow(ResultSet resultSet, int i) throws SQLException {
            TransactionLimit transactionLimit = new TransactionLimit();
            transactionLimit.setId(resultSet.getString("id"));
            transactionLimit.setMin(new BigDecimal(resultSet.getString("min_value")));
            transactionLimit.setMax(new BigDecimal(resultSet.getString("max_value")));
            return transactionLimit;
        }

    }
}
