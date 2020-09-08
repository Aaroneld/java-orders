package lambda.aaron.orders.repositories;

import org.springframework.data.repository.CrudRepository;
import lambda.aaron.orders.models.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
