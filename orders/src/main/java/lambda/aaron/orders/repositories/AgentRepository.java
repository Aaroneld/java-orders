package lambda.aaron.orders.repositories;

import lambda.aaron.orders.models.Agent;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AgentRepository extends CrudRepository<Agent, Long> {

    List<Agent> findByAgentcode(long code);
}
