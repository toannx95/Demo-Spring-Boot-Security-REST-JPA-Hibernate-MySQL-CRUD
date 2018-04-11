package com.s3corp.dto;

public class AccountDTO {

	private String userName;
	private String password;
	private String role;
	private String email;
	private String name;
	private boolean active;

	public AccountDTO() {
		super();
	}

	public AccountDTO(String userName, String password, String role, String email, String name, boolean active) {
		super();
		this.userName = userName;
		this.password = password;
		this.role = role;
		this.email = email;
		this.name = name;
		this.active = active;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "AccountDTO [userName=" + userName + ", password=" + password + ", role=" + role + ", email=" + email
				+ ", name=" + name + ", active=" + active + "]";
	}

}
