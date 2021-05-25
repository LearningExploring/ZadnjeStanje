package com.iktakademija.Dan0301_RestVezbe.controllers;

import java.time.LocalDate;

public class BankClientEntities {

	protected Integer id;
	protected String name;
	protected String surname;
	protected String email;
	protected Character bonitet;
	protected LocalDate datumRodenja;

	public BankClientEntities() {
		super();
	}

	public BankClientEntities(Integer id, String name, String surname, String email, 
			LocalDate datumRodenja) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.bonitet = '-';
		this.datumRodenja = datumRodenja;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Character isBonitet() {
		return bonitet;
	}

	public void setBonitet(Character bonitet) {
		this.bonitet = bonitet;
	}

	public LocalDate getDatumRodenja() {
		return datumRodenja;
	}

	public void setDatumRodenja(LocalDate datumRodenja) {
		this.datumRodenja = datumRodenja;
//		if (getAges() > 65) {
//			setBonitet(false);
//		} else {
//			setBonitet(true);
//		}
	}
	
	public int getAges()
	{
		return (int) java.time.temporal.ChronoUnit.YEARS.between(getDatumRodenja(), LocalDate.now());
	}
	
	public boolean anythingNull()
	{		
		if (this.id == null) return true;
		if (this.name == null) return true;
		if (this.surname == null) return true;
		if (this.email == null) return true;
		if (this.bonitet == null) return true;
		if (this.datumRodenja == null) return true;
		return false;
	}

}
