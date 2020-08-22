package net.tmclean.pettracker.db.service.impl;

import java.util.List;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import io.micronaut.transaction.annotation.ReadOnly;
import net.tmclean.pettracker.db.model.event.Event;
import net.tmclean.pettracker.db.model.event.EventItem;
import net.tmclean.pettracker.db.model.pet.Pet;
import net.tmclean.pettracker.db.service.PetEventService;
import net.tmclean.pettracker.db.service.PetService;

@Singleton
public class PetEventServiceImpl implements PetEventService {

	private final EntityManager entityManager;
	private final PetService petService;
	
	public PetEventServiceImpl( final EntityManager entityManager, final PetService petService ) {
		this.entityManager = entityManager;
		this.petService = petService;
	}
	
	@Override
	@Transactional
	public Event addEventForPet( String petId, Event event ) {
		Pet pet = this.petService.getPet( petId );
		event.setPet( pet );
		
		if( event.getItems() != null ) {
			for( EventItem item : event.getItems() ) {
				this.entityManager.persist( item );
			}
		}
		
		this.entityManager.persist( event );
		return event;
	}
	
	@Override
	@ReadOnly
	public List<Event> getEventsForPet( String petId ) {
		
		Pet pet = this.petService.getPet( petId );
		
		CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Event> cq = cb.createQuery( Event.class );
		Root<Event> root = cq.from( Event.class );
		cq = cq.select( root ).where( cb.equal( root.get( "pet"), pet ) );
		
		TypedQuery<Event> allQuery = this.entityManager.createQuery( cq );
		
		return allQuery.getResultList();
	}	
}
