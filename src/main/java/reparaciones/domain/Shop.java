package reparaciones.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Shop {

	private List<Customer> customers = new ArrayList<Customer>();

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public void addCustomer(Customer customer) {
		this.getCustomers().add(customer);
	}

	public List<Customer> findCustomersByName(String name) {
		Iterator<Customer> anIterator = this.getCustomers().iterator();
		List<Customer> customers = new ArrayList<Customer>();
		Customer currentCustomer = null;

		while (anIterator.hasNext()) {
			currentCustomer = anIterator.next();
			if (currentCustomer.getFirstName().equalsIgnoreCase(name)) {
				customers.add(currentCustomer);
			}
		}

		return customers;
	}

	public Customer findCustomerById(Long id) {
		Iterator<Customer> anIterator = this.getCustomers().iterator();
		Customer customer = null;
		Customer currentCustomer = null;

		while (anIterator.hasNext() && customer == null) {
			currentCustomer = anIterator.next();
			if (currentCustomer.getId().equals(id)) {
				customer = currentCustomer;
			}
		}

		return customer;
	}

	public boolean removeCustomer(Long id) {

		Customer customer = findCustomerById(id);

		if (customer == null)
			return false;

		return this.getCustomers().remove(customer);
	}

	public boolean updateCustomer(Customer customer) {

		Customer actualCustomer = findCustomerById(customer.getId());
		
		if (actualCustomer == null)
			return false;
		
		actualCustomer.updateAttributes(customer);
		
		return true;
	}

}
