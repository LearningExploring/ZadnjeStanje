package com.iktakademija.Dan0401_ProjekatSpeed.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iktakademija.Dan0401_ProjekatSpeed.entitires.OfferEntity;
import com.iktakademija.Dan0401_ProjekatSpeed.entitires.enums.EOfferStatus;

@RestController
@RequestMapping(path = "/project/offers")
public class OfferController {

	// Mock database
	private List<OfferEntity> MockDataBase;

	// Mock database getter
	private List<OfferEntity> getMockDataBase() {
		if (MockDataBase == null) {
			MockDataBase = new ArrayList<>();
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(Calendar.DATE, 5);
			OfferEntity o1 = new OfferEntity(1, "2 tickets for Killers concert", "Enjoy!!!", new Date(), cal.getTime(),
					100000.00, 6500.00, " ", 10, 0, EOfferStatus.WAIT_FOR_APPROVING);
			OfferEntity o2 = new OfferEntity(2, "VIVAX 24LE76T2", "Don't miss this fantastic offer!", new Date(),
					cal.getTime(), 200000.00, 16500.00, " ", 5, 0, EOfferStatus.WAIT_FOR_APPROVING);
			OfferEntity o3 = new OfferEntity(3, "Dinner for two in Aqua Doria", "Excellent offer", new Date(),
					cal.getTime(), 6000.00, 3500.00, " ", 4, 0, EOfferStatus.WAIT_FOR_APPROVING);
			MockDataBase.add(o1);
			MockDataBase.add(o2);
			MockDataBase.add(o3);
		}
		return MockDataBase;
	}

	// Random id provider
	public Integer getNewId()
	{
		return new Random().nextInt(Integer.MAX_VALUE);
	}
	
	// 3.3 kreirati REST endpoint koja vraća listu svih ponuda. putanja /project/offers
	@RequestMapping(path = "", method = RequestMethod.GET)
	public List<OfferEntity> getAllOffers() {
		
		// URL GET http://localhost:8090/project/offers
		
		return getMockDataBase();
	}
	
	// 3.4 kreirati REST endpoint koji omogućava dodavanje nove ponude
	// putanja /project/offers
	// metoda treba da vrati dodatu ponudu
	@RequestMapping(path = "", method = RequestMethod.POST)
	public OfferEntity setOffer(@RequestBody OfferEntity newOffer) {

		// URL POST http://localhost:8090/project/offers

		// Exit if nothing to add
		if (newOffer == null)
			return null;
	
		// Add new offer to database 
		newOffer.setId(getNewId());
		getMockDataBase().add(newOffer);
		return newOffer;
	}	
	
	// 3.5 kreirati REST endpoint koji omogućava izmenu postojeće ponude
	// putanja /project/offers/{id}
	// ukoliko je prosleđen ID koji ne pripada nijednoj ponuditreba da vrati null, a u suprotnom vraća podatke ponudesa izmenjenim vrednostima
	// NAPOMENA: u okviru ove metodene menjati vrednost atributa offer status
	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	public OfferEntity getAddOffer(@PathVariable(name = "id") Integer id, @RequestBody OfferEntity ofr) {
		
		// URL PUT http://localhost:8090/project/offers
		
		// Validate input
		if (id == null || ofr == null) return null;

		// Find offer in base
		OfferEntity offer = getOfferById(id);
		if (offer == null) return null;

		// Change offer status
		if (ofr.getOfferStatus() != null)
			offer.setOfferStatus(ofr.getOfferStatus());		
		return offer;		
	}
	
	// 3.6 kreirati REST endpoint koji omogućava brisanje postojeće ponude
	// putanja /project/offers/{id}
	// ukoliko je prosleđen ID koji ne pripada nijednoj ponudimetoda treba da vrati null, a u suprotnom vraća podatke o ponudikoja je obrisana
	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public OfferEntity getAddOffer(@PathVariable(name = "id") Integer id) {
		
		// URL DELETE http://localhost:8090/project/offers/2
		
		// Validate input
		if (id == null) return null;

		// Find offer in base
		OfferEntity offer = getOfferById(id);
		if (offer == null) return null;

		// Delete offer
		getMockDataBase().remove(offer);	
		return offer;		
	}	
	
	// 3.7 kreirati REST endpoint koji vraća ponudu po vrednosti prosleđenog ID-a
	// putanja /project/offers/{id}
	// u slučaju da ne postoji ponudasa traženom vrednošću ID-a vratiti null
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public OfferEntity getOfferById(@PathVariable(name = "id") Integer id) {
		
		// URL GET http://localhost:8090/project/offers/2
		
		// Validate input
		if (id == null) return null;
		
		// Find offer by id
		for (OfferEntity oe : getMockDataBase()) 
			if (oe.getId().equals(id)) return oe;
		return 	null;			
	}	
	
	// 3.8 kreirati REST endpoint koji omogućava promenu vrednosti atributa offer status postojeće ponude
	// putanja /project/offers/changeOffer/{id}/status/{status}
	// ukoliko je prosleđen ID koji ne pripada nijednoj ponudi metoda treba da vrati null, a u suprotnom vraća podatke o ponudikoja je obrisana
	@RequestMapping(path = "/changeOffer/{id}/status/{status}", method = RequestMethod.PUT)
	public OfferEntity getAddOffer(@PathVariable(name = "id") Integer id, @PathVariable(name = "status") EOfferStatus status) {
		
		// URL PUT http://localhost:8090/project/offers/changeOffer/2/status/EXPIRED
		
		// Validate input
		if (id == null || status == null) return null;

		// Find offer in base
		OfferEntity offer = getOfferById(id);
		if (offer == null) return null;

		// Change offer status
		offer.setOfferStatus(status);		
		return offer;		
	}
	
	// 3.9 kreirati REST endpoint koji omogućava pronalazak svih ponuda čijase akcijska cena nalazi u odgovarajućem rasponu
	// putanja /project/offers/findByPrice/{lowerPrice}/and/{upperPrice}
	@RequestMapping(path = "/findByPrice/{lowerPrice}/and/{upperPrice}", method = RequestMethod.GET)
	public List<OfferEntity> getFiindInRange(@PathVariable(name = "lowerPrice") Double lowerPrice, @PathVariable(name = "upperPrice") Double upperPrice) {
		
		// URL GET http://localhost:8090/project/offers/findByPrice/1000.0/and/10000.0
		
		// Validate input
		if (lowerPrice == null || upperPrice == null) return null;

		// Find offer in base
		List<OfferEntity> offers = new ArrayList<OfferEntity>();
		for (OfferEntity oe : getMockDataBase()) {
			if (oe.getActionPrice() >= lowerPrice && oe.getActionPrice() <= upperPrice)
			{
				offers.add(oe);
			}
		}	
		return offers;				
	}
		
}