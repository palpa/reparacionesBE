package reparaciones.domain;

import org.springframework.hateoas.Identifiable;

public class Customer implements Identifiable<Long> {
	private final Long id;
	private final String firstName;
	private final String lastName;
	private final String address;

	private Customer() {
		throw new AssertionError();
	}

	private Customer(Builder builder) {
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

	public static class Builder {
		private final Long id;
		private final String firstName;
		private final String lastName;
		private String address;

		public Builder(Long id, String firstName, String lastName) {
			this.id = id;
			this.firstName = firstName;
			this.lastName = lastName;
		}

		public Builder address(String address) {
			this.address = address;
			return this;
		}

		public Customer build() {
			return new Customer(this);
		}
	}
}
