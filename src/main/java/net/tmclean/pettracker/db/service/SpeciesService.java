package net.tmclean.pettracker.db.service;

import java.util.List;

import net.tmclean.pettracker.db.model.species.Species;
import net.tmclean.pettracker.db.model.species.config.SpeciesConfig;

public interface SpeciesService {
	List<Species> getSpecies();
	Species getSpecies( String speciesId );

	List<SpeciesConfig> getSpeciesConfigs();
	SpeciesConfig getSpeciesconfig( String speciesConfigId );
	SpeciesConfig addSpeciesConfig( SpeciesConfig speciesConfig );
}
