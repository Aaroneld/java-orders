package lambda.aaron.orders.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private long paymentid;

    @Column(nullable = false)
    private String type;

    @ManyToMany(mappedBy = "payments")
    @JsonIgnoreProperties
    private Set<Order> orders = new HashSet<>();

    public Payment() {
    }

    public Payment(String type)
    {
        this.type = type;
    }

    public void setPaymentid(long paymentid) {
        this.paymentid = paymentid;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }


}
