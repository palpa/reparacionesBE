package reparaciones.domain;

import org.springframework.hateoas.Identifiable;

public class Customer implements Identifiable<Long> {

	private final Long id;
	private String firstName;
	private String lastName;
	private String dni;
	private String address;
	private String email;
	private String contactNumber;

	private Customer() {
		throw new AssertionError();
	}

	private Customer(CustomerBuilder builder) {
		this.id = builder.id;
		this.dni = builder.dni;
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.address = builder.address;
		this.email = builder.email;
		this.contactNumber = builder.contactNumber;
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


	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEMail() {
		return email;
	}

	public void setEMail(String mail) {
		this.email = mail;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public void updateAttributes(Customer customer) {
		
		if (customer.firstName != null)
			this.firstName = customer.firstName;

		if (customer.lastName != null)
			this.lastName = customer.lastName;
		
		if (customer.dni != null)
			this.dni = customer.dni;

		if (customer.address != null)
			this.address = customer.address;
		
		if (customer.email != null)
			this.email = customer.email;
		
		if (customer.contactNumber != null)
			this.contactNumber = customer.contactNumber;
	}

	public static class CustomerBuilder {

		private static Long idIncrement = 1L;

		private Long id = 0L;
		private final String dni;
		private final String firstName;
		private final String lastName;
		private String address;
		private String email;
		private String contactNumber;

		private CustomerBuilder(String dni, String firstName, String lastName) {
			this.dni = dni;
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
		
		public CustomerBuilder email(String email) {
			this.email = email;
			return this;
		}
		
		public CustomerBuilder contactNumber(String contactNumber) {
			this.contactNumber = contactNumber;
			return this;
		}

		public Customer build() {
			if (this.id == 0L)
				this.id = CustomerBuilder.idIncrement++;

			return new Customer(this);
		}
	}

	public static CustomerBuilder getBuilder(String customerDni,
			String customerFirstName, String customerLastName) {

		return new CustomerBuilder(customerDni, customerFirstName, customerLastName);
	}

	public static Long getNextId() {
		return CustomerBuilder.idIncrement;
	}
}
