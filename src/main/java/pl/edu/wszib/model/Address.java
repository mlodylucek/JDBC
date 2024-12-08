package pl.edu.wszib.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Address {
    private long id;
    private String street;
    private String city;
    private String postalCode;
    private String countryCode;
}
