package pl.edu.wszib.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Hardware {
    private long id;
    private String model;
    private String serialNumber;
    private String name;
}
