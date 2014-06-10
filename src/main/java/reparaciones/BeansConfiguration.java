package reparaciones;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import reparaciones.domain.Shop;
import reparaciones.fixtures.CustomerFixture;

@Configuration
public class BeansConfiguration {
	protected final Logger log = LoggerFactory.getLogger(getClass());

	@Bean
	public Shop shop() {
		Shop shop = new Shop();

		log.info("Shop created!");

		shop.setCustomers(CustomerFixture.aCustomer().buildMany(100));
		
		return shop;
	}
}
