package com.iktakademija.Dan0401_ProjekatSpeed.entitires;

import java.util.Random;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CategoryEntity {
	protected Integer id;
	protected String name;
	protected String description;

	public CategoryEntity() {
		super();
	}

	public CategoryEntity(Integer id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@JsonIgnore
	public Integer getNewId()
	{
		return new Random().nextInt();
	}

	@Override
	public String toString() {
		return "CategoryEntity [id=" + id + ", name=" + name + ", description=" + description + "]";
	}	

}
