package reparaciones.domain;

import java.util.HashMap;
import java.util.Map;

public class CustomerAccess {
	
	private Iterable<Customer> customers = CustomerBuilder.aCustomer().buildMany(100);

	private Map<Long, Customer> customersIndex = indexPeople(customers);

	public Iterable<? extends Customer> getCustomers() {
		return customers;
	}

	public Customer getCustomer(Long id) {
		return customersIndex.get(id);

	}

	private Map<Long, Customer> indexPeople(Iterable<Customer> customers) {
		Map<Long, Customer> customerIndex = new HashMap<Long, Customer>();
		for (Customer customer : customers) {
			customerIndex.put(customer.getId(), customer);
		}
		return customerIndex;
	}
}
