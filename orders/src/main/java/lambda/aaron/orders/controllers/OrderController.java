package lambda.aaron.orders.controllers;

import lambda.aaron.orders.models.Order;
import lambda.aaron.orders.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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

    @PostMapping( value = "/order",
            consumes = "application/json")
    public ResponseEntity<?> addNewOrder(
            @Valid
            @RequestBody
                    Order newOrder
    )
    {
        newOrder.setOrdnum(0);
        newOrder = orderService.save(newOrder);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newRestaurantURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{ordnum}")
                .buildAndExpand(newOrder.getOrdnum())
                .toUri();


        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @PutMapping( value ="/order/{id}",
            consumes = "application/json")
    public ResponseEntity<?> updateOrderFull(
            @Valid
            @RequestBody
                    Order updateOrder,
            @PathVariable
                    long id
    )
    {
        updateOrder.setOrdnum(id);
        orderService.save(updateOrder);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping( value = "/order/{id}")
    public ResponseEntity<?> deleteOrder(
            @PathVariable
                long id
    )
    {
        orderService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
