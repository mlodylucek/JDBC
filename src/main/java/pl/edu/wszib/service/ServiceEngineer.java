package pl.edu.wszib.service;

import pl.edu.wszib.model.Hardware;
import pl.edu.wszib.model.Order;
import pl.edu.wszib.repository.OrderRepository;

import java.util.List;

public interface ServiceEngineer {
    public void fixHardware(long orderId, String comment );
    public void rejectOrder(long orderId);
    public void exchangeHardware(long orderId, Hardware oldHardware, Hardware newHardware, String comments);
    public List<Order> readActiveOrderList();
}
