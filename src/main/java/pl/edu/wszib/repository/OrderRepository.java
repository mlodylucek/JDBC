package pl.edu.wszib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.edu.wszib.model.Order;
import pl.edu.wszib.model.OrderStatus;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findTopByOrderStatusOrderByCreatedDateAsc(OrderStatus orderStatus);

    @Query("SELECT o FROM Order o JOIN o.customer c WHERE c.lastName LIKE '%:name%' or c.firstName LIKE '%:name%'")
    List<Order> findAllByCustomerName(@Param("name") String name);
}
