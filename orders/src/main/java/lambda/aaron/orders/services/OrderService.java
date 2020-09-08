package lambda.aaron.orders.services;

import lambda.aaron.orders.models.Order;
import java.util.List;

public interface OrderService {

    List<Order> getAllOrders();

    Order save(Order order);

    void delete(long id);
}
