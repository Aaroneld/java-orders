package lambda.aaron.orders.services;

import lambda.aaron.orders.models.Order;
import lambda.aaron.orders.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepo;

    @Override
    public List<Order> getAllOrders() {
        List<Order> allOrders = new ArrayList<>();

        orderRepo.findAll()
                .iterator()
                .forEachRemaining(allOrders::add);

        return allOrders;
    }
}
