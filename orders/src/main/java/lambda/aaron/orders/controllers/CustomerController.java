package lambda.aaron.orders.controllers;

import lambda.aaron.orders.models.Customer;
import lambda.aaron.orders.services.CustomerService;
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

    @PostMapping( value = "/customer",
                consumes = "application/json")
    public ResponseEntity<?> addNewCustomer(
            @Valid
            @RequestBody
                    Customer newCustomer
    )
    {
        newCustomer.setCustcode(0);
        newCustomer = customerService.save(newCustomer);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newRestaurantURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{custcode}")
                .buildAndExpand(newCustomer.getCustcode())
                .toUri();


        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @PutMapping( value ="/customer/{id}",
                consumes = "application/json")
    public ResponseEntity<?> updateCustomerFull(
            @Valid
            @RequestBody
                Customer updateCustomer,
            @PathVariable
                long id
    )
    {
        updateCustomer.setCustcode(id);
        customerService.save(updateCustomer);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping( value ="/customer/{id}",
            consumes = "application/json")
    public ResponseEntity<?> updateCustomer(
            @Valid
            @RequestBody
                    Customer updateCustomer,
            @PathVariable
                    long id
    )
    {
        customerService.update(updateCustomer, id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping( value = "/customer/{id}")
    public ResponseEntity<?> deleteCustomer(
            @PathVariable
                long id
    )
    {
        customerService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
