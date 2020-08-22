package net.tmclean.pettracker.server.view.pet;

import java.util.List;

import net.tmclean.pettracker.db.model.event.Event;
import net.tmclean.pettracker.db.model.pet.Pet;

public class PetDetailsModel {
	private Pet pet;
	private List<Event> events;
	
	public Pet getPet() { return pet; }
	public PetDetailsModel setPet(Pet pet) { 
		this.pet = pet;
		return this;
	}
	
	public List<Event> getEvents() { return events; }
	public PetDetailsModel setEvents(List<Event> events) { 
		this.events = events;
		return this;
	}
}
