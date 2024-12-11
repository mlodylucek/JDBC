package pl.edu.wszib.service;

import pl.edu.wszib.model.Address;
import pl.edu.wszib.model.Customer;
import pl.edu.wszib.model.Hardware;
import pl.edu.wszib.model.Order;

import java.util.Collection;

public interface HelpDeskWorker {
    public long createOrder(Customer customer, Hardware hardware, String issueDescription);
    public Order readOrder(long orderId);
    public Collection<Order> findByCustomerName(String name);
    public void returnHardware(long orderId);
    public void shipOrder(long orderId, Address alternateAddress);
    public void acceptPayment(long orderId, double amount, String currencyCode);
    public void returnPayment(long orderId);
}
