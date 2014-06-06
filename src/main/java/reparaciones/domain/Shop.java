package reparaciones.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Shop {

	private Collection<Customer> customers = new ArrayList<Customer>();

	public Collection<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(Collection<Customer> customers) {
		this.customers = customers;
	}

	public void addCustomer(Customer customer) {
		this.getCustomers().add(customer);
	}

	public Collection<Customer> findCustomersByName(String name) {
		Iterator<Customer> anIterator = this.getCustomers().iterator();
		Collection<Customer> customers = new ArrayList<Customer>();
		Customer currentCustomer = null;

		while (anIterator.hasNext()) {
			currentCustomer = anIterator.next();
			if (currentCustomer.getFirstName().equalsIgnoreCase(name)) {
				customers.add(currentCustomer);
			}
		}

		return customers;
	}

}
