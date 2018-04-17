package com.spring.security.dto;

public class UserDTO {

	private Integer id;
	private String userName;
	private String password;
	private String email;
	private boolean active;

	public UserDTO() {
		super();
	}

	public UserDTO(Integer id, String userName, String password, String email, boolean active) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.active = active;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", userName=" + userName + ", password=" + password + ", email=" + email
				+ ", active=" + active + "]";
	}

}
