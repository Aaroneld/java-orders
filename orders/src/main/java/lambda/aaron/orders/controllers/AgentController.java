package lambda.aaron.orders.controllers;

import lambda.aaron.orders.models.Agent;

import lambda.aaron.orders.services.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping( value = "/agents")
public class AgentController {

    @Autowired
    private AgentService agentService;

    @GetMapping( value = "/all")
    public ResponseEntity<?> listAllAgents()
    {
        List<Agent> allAgents = agentService.findAllAgents();

        return new ResponseEntity<>(allAgents, HttpStatus.OK);
    }

    @GetMapping(value = "/{code}")
    public ResponseEntity<?> getAgentById(
            @PathVariable long code
    )
    {
        List<Agent> agent = agentService.findAgentById(code);

        return new ResponseEntity<>(agent, HttpStatus.OK);
    }
}
