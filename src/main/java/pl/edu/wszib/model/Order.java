package pl.edu.wszib.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;

@Data
@Accessors(fluent = true)
@Entity
@Table(name = "orders")
public class Order implements Serializable {
    @Id
    @Column(name = "orderId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private ZonedDateTime createdDate = ZonedDateTime.now();
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "customerId")
    private Customer customer;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "hardware_to_order",
            joinColumns = @JoinColumn(name = "orderId"),
            inverseJoinColumns = @JoinColumn(name = "hardwareId"))
    private List<Hardware> items;
    private String issueDescription;
    private String repairDescription;
    private OrderStatus orderStatus;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "paymentId")
    private Payment payment;
    private boolean isPaid;
    private boolean isRefunded;
}
