package net.tmclean.pettracker.db.model.species;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table( name="species" )
public class Species {

	@Id
	@GeneratedValue( generator="uuid" )
	@GenericGenerator( name="uuid", strategy="uuid2" )
	private String id;
	
	@NotNull
	@Column( name="name" )
	private String name;

	@NotNull
	@Column( name="scientificName" )
	private String scientificName;

	public Species() {}
	
	public String getId() { return id; }
	public Species setId(String id) { 
		this.id = id;
		return this;
	}
	
	public String getName() { return name; }
	public Species setName(String name) { 
		this.name = name; 
		return this;
	}

	public String getScientificName() { return scientificName; }
	public Species setScientificName(String scientificName) { 
		this.scientificName = scientificName; 
		return this;
	}
	
	@Override
	public String toString() {
		return
			"ID:        " + this.id + "\n" +
			"Name:      " + this.name + "\n" +
			"Sci. Name: " + this.scientificName;
	}
}
