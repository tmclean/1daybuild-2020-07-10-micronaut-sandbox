package net.tmclean.pettracker.server.api;

import java.util.List;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import net.tmclean.pettracker.db.model.species.Species;
import net.tmclean.pettracker.db.service.SpeciesService;

@Controller( "api/species" )
public class SpeciesApiController {
	
	private final SpeciesService speciesService; 
	
	public SpeciesApiController( final SpeciesService speciesService ) {
		this.speciesService = speciesService;
	}

	@Get( produces = MediaType.APPLICATION_JSON )
	public List<Species> getAllSpecies() {
		return speciesService.getSpecies();
	}
}
