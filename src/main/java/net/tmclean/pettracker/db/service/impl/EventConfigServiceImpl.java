package net.tmclean.pettracker.db.service.impl;

import java.util.List;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;

import io.micronaut.transaction.annotation.ReadOnly;
import net.tmclean.pettracker.db.model.event.EventItemType;
import net.tmclean.pettracker.db.model.event.EventType;
import net.tmclean.pettracker.db.model.event.config.EventToEventItemTypeConfig;
import net.tmclean.pettracker.db.model.event.config.EventTypeConfig;
import net.tmclean.pettracker.db.service.EventConfigService;

@Singleton
public class EventConfigServiceImpl implements EventConfigService {

	private final EntityManager entityManager;
	
	public EventConfigServiceImpl( final EntityManager entityManager ) {
		this.entityManager = entityManager;
	}
	
	@Override
	@ReadOnly
	public List<EventTypeConfig> getEventConfigs() {
		CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<EventTypeConfig> cq = cb.createQuery( EventTypeConfig.class );
		
		TypedQuery<EventTypeConfig> allQuery = 
			this.entityManager.createQuery( 
				cq.select( 
					cq.from( EventTypeConfig.class ) 
				)
			);
		
		return allQuery.getResultList();
	}

	@Override
	@ReadOnly
	public EventTypeConfig getEventConfig( String eventConfigId ) {
		return this.entityManager.find( EventTypeConfig.class, eventConfigId );
	}

	@Override
	@Transactional
	public EventTypeConfig addEventTypeConfig( EventTypeConfig config ) {
		
//		for( EventToEventItemTypeConfig itemType : config.getEventItemTypeConfigs() ) {
//			itemType.setEventTypeConfig( config );
//			entityManager.persist( itemType );
//		}
		
		entityManager.merge( config );
		
		return config;
	}
	
	@Override
	@Transactional
	public EventType addEventType( EventType event ) {
		this.entityManager.persist( event );
		return event;
	}
	
	

	/*
	 * Event Item Type methods
	 */

	@Override
	@ReadOnly
	public List<EventItemType> getEventItemTypes() {
		CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<EventItemType> cq = cb.createQuery( EventItemType.class );
		
		TypedQuery<EventItemType> allQuery = 
			this.entityManager.createQuery( 
				cq.select( 
					cq.from( EventItemType.class ) 
				)
			);
		
		return allQuery.getResultList();
	}
	
	@Override
	@ReadOnly
	public EventItemType getEventItemType( String eventItemTypeId ) {
		return this.entityManager.find( EventItemType.class, eventItemTypeId );
	}
	
	@Override
	@Transactional
	public EventItemType addEventItemType( EventItemType itemType ) {
		this.entityManager.persist( itemType );
		return itemType;
	}
}
