package pl.edu.wszib.repository;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import pl.edu.wszib.model.OrderStatus;

import java.util.Arrays;

@Converter(autoApply = true)
public class OrderStatusConverter implements AttributeConverter<OrderStatus,String> {

    @Override
    public String convertToDatabaseColumn(OrderStatus orderStatus) {
        return null != orderStatus ? orderStatus.name() : null ;
    }

    @Override
    public OrderStatus convertToEntityAttribute(String input) {
        return Arrays.stream(OrderStatus.values())
                .filter(s->s.name().equals(input))
                .findFirst()
                .orElse(null);
    }
}
