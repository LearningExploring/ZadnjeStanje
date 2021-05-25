package com.iktakademija.Dan0602_Data_Acess.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	private String email;
	
	@Column(name = "datRod", nullable = false)
	private LocalDate datumRodenja;
	@Column(name = "brTel")
	private String brTelefona; 
	@Column(name = "jmbg", nullable = false)
	private String JMBG; 
	@Column(name = "brLk", nullable = false)
	private String brLicneKarte;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "address")	
	private AdressEntity address;

	public UserEntity() {
		super();
	}

	public UserEntity(String name, String email) {
		super();
		this.name = name;
		this.email = email;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public AdressEntity getAddress() {
		return address;
	}

	public void setAddress(AdressEntity address) {
		this.address = address;
	}

	public LocalDate getDatumRodenja() {
		return datumRodenja;
	}

	public void setDatumRodenja(LocalDate datumRodenja) {
		this.datumRodenja = datumRodenja;
	}

	public String getBrTelefona() {
		return brTelefona;
	}

	public void setBrTelefona(String brTelefona) {
		this.brTelefona = brTelefona;
	}

	public String getJMBG() {
		return JMBG;
	}

	public void setJMBG(String jMBG) {
		JMBG = jMBG;
	}

	public String getBrLicneKarte() {
		return brLicneKarte;
	}

	public void setBrLicneKarte(String brLicneKarte) {
		this.brLicneKarte = brLicneKarte;
	}	

}
