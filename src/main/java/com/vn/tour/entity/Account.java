package com.vn.tour.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "account")
public class Account {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_seq")
    @SequenceGenerator(name = "account_seq", sequenceName = "account_seq",allocationSize = 1)
	private Long id;
	
	@Column(name = "user_name")
	private String userName;
	
	@Column(name="pass_word")
	private String password;
	
	@Column(name="role")
	private String role;
	
	@Column(name = "status")
	private String status;

	public Account() {
		super();
	}
	public Account(Long accountIdLong, String userName, String password, String role, String status) {
		super();
		this.id = accountIdLong;
		this.userName = userName;
		this.password = password;
		this.role = role;
		this.status = status;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long accountIdLong) {
		this.id = accountIdLong;
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
	
}
