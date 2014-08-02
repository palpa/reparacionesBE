package reparaciones.resources;

import org.springframework.hateoas.ResourceSupport;

public class CustomerResource extends ResourceSupport {
	
	private String firstName;
	private String lastName;
	private String dni;
	private String address;
	private String email;
	private String contactNumber;

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

	@Override
	public String toString() {
		return "CustomerResource [" + firstName + " " + lastName +
				", DNI: " + dni + "  (" + super.toString() + ")]";
	}

}
