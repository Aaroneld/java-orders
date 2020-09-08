package lambda.aaron.orders.services;

import lambda.aaron.orders.models.Customer;
import lambda.aaron.orders.models.Order;
import lambda.aaron.orders.models.Payment;
import lambda.aaron.orders.repositories.CustomerRepository;
import lambda.aaron.orders.repositories.OrderRepository;
import lambda.aaron.orders.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private CustomerRepository custrepos;

    @Autowired
    private PaymentRepository payrepos;

    @Override
    public List<Order> getAllOrders() {
        List<Order> allOrders = new ArrayList<>();

        orderRepo.findAll()
                .iterator()
                .forEachRemaining(allOrders::add);

        return allOrders;
    }

    @Override
    @Transactional
    public Order save(Order order) {

        Order newOrder = new Order();

        if(order.getOrdnum() != 0)
        {
            orderRepo.findById(order.getOrdnum())
                    .orElseThrow(() -> new EntityNotFoundException("Order " + order.getOrdnum() + " Not Found"));

            newOrder.setOrdnum(order.getOrdnum());
        }

        newOrder.setAdvanceamount(order.getAdvanceamount());
        newOrder.setOrdamount(order.getOrdamount());
        newOrder.setOrderdescription(order.getOrderdescription());

        Customer customer = custrepos.findById(order.getCustomer().getCustcode())
                .orElseThrow(() -> new EntityNotFoundException("Customer " +
                        order.getCustomer().getCustcode() + " Not Found"));

        newOrder.setCustomer(customer);

        if(order.getPayments().size() > 0)
        {
            newOrder.getPayments()
                    .clear();
            for(Payment p: order.getPayments())
            {
                Payment newPayment = payrepos.findById(p.getPaymentid())
                        .orElseThrow(() -> new EntityNotFoundException("Payment " + p.getPaymentid() + " Not Found"));

                newOrder.getPayments()
                        .add(newPayment);
            }
        }

        return orderRepo.save(newOrder);
    }

    @Override
    @Transactional
    public void delete(long id) {

        if(orderRepo.findById(id).isPresent())
        {
            orderRepo.deleteById(id);
        }
        else
        {
            throw new EntityNotFoundException(" Order" + id + " Not Found");
        }
    }
}
