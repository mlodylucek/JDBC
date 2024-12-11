package pl.edu.wszib.service;

import lombok.extern.slf4j.Slf4j;
import pl.edu.wszib.model.Hardware;
import pl.edu.wszib.model.Order;
import pl.edu.wszib.repository.OrderRepository;

import java.util.List;

import static pl.edu.wszib.model.OrderStatus.FIXED;
import static pl.edu.wszib.model.OrderStatus.NEW;
import static pl.edu.wszib.model.OrderStatus.REJECTED;

@Slf4j
public class DefaultServiceEngineer implements ServiceEngineer{
    private final OrderRepository orderRepository;

    public DefaultServiceEngineer(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void fixHardware(long orderId, String comment) {
        orderRepository.findById(orderId)
                .map(order -> order.orderStatus(FIXED))
                .map(order -> order.repairDescription(comment))
                .ifPresent(orderRepository::save);
        log.info("Zlecenie numer: {} zostało zrealizowane.", orderId);
    }

    @Override
    public void rejectOrder(long orderId) {
        orderRepository.findById(orderId)
                .map(order -> order.orderStatus(REJECTED))
                .ifPresent(orderRepository::save);
        log.info("Zlecenie numer: {} zostało odrzucone.", orderId);
    }

    @Override
    public void exchangeHardware(long orderId, Hardware oldHardware, Hardware newHardware, String comments) {
        orderRepository.findById(orderId)
                .map(order -> order.repairDescription(comments))
                .map(order -> order.items(replaceHardware(order.items(), oldHardware, newHardware)))
                .map(order -> order.orderStatus(FIXED))
                .ifPresent(orderRepository::save);
        log.info("Zlecenie numer: {} zostało zrealizowane. Sprzęt został wymieniony na: {} ({})", orderId, newHardware.name(), newHardware.id());
    }

    @Override
    public List<Order> readActiveOrderList() {
        return orderRepository.findTopByOrderStatusOrderByCreatedDateAsc(NEW);
    }

    private List<Hardware> replaceHardware(List<Hardware> items, Hardware oldHardware, Hardware newHardware) {
        items.remove(oldHardware);
        items.add(newHardware);
        return items;
    }
}
