package lambda.aaron.orders.services;

import lambda.aaron.orders.models.Customer;
import lambda.aaron.orders.repositories.CustomerRepository;
import lambda.aaron.orders.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service( value = "customerService")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepos;

    @Override
    public List<Customer> findAllCustomers() {

        List<Customer> allCustomers = new ArrayList<>();

        customerRepos.findAll()
                .iterator()
                .forEachRemaining(allCustomers::add);

        return allCustomers;

    }

    @Override
    public List<Customer> findCustomerById(long code) {

        List<Customer> customer;

        customer = customerRepos.findByCustcode(code);

        return  customer;
    }

    @Override
    public List<Customer> findCustomerWithMatchingName(String name) {

        List<Customer> customerWithLikeNames;

        customerWithLikeNames = customerRepos.findByCustnameContaining(name);

        return customerWithLikeNames;
    }
}
