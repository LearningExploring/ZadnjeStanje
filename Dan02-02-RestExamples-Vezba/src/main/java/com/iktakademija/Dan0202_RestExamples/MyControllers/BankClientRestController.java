package com.iktakademija.Dan0202_RestExamples.MyControllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.websocket.server.PathParam;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktakademija.Dan0202_RestExamples.entities.BankClientEntities;

@RestController
@RequestMapping("/bankclients")
public class BankClientRestController {

	private List<BankClientEntities> DB;

	private List<BankClientEntities> getDB() {
		if (DB == null) {
			List<BankClientEntities> clientsFromDB = new ArrayList<BankClientEntities>();
			clientsFromDB.add(new BankClientEntities(1, "Vladimir", "Dimitieski", "a@a.a"));
			clientsFromDB.add(new BankClientEntities(2, "Nebojsa", "Horvat", "a@b.a"));
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
		System.out.println("Client added : " + newClient.getName());
		return newClient;

	}
	
	// Modify existin client in database or return null if client do not exists
	@RequestMapping(value = "/{input}", method = RequestMethod.PUT)
	protected BankClientEntities modifyClient(@PathVariable("input") String id, @RequestBody BankClientEntities client) {
		// Declarations
		List<BankClientEntities> base; 	// Mock Database

		// Get database
		base = getDB();

		// Find client in database
		for (BankClientEntities bce : base) {
			if (bce.getId() == Integer.parseInt(id)) {

				// Modify database
				bce.setId((new Random()).nextInt());
				bce.setName(client.getName());
				bce.setSurname(client.getSurname());
				bce.setEmail(client.getEmail());

				// Return modified client
				System.out.println("Client modified.");
				return bce;
			}
		}

		// Return null if object do not exist in database
		System.out.println("Nothing to modify");
		return null;
	}

	// Remove client from database. Return null if client id do not exists or deleted object.
	@RequestMapping(value = "/{input}", method = RequestMethod.DELETE)
	protected BankClientEntities deleteClient(@PathVariable("input") String id) {
		// Declarations
		List<BankClientEntities> base; 	// Mock Database

		// Get database
		base = getDB();		
		
		// Find client in database
		for (BankClientEntities bce : base) {
			if (bce.getId() == Integer.parseInt(id)) {

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
	@RequestMapping(value = "/client", method = RequestMethod.GET)
	protected BankClientEntities findByName(@RequestParam("name") String name, @RequestParam("surname") String surname) {
		// Declarations
		List<BankClientEntities> base; 	// Mock Database

		// Get database
		base = getDB();	
		
		// If client is found by name and surname return it
		for (BankClientEntities bce : base) {
			if (bce.getName().equalsIgnoreCase(name) && 
				bce.getSurname().equalsIgnoreCase(surname)) {	
				System.out.println("Client Found.");
				return bce;
			}
		}
		
		// 
		System.out.println("Client Not Found.");
		return null;
	}

}
