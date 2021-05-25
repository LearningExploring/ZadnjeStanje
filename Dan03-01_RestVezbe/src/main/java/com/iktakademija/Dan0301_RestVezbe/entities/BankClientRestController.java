package com.iktakademija.Dan0301_RestVezbe.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktakademija.Dan0301_RestVezbe.controllers.BankClientEntities;

@RestController
@RequestMapping("/bankclients")
public class BankClientRestController {

	// Mock data base
	private List<BankClientEntities> DB;

	// Generate or return mock database
	private List<BankClientEntities> getDB() {
		if (DB == null) {
			List<BankClientEntities> clientsFromDB = new ArrayList<BankClientEntities>();
			clientsFromDB.add(new BankClientEntities(1, "Vladimir", "Dimitieski", "a@aaaaaa.aaa", LocalDate.parse("1919-03-29")));
			clientsFromDB.add(new BankClientEntities(2, "Nebojsa", "Horvat", "a@bbbbbb.aaa", LocalDate.parse("1999-03-09")));
			clientsFromDB.add(new BankClientEntities(3, "Ive", "Ivic", "ive@ive.aaa", LocalDate.parse("1989-08-19")));
			clientsFromDB.add(new BankClientEntities(4, "Delme", "Delkovic", null, LocalDate.parse("1989-08-19")));
			DB = clientsFromDB;
		}
		// return all clients to web FE
		return DB;
	}

	// GET all - list all clients
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<BankClientEntities> getAll() {
		// get all clients from database
		// return all clients to web FE
		List<BankClientEntities> clientsFromDB = getDB();
		return clientsFromDB;
	}

	// GET by ID - get single client
	@RequestMapping(value = "/{clientId2}", method = RequestMethod.GET)
	public BankClientEntities getByID(@PathVariable("clientId2") Integer clientId) {

		List<BankClientEntities> clientsFromDB = getDB();

		for (BankClientEntities a : clientsFromDB) {
			if (a.getId().equals(clientId))
				return a;
		}
		return null;

	}

	// POST - create new client
	@RequestMapping(value = "", method = RequestMethod.POST)
	public BankClientEntities createNewClient(@RequestBody BankClientEntities newClient) {
		// insert data into DB
		List<BankClientEntities> clientsFromDB = getDB();
		newClient.setId((new Random()).nextInt());
		clientsFromDB.add(newClient);		
		
		// if all is ok, return new entity
		System.out.println("New client added : " + newClient.getName());
		return newClient;
	}
	
	// PUT - Modify existin client in database or return null if client do not exists
	@RequestMapping(value = "/{input}", method = RequestMethod.PUT)
	public BankClientEntities modifyClient(@PathVariable("input") Integer id, @RequestBody BankClientEntities client) {
		// Find client in database
		for (BankClientEntities bce : getDB()) {
			if (bce.getId().equals(id)) {

				// Modify database
				if (client.getName() != null) 	 bce.setName(client.getName());
				if (client.getSurname() != null) bce.setSurname(client.getSurname());
				if (client.getEmail() != null) 	 bce.setEmail(client.getEmail());

				// Return modified client
				System.out.println("Client modified.");
				return bce;
			}
		}

		// Return null if object do not exist in database
		System.out.println("Nothing to modify");
		return null;
	}

	// DELETE - Remove client from database. Return null if client id do not exists or deleted object.
	@RequestMapping(value = "/{input}", method = RequestMethod.DELETE)
	public BankClientEntities deleteClient(@PathVariable("input") Integer id) {
		// Get database
		List<BankClientEntities> base = getDB();		
		
		// Find client in database
		for (BankClientEntities bce : getDB()) {
			if (bce.getId().equals(id)) {

				// Modify database
				base.remove(bce);

				// Return modified client
				System.out.println("Client deleted.");
				return bce;
			}
		}

		// Return null if object do not exist in database
		System.out.println("Nothing to delete.");
		return null;
	}
	
	// Find client by name and surename
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public List<BankClientEntities> findByName(@RequestParam("Name") String name, @RequestParam("Surname") String surname) {
		List<BankClientEntities> result = new ArrayList<BankClientEntities>();
		
		// If client is found by name and surname return it
		for (BankClientEntities bce : getDB()) {
			if (bce.getName().equalsIgnoreCase(name) && 
				bce.getSurname().equalsIgnoreCase(surname)) {	
				System.out.println("Client Found.");
				result.add(bce);
			}
		}
		return result;
	}

	
	
	// Zadatak 1.
	
	// Return all client emails in a list
	@RequestMapping(value = "/emails", method=RequestMethod.GET)
	public List<String> getAllEmails() {		
		// Populate result list with all emails
		List<String> list = new ArrayList<String>();
		for (BankClientEntities bce : getDB()) {
			list.add(bce.getEmail());
		}
		return list;
	}

	// Return list of all clients with name that contains given letter
	@RequestMapping(value="/clients/{firstLetter}", method = RequestMethod.GET)
	public List<String> getAllNamesWithFirstLetter(@PathVariable("firstLetter") char chr) {
		
		// http://localhost:8090/bankclients/clients/i
		
		// Get database
		List<BankClientEntities> base = getDB();
		
		// Populate result list with names that contains given letter
		List<String> list = new ArrayList<String>();
		for (BankClientEntities bce : base) {
			if (bce.getName().toLowerCase().charAt(0) == chr || 
				bce.getName().toUpperCase().charAt(0) == chr)
			{
				list.add(bce.getName());
			}
		}
		return list;
	}
	
	// Return list of all clients with name and surenames that contains given letter
	@RequestMapping(value="/clients/firstLetters", method = RequestMethod.GET)
	public List<String> getAllNamesAndSurenamesWithLetter(@RequestParam(value="NameChar") Character chrN, @RequestParam(value="SurnameChar") Character chrS) {
		// Populate result list with names that contains given letter
		List<String> list = new ArrayList<String>();
		for (BankClientEntities bce : getDB()) {
			if ((bce.getName().contains(chrN.toString().toLowerCase()) || 
				 bce.getName().contains(chrN.toString().toUpperCase())) &&
				(bce.getSurname().contains(chrS.toString().toLowerCase()) || 
				 bce.getSurname().contains(chrS.toString().toUpperCase())))
			{
				list.add(bce.getName());
			}
		}
		return list;
	}

	// Return sorted names of clients
	@RequestMapping(value="/clients/sort/{order}", method = RequestMethod.GET)
	public List<String> getSortedNames(@PathVariable("order") String sortOrder) {
		// Get database
		List<BankClientEntities> base = getDB();

		// Populate list with names
		List<String> list = new ArrayList<String>();
		for (BankClientEntities bce : base) {
			list.add(bce.getName());
		}

		switch (sortOrder.toUpperCase()) {
		case "ASC":
			Collections.sort(list);
			return list;		
		case "DESC":
			Collections.sort(list, Collections.reverseOrder());
			return list;
		default:
			return list;
		}
	}

	
	// Zadatak 2.
	
	@RequestMapping(value="/clients/bonitet", method = RequestMethod.GET)
	public List<BankClientEntities> changeBonitet() {		
		// Get database
		List<BankClientEntities> base = getDB();
		
		for (BankClientEntities bce : base) {
			if (bce.getAges() > 65) {
				bce.setBonitet('N');
			} else {
				bce.setBonitet('P');
			}
		}
		return base;
	}
	
	@RequestMapping(value="/clients/delete", method = RequestMethod.DELETE)
	public List<BankClientEntities> deleteIncompleteClients() {
		// Get database
		List<BankClientEntities> base = getDB();
		
		// Remove from list
		for (int i=0; i<base.size(); i++)
		{
			if (base.get(i).anythingNull()) {
				base.remove(base.get(i));
			}
		}

		return base;
	}
	
}
