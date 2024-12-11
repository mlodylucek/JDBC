package pl.edu.wszib.service;

import lombok.extern.slf4j.Slf4j;
import pl.edu.wszib.exception.ServiceException;
import pl.edu.wszib.model.Address;
import pl.edu.wszib.model.Customer;
import pl.edu.wszib.model.Hardware;
import pl.edu.wszib.model.Order;
import pl.edu.wszib.model.OrderStatus;
import pl.edu.wszib.model.Payment;
import pl.edu.wszib.repository.CustomerRepository;
import pl.edu.wszib.repository.OrderRepository;

import java.time.ZonedDateTime;
import java.util.List;

import static pl.edu.wszib.model.OrderStatus.NEW;
import static pl.edu.wszib.model.OrderStatus.RETURNED;

@Slf4j
public class DefaultHelpdeskWorker implements HelpDeskWorker{
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    public DefaultHelpdeskWorker(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public long createOrder(Customer customer, Hardware hardware, String issueDescription) {
        Order order = new Order()
                .orderStatus(OrderStatus.NEW)
                .createdDate(ZonedDateTime.now())
                .issueDescription(issueDescription)
                .customer(customer)
                .items(List.of(hardware));
        Order savedOrder = orderRepository.saveAndFlush(order);
        log.info("Utworzono nowe zlecenie: {}", savedOrder);
        return savedOrder.id();

    }

    @Override
    public Order readOrder(long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(()->ServiceException.throwWithMessage("Nie znaleziono zamówienia!"));
    }

    @Override
    public List<Order> findByCustomerName(String name) {
        return orderRepository.findAllByCustomerName(name);
    }

    @Override
    public void returnHardware(long orderId) {
        Order order =  readOrder(orderId);
        validateOrderReadyToReturn(order);
        order.orderStatus(RETURNED);
        orderRepository.saveAndFlush(order);
    }

    private void validateOrderReadyToReturn(Order order) {
        if (!order.isPaid()){
            throw ServiceException.throwWithMessage("Naprawa nie została opłacona!");
        }

        if(order.isRefunded()) {
            throw ServiceException.throwWithMessage("Zwrócono już pieniądze za urządzenie!");
        }

        if (RETURNED.equals( order.orderStatus())) {
            throw ServiceException.throwWithMessage("Urządzenie już zostało zwrócone!");
        }

        if (NEW.equals( order.orderStatus())) {
            throw ServiceException.throwWithMessage("Urządzenie nie jest gotowe do odbioru!");
        }
    }

    @Override
    public void shipOrder(long orderId, Address alternateAddress) {
        Order order =  readOrder(orderId);
        validateOrderReadyToReturn(order);
        order.orderStatus(RETURNED);
        if (null != alternateAddress) {
            Customer customer = order.customer();
            customer.address(alternateAddress);
            customerRepository.save(customer);
        }
        Order savedOrder = orderRepository.saveAndFlush(order);
        Address sentToAddress = savedOrder.customer().address();
        log.info("Zlecenie {} zostało wysłane na adres: {}", orderId, sentToAddress);
    }

    @Override
    public void acceptPayment(long orderId, double amount, String currencyCode) {
        Order order =  readOrder(orderId);

        if (order.isPaid() && null != order.payment()) {
            throw ServiceException.throwWithMessage("Naprawa została już opłacona!");
        }

        order.payment(new Payment()
                        .amount(amount)
                        .currency(currencyCode))
                .isPaid(true);
        orderRepository.save(order);
        log.info("Zlecenie {} zostało opłacone: {}", orderId, order.payment());
    }

    @Override
    public void returnPayment(long orderId) {
        Order order =  readOrder(orderId);
        if (!order.isPaid()) {
            throw ServiceException.throwWithMessage("Naprawa nie została jeszcze opłacona!");
        }
        Payment payment = order.payment();
        order.isRefunded(true);
        orderRepository.save(order);
        log.info("Opłata {}{} za zlecenie {} została zwrócona", payment.currency(), payment.amount(), orderId);
    }
}
