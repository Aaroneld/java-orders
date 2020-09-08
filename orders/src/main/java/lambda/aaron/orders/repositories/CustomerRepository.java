package lambda.aaron.orders.repositories;

import org.springframework.data.repository.CrudRepository;
import lambda.aaron.orders.models.Customer;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long>
{
    List<Customer> findByCustcode(long code);

    List<Customer> findByCustnameContaining(String name);
}
