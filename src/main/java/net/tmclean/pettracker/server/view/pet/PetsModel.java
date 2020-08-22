package net.tmclean.pettracker.server.view.pet;

import java.util.List;

import net.tmclean.pettracker.db.model.pet.Pet;

public class PetsModel {
	private List<Pet> pets;
	
	public PetsModel( List<Pet> pets ) {
		this.pets = pets;
	}

	public List<Pet> getPets() { return pets; }
	public PetsModel setPets(List<Pet> pets) { 
		this.pets = pets;
		return this;
	}
}
