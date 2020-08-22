package net.tmclean.pettracker.server.api;

import java.util.List;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import net.tmclean.pettracker.db.model.event.Event;
import net.tmclean.pettracker.db.model.pet.Pet;
import net.tmclean.pettracker.db.service.PetEventService;
import net.tmclean.pettracker.db.service.PetService;

@Controller( "/api/pet" )
public class PetApiController {
	
	private final PetService petService;
	private final PetEventService petEventService;
	
	public PetApiController( 
		final PetService petService, 
		final PetEventService petEventService 
	) {
		this.petService = petService;
		this.petEventService = petEventService;
	}

	@Get( value="/_all", produces = MediaType.APPLICATION_JSON )
	public List<Pet> getAllPets() {
		return petService.getPets();
	}

	@Get( value="/{petId}", produces = MediaType.APPLICATION_JSON )
	public Pet getPet( @PathVariable( "petId" ) String petId ) {
		return petService.getPet( petId );
	}

	@Get( value="/{petId}/events", produces = MediaType.APPLICATION_JSON )
	public List<Event> getPetEvents( @PathVariable( "petId" ) String petId ) {
		Pet pet = petService.getPet( petId );
		return this.petEventService.getEventsForPet( pet.getId() );
	}
}
