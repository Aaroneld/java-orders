package lambda.aaron.orders.controllers;

import lambda.aaron.orders.models.Order;
import lambda.aaron.orders.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping( value = "/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping( value = "/all")
    public ResponseEntity<?> getAllOrders()
    {
        List<Order> allOrders = orderService.getAllOrders();

        return new ResponseEntity<>(allOrders, HttpStatus.OK);
    }
}
