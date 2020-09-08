package lambda.aaron.orders.services;

import lambda.aaron.orders.models.Agent;
import lambda.aaron.orders.repositories.AgentRepository;
import lambda.aaron.orders.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "agentService")
public class AgentServiceImpl implements AgentService
{
    @Autowired
    private AgentRepository agentRepos;

    @Autowired
    private CustomerRepository customerRepos;

    @Override
    public List<Agent> findAllAgents() {

        List<Agent> allAgents = new ArrayList<>();

        agentRepos.findAll()
                .iterator()
                .forEachRemaining(allAgents::add);

        return allAgents;
    }

    @Override
    public List<Agent> findAgentById(long code) {

        List<Agent> agent = new ArrayList<>();

        agent = agentRepos.findByAgentcode(code);

        return agent;
    }
}
