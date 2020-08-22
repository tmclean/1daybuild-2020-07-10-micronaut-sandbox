package net.tmclean.pettracker.db.service.impl;

import java.util.List;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;

import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Join.Type;
import io.micronaut.transaction.annotation.ReadOnly;
import net.tmclean.pettracker.db.model.species.Species;
import net.tmclean.pettracker.db.model.species.config.SpeciesConfig;
import net.tmclean.pettracker.db.model.species.config.SpeciesConfigToEventItemType;
import net.tmclean.pettracker.db.model.species.config.SpeciesConfigToEventTypeConfig;
import net.tmclean.pettracker.db.service.SpeciesService;

@Singleton
public class SpeciesServiceImpl implements SpeciesService {

	private final EntityManager entityManager;
	
	public SpeciesServiceImpl( final EntityManager entityManager ) {
		this.entityManager = entityManager;
	}
	
	@Override
	@ReadOnly
	public List<Species> getSpecies() {
		CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Species> cq = cb.createQuery( Species.class );
		
		TypedQuery<Species> allQuery = 
			this.entityManager.createQuery( 
				cq.select( 
					cq.from( Species.class ) 
				)
			);
		
		return allQuery.getResultList();
	}
	
	@Override
	@ReadOnly
	public Species getSpecies( final String speciesId ) {
		return entityManager.find( Species.class, speciesId );
	}
	

	/*
	 * Species config methods
	 */
	
	@Override
	@ReadOnly
	public List<SpeciesConfig> getSpeciesConfigs() {
		CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<SpeciesConfig> cq = cb.createQuery( SpeciesConfig.class );
		
		TypedQuery<SpeciesConfig> allQuery = 
			this.entityManager.createQuery( 
				cq.select( 
					cq.from( SpeciesConfig.class ) 
				)
			);
		
		List<SpeciesConfig> configs = allQuery.getResultList();
//		for( SpeciesConfig config : configs ) {
//			config.getSpecies();
//			config.getEventItemTypes();
//			config.getEventTypeConfigs();
//		}
		
		return configs;
	}
	
	@Override
	@ReadOnly
	public SpeciesConfig getSpeciesconfig( final String speciesConfigId ) {
		return entityManager.find( SpeciesConfig.class, speciesConfigId );
	}
	

	@Override
	@Transactional
	public SpeciesConfig addSpeciesConfig( final SpeciesConfig speciesConfig ) {
		
		Species species = speciesConfig.getSpecies();
		entityManager.persist( species );
		
		entityManager.merge( speciesConfig );
		
		speciesConfig.getSpecies();
		
		return speciesConfig;
	}
	
}
