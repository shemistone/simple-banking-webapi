package local.shemi.simplebanking.webapi.agent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * Created by shemistone on 15/07/17.
 */
@Repository
public class AgentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Optional<Agent> findById(String agentNo) {
        final String SQL = ""
                + "select "
                + "agentid, "
                + "agenttype, "
                + "names, "
                + "account, "
                + "address, "
                + "phone, "
                + "device, "
                + "devicemobile, "
                + "password, "
                + "disabled, "
                + "approved, "
                + "limit, "
                + "lang, "
                + "branchid, "
                + "creationdate "
                + "from "
                + "tb_agents "
                + "where "
                + "agentid = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(SQL, new AgentRowMapper(), agentNo));
    }

    private static class AgentRowMapper implements RowMapper<Agent> {

        @Override
        public Agent mapRow(ResultSet resultSet, int i) throws SQLException {
            Agent agent = new Agent();
            agent.setId(resultSet.getString("agentid"));
            agent.setType(resultSet.getString("agenttype"));
            agent.setDisabled(resultSet.getBoolean("disabled"));
            agent.setApproved(resultSet.getBoolean("approved"));
            agent.setPassword(resultSet.getString("password"));
            agent.setMobileNo(resultSet.getString("phone"));
            agent.setName(resultSet.getString("names"));
            agent.setAccountNo(resultSet.getString("account"));
            agent.setAddress(resultSet.getString("address"));
            agent.setLimit(resultSet.getBigDecimal("limit"));
            agent.setLang(resultSet.getString("lang"));
            agent.setDevice(resultSet.getString("device"));
            agent.setDeviceMobileNo(resultSet.getString("devicemobile"));
            agent.setBranch(resultSet.getString("branchid"));
            return agent;
        }

    }
}
