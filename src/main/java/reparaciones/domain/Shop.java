package reparaciones.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;;

//@Component
//@Scope(value = "singleton")
public class Shop {

//	@Autowired
//	CustomerAccess customerAccess;
//	
//	public Shop() {
//		super();
//		
//		customers = new ArrayList<Customer>();
//		
//		for (Customer customer : customerAccess.getCustomers() )
//		{
//			this.addCustomer(customer);
//		}	
//	}

	private Collection<Customer> customers;

	public Collection<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(Collection<Customer> customers) {
		this.customers = customers;
	}

	public void addCustomer(Customer customer) {
		this.getCustomers().add(customer);
	}
	
	public Collection<Customer> findCustomersByName (String name){
		Iterator<Customer> anIterator = this.getCustomers().iterator();
		Collection<Customer> customers = new ArrayList<Customer>();
		Customer currentCustomer = null;
			
		while (anIterator.hasNext())
		{
			currentCustomer = anIterator.next();
			if (currentCustomer.getFirstName().equalsIgnoreCase(name)){
				customers.add(currentCustomer);
			}
		}
		
		return customers;
	}

}
