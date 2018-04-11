package com.s3corp.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNT")
public class Account implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "USERNAME")
	private String userName;

	@Column(name = "PASSWORD", nullable = false)
	private String password;

	@Column(name = "ROLE", nullable = false)
	private String role;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "NAME")
	private String name;

	@Column(name = "ACTIVE", nullable = false)
	private boolean active;

	public Account() {
		super();
	}

	public Account(String userName, String password, String role, String email, String name, boolean active) {
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
		return "Account [userName=" + userName + ", password=" + password + ", role=" + role + ", email=" + email
				+ ", name=" + name + ", active=" + active + "]";
	}

}
