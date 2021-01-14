/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.shemi.simplebanking.webapi.setting;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
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
public class SettingRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Setting> findAll() {
        final String SQL = ""
                + "select "
                + "paramname, "
                + "paramvalue "
                + "from "
                + "tb_generalparams";
        return jdbcTemplate.query(SQL, new SettingRowMapper());
    }

    public Optional<Setting> findByName(String name) {
        final String SQL = ""
                + "select "
                + "paramname, "
                + "paramvalue "
                + "from "
                + "tb_generalparams "
                + "where "
                + "paramname = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(SQL, new SettingRowMapper(), name));
    }

    private static class SettingRowMapper implements RowMapper<Setting> {

        @Override
        public Setting mapRow(ResultSet resultSet, int i) throws SQLException {
            Setting setting = new Setting();
            setting.setId(resultSet.getString("paramname"));
            setting.setValue(resultSet.getString("paramvalue"));
            return setting;
        }

    }
}
