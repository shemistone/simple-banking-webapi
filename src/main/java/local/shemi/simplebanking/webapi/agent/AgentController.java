package local.shemi.simplebanking.webapi.agent;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by shemistone on 15/07/17.
 */
@RestController
public class AgentController {

    @Autowired
    private AgentService service;

    @GetMapping("/agents/{agent-no}")
    public ResponseEntity<Agent> get(@PathVariable("agent-no") String agentNo) {
        Optional<Agent> agent = service.find(agentNo);
        if (agent.isPresent()) {
            return ResponseEntity.of(agent);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

}
