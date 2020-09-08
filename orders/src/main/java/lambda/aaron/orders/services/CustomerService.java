package lambda.aaron.orders.services;

import lambda.aaron.orders.models.Customer;

import java.util.List;


public interface CustomerService {
    List<Customer> findAllCustomers();

    List<Customer> findCustomerById(long code);

    List<Customer> findCustomerWithMatchingName(String name);
}
