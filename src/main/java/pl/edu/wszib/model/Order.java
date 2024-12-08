package pl.edu.wszib.model;

import java.time.ZonedDateTime;
import java.util.List;

public class Order {
    private int id;
    private ZonedDateTime createdDate = ZonedDateTime.now();
    private Customer customer;
    private List<Hardware> items;
    private String issueDescription;
    private OrderStatus orderStatus;
    private Payment payment;
}
