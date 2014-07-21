package reparaciones.domain;

import org.springframework.hateoas.Identifiable;

public class Customer implements Identifiable<Long> {

	private final Long id;
	private String firstName;
	private String lastName;
	private String address;

	private Customer() {
		throw new AssertionError();
	}

	private Customer(CustomerBuilder builder) {
		this.id = builder.id;
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.address = builder.address;
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getAddress() {
		return address;
	}

	public void updateAttributes(Customer customer) {
		
		if (customer.firstName != null)
			this.firstName = customer.firstName;
		
		if (customer.lastName != null)
			this.lastName = customer.lastName;
		
		if (customer.address != null)
			this.address = customer.address;
	}

	public static class CustomerBuilder {

		private static Long idIncrement = 1L;

		private Long id = 0L;
		private final String firstName;
		private final String lastName;
		private String address;

		private CustomerBuilder(String firstName, String lastName) {
			this.firstName = firstName;
			this.lastName = lastName;
		}

		public CustomerBuilder id(Long id) {
			this.id = id;
			return this;
		}

		public CustomerBuilder address(String address) {
			this.address = address;
			return this;
		}

		public Customer build() {
			if (this.id == 0L)
				this.id = CustomerBuilder.idIncrement++;

			return new Customer(this);
		}
	}

	public static CustomerBuilder newInstance(String firstName, String lastName) {
		return new CustomerBuilder(firstName, lastName);
	}

	public static Long getNextId() {
		return CustomerBuilder.idIncrement;
	}
}
