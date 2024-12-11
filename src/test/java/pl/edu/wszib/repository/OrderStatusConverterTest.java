package pl.edu.wszib.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.edu.wszib.model.OrderStatus;


class OrderStatusConverterTest {

    private OrderStatusConverter converter = new OrderStatusConverter();

    @Test
    void shouldMapEnumValueToString() {
        //given
        OrderStatus orderStatus = OrderStatus.REJECTED;
        //when
        String result = converter.convertToDatabaseColumn(orderStatus);
        //then
        Assertions.assertEquals(orderStatus.name(), result);
    }

    @Test
    void shouldMapNullEnumValue() {
        //given
        //when
        String result = converter.convertToDatabaseColumn(null);
        //then
        Assertions.assertNull(result);
    }

    @Test
    void shouldMapNullString() {
        //given
        //when
        OrderStatus result = converter.convertToEntityAttribute(null);
        //then
        Assertions.assertNull(result);
    }
    @Test
    void shouldMapInvalidStringToNull() {
        //given
        String invalid = "ssdffs";
        //when
        OrderStatus result = converter.convertToEntityAttribute(invalid);
        //then
        Assertions.assertNull(result);
    }

    @Test
    void shouldMapValidStringToEnumValue() {
        //given
        OrderStatus assumedStatus = OrderStatus.REJECTED;
        String valid = assumedStatus.name();
        //when
        OrderStatus result = converter.convertToEntityAttribute(valid);
        //then
        Assertions.assertEquals(assumedStatus, result);
    }

}