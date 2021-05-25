package com.iktakademija.Dan0602_Data_Acess.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktakademija.Dan0602_Data_Acess.entities.AdressEntity;
import com.iktakademija.Dan0602_Data_Acess.repositories.AdressRepository;

@RestController
@RequestMapping(path = "/api/v1/address")
public class AdressController {
	
	@Autowired
	private AdressRepository adressRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<AdressEntity> getAll() {
		return (List<AdressEntity>) adressRepository.findAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	public AdressEntity saveNewAdress(@RequestParam String street, @RequestParam String city, @RequestParam String country) {
		
		AdressEntity adressEntity = new AdressEntity();
		adressEntity.setStreet(street);
		adressEntity.setCity(city);
		adressEntity.setCountry(country);
		return adressRepository.save(adressEntity);
	}

}
