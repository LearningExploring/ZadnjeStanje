package com.iktakademija.Dan0401_ProjekatSpeed.entitires;

import java.util.Random;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iktakademija.Dan0401_ProjekatSpeed.entitires.enums.EUserRole;

public class UserEntity {
	protected Integer id;
	protected String firstName;
	protected String lastName;
	protected String username;
	protected String password;
	protected String email;
	protected EUserRole userRole;

	public UserEntity(Integer id, String firstName, String lastName, String email, String username, String password, EUserRole userRole) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.userRole = userRole;
	}

	public UserEntity() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public EUserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(EUserRole userRole) {
		this.userRole = userRole;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@JsonIgnore
	public Integer getNewId() {
		return new Random().nextInt();
	}

	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", username=" + username
				+ ", password=" + password + ", email=" + email + ", userRole=" + userRole + "]";
	}

}
