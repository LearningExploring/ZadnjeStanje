package com.iktakademija.Dan0304_Projekat.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iktakademija.Dan0304_Projekat.entities.CategoryEntity;

@RestController
@RequestMapping(path = "/project/categories")
public class CategoryController {
	
	// Mock databaseK 
	private List<CategoryEntity> DB;

	// Get mock databaseK 
	private List<CategoryEntity> getDB() {
		if (DB == null) {
			List<CategoryEntity> categories = new ArrayList<CategoryEntity>();
			CategoryEntity c1 = new CategoryEntity(1, "music", "description 1");
			CategoryEntity c2 = new CategoryEntity(2, "food", "description 2");
			CategoryEntity c3 = new CategoryEntity(3, "entertainment", "description 3");

			categories.add(c1);
			categories.add(c2);
			categories.add(c3);
			DB = categories;
		}
		return DB;
	}
	
	// 2.3 kreirati REST endpoint koji vraća listu svih kategorija
	// putanja /project/categories
	@RequestMapping(path = "", method = RequestMethod.GET)
	public List<CategoryEntity> getAllCategories() {
		
		// URL GET http://localhost:8090/project/categories
		
		// Retur list of all categories
		return getDB();
	}
	
	// 2.4 kreirati REST endpoint koji omogućava dodavanje nove kategorije
	// putanja /project/categories
	// metoda treba da vrati dodatu kategoriju
	@RequestMapping(path = "", method = RequestMethod.POST)
	public CategoryEntity addCategory(@RequestBody CategoryEntity cat) {
		
		// URL POST http://localhost:8090/project/categories
		
		// Check parameter to add
		if (cat == null) return null;
		
		// Add and return new category
		cat.setId(cat.getNewId());
		getDB().add(cat);				
		return cat;
	}
	
	// 2.5 kreirati REST endpoint koji omogućava izmenu postojeće kategorije
	// putanja /project/categories/{id}
	// ukoliko je prosleđen ID koji ne pripada nijednoj kategoriji metoda treba da vrati null, a u suprotnom vraća podatke kategorije sa izmenjenim vrednostima
	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	public CategoryEntity changeCategory(@PathVariable(name = "id") Integer id, @RequestBody CategoryEntity cat) {
		
		// URL PUT http://localhost:8090/project/categories/2
		
		// Check input parameter validity
		if (id == null || cat == null) return null;

		// Find in database and return if found
		for (CategoryEntity ce : getDB())
			if (ce.getId().equals(id)) {
				if (cat.getDescription() != null)
					ce.setDescription(cat.getDescription());
				if (cat.getName() != null)
					ce.setName(cat.getName());
				return ce;
			}
		return null;
	}
	
	// 2.6 kreirati REST endpoint koji omogućava brisanje postojeće kategorije
	// putanja /project/categories/{id}
	// ukoliko je prosleđen ID koji ne pripada nijednoj kategoriji metoda treba da vrati null, a u suprotnom vraća podatke o kategoriji koja je obrisana
	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public CategoryEntity removeCategory(@PathVariable(name = "id") Integer id) {
		
		// URL DELETE http://localhost:8090/project/categories/2
		
		// Check input parameter validity
		if (id == null) return null;
		
		// Remove if exists 
		for (CategoryEntity ce : getDB()) {
			if (ce.getId().equals(id)) {
				getDB().remove(ce);
				return ce;
			}
		}
		return null;
	}
	
	// 2.7 kreirati REST endpoint koji vraća kategoriju po vrednosti prosleđenog ID-a
	// putanja /project/categories/{id}
	// u slučaju da ne postoji kategorija sa traženom vrednošću ID-a vratiti null
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public CategoryEntity getAllCategories(@PathVariable(name = "id") Integer id) {

		// URL GET http://localhost:8090/project/categories/2

		// Check input parameter validity
		if (id == null) return null;

		// Remove if exists
		for (CategoryEntity ce : getDB())
			if (ce.getId().equals(id))
				return ce;
		return null;
	}

}
