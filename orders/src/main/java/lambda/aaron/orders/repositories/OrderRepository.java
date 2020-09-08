package lambda.aaron.orders.repositories;

import org.springframework.data.repository.CrudRepository;
import lambda.aaron.orders.models.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
