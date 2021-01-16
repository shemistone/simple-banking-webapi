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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author shemistone
 */
@Repository
public class TransactionTotalRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Optional<TransactionTotal> findByMobileNoAndProcessingCode(String mobileNo, String processingCode) {
        final String SQL = ""
                + "select "
                + "amt "
                + "from "
                + "vw_daily_limits "
                + "where "
                + "phoneno = ? "
                + "and "
                + "trntype = ?";
        TransactionTotal total = jdbcTemplate.queryForObject(SQL,
                new TransactionTotalRowMapper(), mobileNo, processingCode);
        if (total != null) {
            return Optional.of(total);
        } else {
            TransactionTotal transactionTotal = new TransactionTotal();
            transactionTotal.setAmount(BigDecimal.ZERO);
            return Optional.of(transactionTotal);
        }

    }

    private static class TransactionTotalRowMapper implements RowMapper<TransactionTotal> {

        @Override
        public TransactionTotal mapRow(ResultSet resultSet, int i) throws SQLException {
            TransactionTotal transactionTotal = new TransactionTotal();
            transactionTotal.setAmount(new BigDecimal(resultSet.getString("amt")));
            return transactionTotal;
        }
    }

}
