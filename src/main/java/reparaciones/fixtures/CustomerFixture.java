package reparaciones.fixtures;

import java.util.ArrayList;
import java.util.List;

import reparaciones.domain.Customer;

public class CustomerFixture {
	private static long count = 0;
	private static final String FIRST_NAME = "John";
	private static final String LAST_NAME = "Snow";

	public static CustomerFixture aCustomer() {
		return new CustomerFixture();
	}

	public Customer  build() {
		synchronized (this) {
			count++;
		}
		return Customer.newInstance(count, FIRST_NAME, LAST_NAME + ' ' + count).build();
	}

	public List<Customer> buildMany(int numberOf) {
		List<Customer> customers = new ArrayList<Customer>();
		for (int i = 0; i < numberOf; i++) {
			customers.add(build());
		}
		return customers;
	}
}
