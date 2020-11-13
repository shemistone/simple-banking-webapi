/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.shemi.simplebanking.webapi.client;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author bmg
 */
@Repository
public class ClientRoleRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ClientRole> findAllByClientId(String clientId) {
        final String SQL = "select * from client_role_detail where username = ?";
        return jdbcTemplate.query(SQL, new ClientRoleRowMapper(), clientId);
    }

    public ClientRole findByClientId(String clientId) {
        final String SQL = "select * from client_role_detail where username = ?";
        return jdbcTemplate.queryForObject(SQL, new ClientRoleRowMapper(), clientId);
    }

    private static class ClientRoleRowMapper implements RowMapper<ClientRole> {

        @Override
        public ClientRole mapRow(ResultSet resultSet, int i) throws SQLException {
            ClientRole clientRole = new ClientRole();
            clientRole.setId(resultSet.getString("id"));
            Client client = new Client(resultSet.getString("username"), resultSet.getString("password"));
            clientRole.setClient(client);
            Role role = new Role(resultSet.getString("name"));
            clientRole.setRole(role);
            return clientRole;
        }

    }
}
