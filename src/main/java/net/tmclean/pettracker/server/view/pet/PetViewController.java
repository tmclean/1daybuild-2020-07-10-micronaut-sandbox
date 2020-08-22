package net.tmclean.pettracker.server.view.pet;

import java.util.List;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.views.ModelAndView;
import net.tmclean.pettracker.db.model.event.Event;
import net.tmclean.pettracker.db.model.pet.Pet;
import net.tmclean.pettracker.db.service.PetEventService;
import net.tmclean.pettracker.db.service.PetService;

@Controller( "/pets" )
public class PetViewController {
	
	private final PetService petService;
	private final PetEventService eventService;
	
	public PetViewController( 
		final PetService petService, 
		final PetEventService eventService 
	) {
		this.petService   = petService;
		this.eventService = eventService;
	}

	@Get( "/" )
	public ModelAndView<PetsModel> getPetsPage() {
		List<Pet> pets = this.petService.getPets();
		return new ModelAndView<PetsModel>( "pet-list", new PetsModel( pets ) );
	}
	
	@Get( "/{petId}" )
	public ModelAndView<PetDetailsModel> getPetDetail( HttpRequest<?> request, @PathVariable( "petId" ) String petId ) {
		Pet pet = this.petService.getPet( petId );
		List<Event> events = this.eventService.getEventsForPet( petId );
		
		PetDetailsModel model = new PetDetailsModel();
		model.setPet( pet );
		model.setEvents( events );

		return new ModelAndView<PetDetailsModel>( "pet-detail", model );
	}
}
