package reparaciones;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.config.EnableHypermediaSupport;

import reparaciones.domain.Customer;
import reparaciones.domain.CustomerAccess;
import reparaciones.domain.Shop;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableHypermediaSupport(type = {EnableHypermediaSupport.HypermediaType.HAL})
public class Application {
	
	protected final Logger log = LoggerFactory.getLogger(getClass());

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public Shop shop() {
		Shop shop = new Shop();
	
		log.info("Shop created!");
		
		Iterable<? extends Customer> customers = customerAccess().getCustomers();
		for (Customer customer : customers) {
			shop.addCustomer(customer);
		}

		return shop;
	}
	
	@Bean
    public CustomerAccess customerAccess() {
        return new CustomerAccess();
    }
}
