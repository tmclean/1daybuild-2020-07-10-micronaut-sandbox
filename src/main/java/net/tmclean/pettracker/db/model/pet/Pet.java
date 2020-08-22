package net.tmclean.pettracker.db.model.pet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import net.tmclean.pettracker.db.model.species.Species;

@Entity
@Table( name="pet" )
public class Pet  {

	private String  id;
	private Species species;
	private PetSex  sex;
	private String  name;

	@Id
	@GeneratedValue( generator="uuid" )
	@GenericGenerator( name="uuid", strategy="uuid2" )
	public String getId() { return id; }
	public Pet setId(String id) { 
		this.id = id; 
		return this;
	}

	@NotNull
	@ManyToOne( optional=false )
	public Species getSpecies() { return species; }
	public Pet setSpecies(Species species) { 
		this.species = species;
		return this;
	}

	@NotNull
	@Column( name="sex" )
	public PetSex getSex() { return sex; }
	public Pet setSex(PetSex sex) {
		this.sex = sex;
		return this;
	}
	
	@NotNull
	@Column( name="name" )
	public String getName() { return name; }
	public Pet setName(String name) { 
		this.name = name; 
		return this;
	}
}
