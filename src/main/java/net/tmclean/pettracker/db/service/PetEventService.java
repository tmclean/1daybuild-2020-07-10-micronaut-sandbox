package net.tmclean.pettracker.db.service;

import java.util.List;

import net.tmclean.pettracker.db.model.event.Event;

public interface PetEventService {

	List<Event> getEventsForPet( String petId );
	Event addEventForPet( String petId, Event event );
}
