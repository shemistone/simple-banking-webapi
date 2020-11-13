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
public class ClientPermissionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ClientPermission> findAllByClientId(String clientId) {
        final String SQL = "select * from client_permission_detail where username = ?";
        return jdbcTemplate.query(SQL, new ClientPermissionRowMapper(), clientId);
    }

    public ClientPermission findByClientId(String clientId) {
        final String SQL = "select * from client_permission_detail where username = ?";
        return jdbcTemplate.queryForObject(SQL, new ClientPermissionRowMapper(), clientId);
    }

    private static class ClientPermissionRowMapper implements RowMapper<ClientPermission> {

        @Override
        public ClientPermission mapRow(ResultSet resultSet, int i) throws SQLException {
            ClientPermission clientPermission = new ClientPermission();
            clientPermission.setId(resultSet.getString("id"));
            Client client = new Client(resultSet.getString("username"), resultSet.getString("password"));
            clientPermission.setClient(client);
            Permission permission = new Permission(resultSet.getString("name"));
            clientPermission.setPermission(permission);
            return clientPermission;
        }

    }
}
