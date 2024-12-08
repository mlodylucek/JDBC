package pl.edu.wszib.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Payment {
    private long id;
    private String currency;
    private double amount;
}
