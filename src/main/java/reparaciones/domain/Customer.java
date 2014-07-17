package reparaciones.domain;

import org.springframework.hateoas.Identifiable;

public class Customer implements Identifiable<Long> {

	private final Long id;
	private String firstName;
	private String lastName;
	private final String address;

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

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getAddress() {
		return address;
	}

	public static class CustomerBuilder {

		private static Long idIncrement = 1L;

		private final Long id = CustomerBuilder.idIncrement++;
		private final String firstName;
		private final String lastName;
		private String address;

		private CustomerBuilder(String firstName, String lastName) {
			this.firstName = firstName;
			this.lastName = lastName;
		}

		public CustomerBuilder address(String address) {
			this.address = address;
			return this;
		}

		public Customer build() {
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
