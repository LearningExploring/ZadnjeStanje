package com.iktakademija.Dan0304_Projekat.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktakademija.Dan0304_Projekat.entities.UserEntity;
import com.iktakademija.Dan0304_Projekat.entities.UserRole;

@RestController
@RequestMapping(value= "/project/users")
public class UserController {
	
	// Mock Database
	private List<UserEntity> DB;
	
	// Mock database getter
	private List<UserEntity> getDB() {
		if (DB == null) {
			List<UserEntity> users = new ArrayList<>();
			UserEntity u1 = new UserEntity(1, "Vladimir", "Dimitrieski", "dimitrieski@uns.ac.rs", "vladimir",
					"vladimir", UserRole.ROLE_CUSTOMER);
			UserEntity u2 = new UserEntity(2, "Milanka", "Kalkan", "milankamkalkan@uns.ac.rs", "milanka", "milanka",
					UserRole.ROLE_CUSTOMER);
			UserEntity u3 = new UserEntity(3, "Angelina", "Vujanovic", "avujanovic@uns.ac.rs", "angelina", "angelina",
					UserRole.ROLE_CUSTOMER);
			users.add(u1);
			users.add(u2);
			users.add(u3);
			DB = users;
		}
		return DB;
	}
	
	// 1.3 kreirati REST endpoint koji vraća listu korisnika aplikacije putanja /project/users
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<UserEntity> getAllUsers() {
		
		// URL: GET http://localhost:8090/project/users
		
		// Return all users from base
		return getDB();
	}
	
	// 1.4 kreirati REST endpoint koji vraća korisnika po vrednosti prosleđenog ID-a
	// putanja /project/users/{id}
	// u slučaju da ne postoji korisniksa traženom vrednošću ID-a vratiti null
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public UserEntity getUserById(@PathVariable("id") Integer id) {

		// URL GET http://localhost:8090/project/users/2

		// Safe check
		if (id == null)
			return null;

		// Get all users in base
		List<UserEntity> base = getDB();

		// Find user in base or return null
		for (UserEntity ue : base) {
			if (ue.getId() == id.intValue()) {
				return ue;
			}
		}
		return null;
	}

	//	1.5 kreirati REST endpoint koji omogućava dodavanje novog korisnika
	//	putanja /project/users
	//	u okviru ove metode postavi vrednost atributa user role na ROLE_CUSTOMER metoda treba da vrati dodatog korisnika
	@RequestMapping(value = "", method = RequestMethod.POST)
	public UserEntity addNewUser(@RequestBody UserEntity user) {
		
		// URL: POST http://localhost:8090/project/users
		
		// Check is new user is valid
		if (user == null) return null;

		// Set new id and add to database
		user.setId(user.getNewId());
		user.setUserRole(UserRole.ROLE_CUSTOMER);
		getDB().add(user);		
		
		// Return new user
		return user;		
	}

	//1.6 kreirati REST endpoint koji omogućava izmenu postojećeg korisnika
	//putanja /project/users/{id}
	//ukoliko je prosleđen ID koji ne pripada nijednom korisniku metoda treba da vrati null, a u suprotnom vraća podatke korisnika sa izmenjenim vrednostima
	//NAPOMENA: u okviru ove metodene menjati vrednost atributa user role i password
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public UserEntity updateUser(@PathVariable("id") Integer id, @RequestBody UserEntity user) {

		// URL PUT http://localhost:8090/project/users/2

		// Get Find user and change user role and pasword
		UserEntity existingUser = getUserById(id);
		if (existingUser != null) {
			if (user.getUserRole() != null)
				existingUser.setUserRole(user.getUserRole());
			if (user.getPassword() != null)
				existingUser.setPassword(user.getPassword());
		}
		return existingUser;	
	}
	
	//1.7 kreirati REST endpoint koji omogućava izmenu atributa user_role postojećeg korisnika
	//putanja /project/users/change/{id}/role/{role}
	//ukoliko je prosleđen ID koji ne pripada nijednom korisniku metoda treba da vrati null, a u suprotnom vraća podatke korisnika sa izmenjenom vrednošću atributa user role
	@RequestMapping(value = "/change/{id}/role/{role}", method = RequestMethod.PUT)
	public UserEntity changeRole(@PathVariable("id") Integer id, @PathVariable("role") UserRole role) {
		
		// URL PUT http://localhost:8090/project/users/change/2/role/ROLE_SELLER

		// Get Find user and change user role
		UserEntity user = getUserById(id);
		if (user != null && role != null && user.getUserRole() != null) {
			user.setUserRole(user.getUserRole());
		}
		return user;
	}
	
	
	//1.8 kreirati REST endpoint koji omogućava izmenu vrednosti atributa password postojećeg korisnika
	//putanja /project/users/changePassword/{id}
	//kao RequestParam proslediti vrednosti stare i nove lozinke
	//ukoliko je prosleđen ID koji ne pripada nijednom korisniku metoda treba da vrati null, a u suprotnom vraća podatke korisnikasa izmenjenom vrednošću atributa password
	//NAPOMENA: da bi vrednost atributa password mogla da bude zamenjenasa novom vrednošću, neophodno je da se vrednost stare lozinke korisnika poklapa sa vrednošću stare lozinke prosleđene kao RequestParam
	@RequestMapping(value = "/changePassword/{id}", method = RequestMethod.PUT)
	public UserEntity changePassword(@PathVariable("id") Integer id, @RequestParam("old") String oldPass, @RequestParam("new") String newPass) {
		
		// URL PUT http://localhost:8090/project/users/changePassword/2?old=milanka&new=1111

		// Get user by id and return null if not found
		UserEntity user = getUserById(id);
		if (user == null)
			return null;

		// Check is passwords parametere valid
		if (oldPass == null || newPass == null)
			return user;

		// Check is old pasword match
		if (user.getPassword().equals(oldPass) == false)
			return user;

		user.setPassword(newPass);
		return user;
	}

	//1.9 kreirati REST endpoint koji omogućava brisanje postojećeg korisnika
	//putanja /project/users/{id}
	//ukoliko je prosleđen ID koji ne pripada nijednom korisniku metoda treba da vrati null, a u suprotnom vraća podatke o korisniku koji je obrisan
	@RequestMapping(value = "/{id}" , method = RequestMethod.DELETE)
	public UserEntity removeUser(@PathVariable("id") Integer id) {		
		
		// URL DELETE http://localhost:8090/project/users/2
		
		// Get user from base. Return null if not exist		
		UserEntity user = getUserById(id);
		if (user == null) return null;
		
		// Remove user from base
		getDB().remove(user);		
		return user;
	}
	
	//1.10 kreirati REST endpoint koji vraća korisnika po vrednosti prosleđenog username-a
	//putanja /project/users/by-username/{username}
	//u slučaju da ne postoji korisniksa traženimusername-om vratiti null
	@RequestMapping(value = "/by-username/{username}", method = RequestMethod.GET)
	public UserEntity getByUsername(@PathVariable("username") String name) {

		// URL GET http://localhost:8090/project/users/by-username/angelina
		
		// Exit if no username string
		if (name == null) return null;
		
		// Get user from base. Return null if not exist		
		for (UserEntity userEntity : getDB()) {
			if (userEntity.getUsername().equals(name))
				return userEntity;
		}
		return null;
	}
}
