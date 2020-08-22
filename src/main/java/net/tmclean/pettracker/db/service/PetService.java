package net.tmclean.pettracker.db.service;

import java.util.List;

import net.tmclean.pettracker.db.model.pet.Pet;

public interface PetService {
	List<Pet> getPets();
	Pet getPet( String petId );
	Pet addPet( Pet pet );
	Pet deletePet( String petId );
//
//	PetEvent getEvent( String eventId );
//	List<PetEvent> getEventsForPet( String petId );
//	PetEvent addEvent( String petId, PetEvent event );
//	PetEvent deleteEvent( String eventId );
//
//	PetEventItem getEventItem( String eventItemId );
//	List<PetEventItem> getEventItems( String eventId );
//	PetEventItem addEventItem( String eventId, PetEventItem item );
//	PetEventItem deleteEventItem( String eventItem );
}
