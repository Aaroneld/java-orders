package lambda.aaron.orders.services;

import lambda.aaron.orders.models.Customer;
import lambda.aaron.orders.models.Order;
import lambda.aaron.orders.models.Agent;
import lambda.aaron.orders.repositories.AgentRepository;
import lambda.aaron.orders.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service( value = "customerService")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepos;

    @Autowired
    AgentRepository agentrepos;

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

    @Override
    @Transactional
    public Customer save(Customer customer)
    {
        Customer newCustomer = new Customer();

        if(customer.getCustcode() != 0)
        {
            customerRepos.findById(customer.getCustcode())
                    .orElseThrow(() -> new EntityNotFoundException("Customer " + customer.getCustcode()
                    + " Not Found"));
            newCustomer.setCustcode(customer.getCustcode());
        }

        newCustomer.setCustcity(customer.getCustcity());
        newCustomer.setCustname(customer.getCustname());
        newCustomer.setCustcountry(customer.getCustcity());
        newCustomer.setOpeningamt(customer.getOpeningamt());
        newCustomer.setOutstandingamt(customer.getOutstandingamt());
        newCustomer.setPaymentamt(customer.getPaymentamt());
        newCustomer.setPhone(customer.getPhone());
        newCustomer.setWorkingarea(customer.getWorkingarea());
        newCustomer.setGrade(customer.getGrade());
        newCustomer.setReceiveamt(customer.getReceiveamt());

        Agent custAgent = agentrepos.findById(customer.getAgent().getAgentcode())
                    .orElseThrow(() -> new EntityNotFoundException("Agent " + customer.getAgent() + " Not Found"));
        newCustomer.setAgent(custAgent);


        newCustomer.getOrders()
                .clear();
        for(Order o: customer.getOrders())
        {
            Order newOrder = new Order(
                    o.getOrdamount(),
                    o.getAdvanceamount(),
                    o.getCustomer(),
                    o.getOrderdescription());

            newOrder.setCustomer(newCustomer);
            newCustomer.getOrders()
                    .add(newOrder);

        }

        System.out.println(newCustomer);

        return customerRepos.save(newCustomer);
    }

    @Override
    @Transactional
    public Customer update(Customer customer, long id) {

        Customer currentCustomer = customerRepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer " + id + " Not Found"));

        Customer c = customer;

        if(c.getCustname() != null)
        {
            currentCustomer.setCustname(c.getCustname());
        }

        if(c.getCustcity() != null)
        {
            currentCustomer.setCustcity(c.getCustcity());
        }

        if(c.getCustcountry() != null)
        {
            currentCustomer.setCustcountry(c.getCustcountry());
        }

        if(c.getGrade() != null)
        {
            currentCustomer.setGrade(c.getGrade());
        }

        if(c.getOpeningamt() != 0)
        {
            currentCustomer.setOpeningamt(c.getOpeningamt());
        }

        if(c.getOutstandingamt() != 0)
        {
            currentCustomer.setOpeningamt(c.getOpeningamt());
        }

        if(c.getPaymentamt() != 0) {
            currentCustomer.setPaymentamt(c.getPaymentamt());
        }

        if(c.getPhone() != null)
        {
            currentCustomer.setPhone(c.getPhone());
        }

        if(c.getWorkingarea() != null)
        {
            currentCustomer.setWorkingarea(c.getWorkingarea());
        }

        if(c.getReceiveamt() != 0)
        {
            currentCustomer.setReceiveamt(c.getReceiveamt());
        }

        if(c.getAgent() != null)
        {
            Agent custAgent = agentrepos.findById(customer.getAgent().getAgentcode())
                    .orElseThrow(() -> new EntityNotFoundException("Agent " + customer.getAgent() + " Not Found"));
            currentCustomer.setAgent(custAgent);
        }

        if(c.getOrders().size() > 0)
        {
            currentCustomer.getOrders()
                    .clear();
            for(Order o: customer.getOrders())
            {
                Order newOrder = new Order(
                        o.getOrdamount(),
                        o.getAdvanceamount(),
                        o.getCustomer(),
                        o.getOrderdescription());

                newOrder.setCustomer(currentCustomer);
                currentCustomer.getOrders()
                        .add(newOrder);

            }
        }

        System.out.println(currentCustomer);

        return customerRepos.save(currentCustomer);
    }

    @Override
    public void delete(long id) {
        if(customerRepos.findById(id).isPresent())
        {
            customerRepos.deleteById(id);
        }
        else
        {
            throw new EntityNotFoundException("Customer " + id + " Not Found");
        }
    }
}
