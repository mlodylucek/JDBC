package pl.edu.wszib.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(fluent = true)
@Entity
@Table(name = "address")
public class Address implements Serializable {
    @Id
    @Column(name = "addressId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 128)
    private String street;
    @Column(length = 64)
    private String city;
    @Column(length = 6)
    private String postalCode;
    @Column(length = 2)
    private String countryCode;
}
