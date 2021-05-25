package com.iktakademija.Dan0501_Data_Acess.controllers;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktakademija.Dan0501_Data_Acess.entities.AdressEntity;
import com.iktakademija.Dan0501_Data_Acess.entities.UserEntity;
import com.iktakademija.Dan0501_Data_Acess.repositories.AdressRepository;
import com.iktakademija.Dan0501_Data_Acess.repositories.UserRepository;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AdressRepository addressRepository;
	
	@RequestMapping(method = RequestMethod.POST)
	public UserEntity saveNewUser(@RequestParam String name, @RequestParam String email)
	{
		UserEntity newUser = new UserEntity();
		newUser.setEmail(email);
		newUser.setName(name);		
		
		userRepository.save(newUser); 
		return newUser;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<UserEntity> getAll()
	{
		return (List<UserEntity>)userRepository.findAll();
	}
	
	@RequestMapping(path = "/{id}/address", method = RequestMethod.PUT)
	public UserEntity addAdressToUser (@PathVariable Integer id, @RequestParam Integer address)
	{
		UserEntity userEntity = userRepository.findById(id).get();
		AdressEntity adressEntity = addressRepository.findById(address).get();		
		userEntity.setAddress(adressEntity);		
		return userRepository.save(userEntity);
	}
	
//	•	Uraditi sledeće stavke:
//	•	1.1 popuniti bazu podataka sa podacima o deset osoba
//	@RequestMapping(method = RequestMethod.POST, path = "/add10")
//	public UserEntity add10Users()
//	{
//		UserEntity newUser = new UserEntity();
//		newUser.setEmail("Addresa01@Nekisajt.com");
//		newUser.setName("Petar");		
//		userRepository.save(newUser); 
//		
//		newUser.setEmail("Addresa01@Nekisajt.com");
//		newUser.setName("Petar");		
//		userRepository.save(newUser); 		
//		return newUser;
//	}	
	
//	•	1.2 u potpunosti omogućiti rad sa korisnicima
//	•	vraćanje korisnika po ID
//	•	ažuriranje korisnika
//	•	brisanje korisnika
	@RequestMapping(method = RequestMethod.GET, path = "/{id}")
	public UserEntity getUserById(@PathVariable Integer id) {
		return userRepository.findById(id).get();
	}
	
	// dodati delete i put
	
	
	// 1.3 omogućiti pronalaženje korisnika po email adresi putanja /by-email
	@RequestMapping(method = RequestMethod.GET, path = "/by-email")
	public UserEntity getUserByEmailAddress(@RequestParam String email) {
		
		return userRepository.findByEmail(email);
	}
	
	//	1.4 omogućiti pronalaženje korisnika po imenu
	//	vraćanje korisnika sortiranih po rastućoj vrednosti emaila putanja /by-name
	@RequestMapping(method = RequestMethod.GET, path = "/by-name")
	public List<UserEntity> getAllUserByNameSorted(@RequestParam String name) {
		
		return userRepository.findAllByNameOrderByEmailAsc(name);
	}	
	
	
}
