package reparaciones.domain;

public class Worker {
	
	private boolean isOwner;
	private String password;
	private String username;
	
	public String getPassword() {
		return password;
	}
	public String getUsername() {
		return username;
	}
	public boolean isOwner() {
		return isOwner;
	}
	public void setOwner(boolean isOwner) {
		this.isOwner = isOwner;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setUsername(String username) {
		this.username = username;
	}	

}
