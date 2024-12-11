package pl.edu.wszib;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.edu.wszib.repository.CustomerRepository;
import pl.edu.wszib.repository.OrderRepository;
import pl.edu.wszib.service.DefaultHelpdeskWorker;
import pl.edu.wszib.service.DefaultServiceEngineer;
import pl.edu.wszib.service.HelpDeskWorker;
import pl.edu.wszib.service.ServiceEngineer;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public ServiceEngineer serviceEngineer(OrderRepository orderRepository){
        return new DefaultServiceEngineer(orderRepository);
    }

    @Bean
    public HelpDeskWorker helpDeskWorker(OrderRepository orderRepository, CustomerRepository customerRepository){
        return new DefaultHelpdeskWorker(orderRepository, customerRepository);
    }
}
