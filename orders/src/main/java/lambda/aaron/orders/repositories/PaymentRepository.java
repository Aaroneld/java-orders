package lambda.aaron.orders.repositories;

import org.springframework.data.repository.CrudRepository;
import lambda.aaron.orders.models.Payment;
public interface PaymentRepository extends CrudRepository<Payment, Long> {
}
