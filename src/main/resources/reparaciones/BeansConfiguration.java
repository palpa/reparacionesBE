package reparaciones;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import reparaciones.domain.Customer;
import reparaciones.domain.CustomerAccess;
import reparaciones.domain.Shop;

@Configuration
public class BeansConfiguration {
	protected final Logger log = LoggerFactory.getLogger(getClass());

	@Bean
	public Shop shop() {
		Shop shop = new Shop();

		log.info("Shop created!");

		Iterable<? extends Customer> customers = customerAccess()
				.getCustomers();
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
