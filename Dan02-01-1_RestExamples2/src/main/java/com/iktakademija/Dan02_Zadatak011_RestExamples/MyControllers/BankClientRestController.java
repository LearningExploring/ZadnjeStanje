package com.iktakademija.Dan02_Zadatak011_RestExamples.MyControllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iktakademija.Dan02_Zadatak011_RestExamples.entities.BankClientEntities;

@RestController
@RequestMapping("/bankclients")
public class BankClientRestController {

	private List<BankClientEntities> getDB() {
		List<BankClientEntities> clientsFromDB = new ArrayList<BankClientEntities>();
		clientsFromDB.add(new BankClientEntities(1, "Vladimir", "Dimitieski", "a@a.a"));
		clientsFromDB.add(new BankClientEntities(2, "Nebojsa", "Horvat", "a@b.a"));

		// return all clients to web FE
		return clientsFromDB;
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
	// deo putanje u {} zagradama. u putanji je posle /
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
		return newClient;

	}

//	// PUT - modify existing client
//	@RequestMapping("/{id}")
//	public void asd() {
//
//	}
//
//	// DELETE - delete existing client
//	@RequestMapping("/{id}")
//	public void asd() {
//
//	}

}
