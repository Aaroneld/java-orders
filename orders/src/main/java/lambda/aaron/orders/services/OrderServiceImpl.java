package lambda.aaron.orders.services;

import lambda.aaron.orders.models.Payment;
import lambda.aaron.orders.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("orderservice")
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepo;

}
