/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.shemi.simplebanking.webapi.agent;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author bmg
 */
@Service
public class AgentService {

    @Autowired
    private AgentRepository repository;

    public Optional<Agent> find(String agentNo) {
        return repository.findById(agentNo);
    }

}
