package pl.edu.wszib;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import pl.edu.wszib.model.Address;
import pl.edu.wszib.model.Customer;
import pl.edu.wszib.model.Hardware;
import pl.edu.wszib.model.Order;
import pl.edu.wszib.service.HelpDeskWorker;
import pl.edu.wszib.service.ServiceEngineer;

@Slf4j
@EnableJpaRepositories
@SpringBootApplication
public class ServiceApplication implements CommandLineRunner {

    @Autowired
    HelpDeskWorker helpDeskWorker;

    @Autowired
    ServiceEngineer serviceEngineer;

    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Customer customer = new Customer()
                .firstName("Hermenegilda")
                .lastName("Kociubińska")
                .address(new Address()
                        .city("Pułtusk")
                        .postalCode("12-345")
                        .street("Buraczkowa 13")
                        .countryCode("PL")
                );
        log.info("Przychodzi klient: {}", customer);

        Hardware hardware = new Hardware()
                .model("Dell Inspiron")
                .serialNumber("DF3ASD")
                .name("Laptop");

        String opis = "Uszkodzony twardy dysk";

        long orderId = helpDeskWorker.createOrder(customer, hardware, opis);

        helpDeskWorker.acceptPayment(orderId, 150.15, "PLN");

        log.info("Zlecenie jest realizowane");

        Order servicedOrder = serviceEngineer.readActiveOrderList()
                .getFirst();

        serviceEngineer.fixHardware(servicedOrder.id(), "Wymiana twardego dysku");
        Address newAddress =  new Address().city("Kraków").street("Kijowska 11").postalCode("30-011").countryCode("PL");
        log.info("Klient zleca wysyłkę urządzenia pod adres: {}", newAddress);
        helpDeskWorker.shipOrder(orderId, newAddress);
    }
}