package com.krushna.springbootelasticcache.model;

import java.io.Serializable;

public class Product implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name;
	String id;
	String description;

	public String getName() {
		return name;
	}

	public void setName(String name)  {
		this.name = name;
	}

	public Product(String name, String id, String description) {
		super();
		this.name = name;
		this.id = id;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
