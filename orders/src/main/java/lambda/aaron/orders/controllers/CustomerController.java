package lambda.aaron.orders.controllers;

import lambda.aaron.orders.models.Customer;
import lambda.aaron.orders.repositories.CustomerRepository;
import lambda.aaron.orders.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/all")
    public ResponseEntity<?> findAllCustomers()
    {
        List<Customer> allCustomers = customerService.findAllCustomers();

        return new ResponseEntity<>(allCustomers, HttpStatus.OK);
    }

    @GetMapping( value = "/{code}")
    public ResponseEntity<?> findCustomerById(
            @PathVariable long code
    )
    {
        List<Customer> customer = customerService.findCustomerById(code);

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping( value = "/namelike/{name}")
    public ResponseEntity<?> findByCustomerByLikeName(
            @PathVariable String name
    )
    {
        List<Customer> customersOfLikeNames = customerService.findCustomerWithMatchingName(name);

        return new ResponseEntity<>(customersOfLikeNames, HttpStatus.OK);
    }

}
