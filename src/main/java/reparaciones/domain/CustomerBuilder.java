package reparaciones.domain;

import java.util.ArrayList;
import java.util.List;

public class CustomerBuilder {
	private static long count = 0;

	String firstName = "Super";
	String lastName = "Gay";
	Long id;

	public static CustomerBuilder aCustomer() {
		return new CustomerBuilder();
	}

	public Customer  build() {
		synchronized (this) {
			count++;
		}
		Customer customer = new Customer();
		customer.setFirstName(firstName);
		customer.setLastName(lastName +' '+ count);
		customer.setId(count);
		return customer;
	}

	public Iterable<Customer> buildMany(int numberOf) {
		List<Customer> customers = new ArrayList<Customer>();
		for (int i = 0; i < numberOf; i++) {
			customers.add(build());
		}
		return customers;
	}
}
