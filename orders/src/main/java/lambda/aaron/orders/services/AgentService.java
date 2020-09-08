package lambda.aaron.orders.services;

import lambda.aaron.orders.models.Agent;

import java.util.List;

public interface AgentService {

    List<Agent> findAllAgents();

    List<Agent> findAgentById(long code);
}
