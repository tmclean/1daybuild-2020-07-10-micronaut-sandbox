package net.tmclean.pettracker.db.service.impl;

import java.util.List;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;

import io.micronaut.transaction.annotation.ReadOnly;
import net.tmclean.pettracker.db.model.pet.Pet;
import net.tmclean.pettracker.db.service.PetService;

@Singleton
public class PetServiceImpl implements PetService {

	private final EntityManager entityManager;
	
	public PetServiceImpl( final EntityManager entityManager ) {
		this.entityManager = entityManager;
	}

	@Override
	@ReadOnly
	public List<Pet> getPets() {
		CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Pet> cq = cb.createQuery( Pet.class );
		
		TypedQuery<Pet> allQuery = 
			this.entityManager.createQuery( 
				cq.select( 
					cq.from( Pet.class ) 
				)
			);
		
		return allQuery.getResultList();
	}

	@Override
	@ReadOnly
	public Pet getPet( String petId ) {
		return entityManager.find( Pet.class, petId );
	}

	@Override
	@Transactional
	public Pet addPet( Pet pet ) {
		entityManager.persist( pet );
		return pet;
	}

	@Override
	@Transactional
	public Pet deletePet( String petId ) {
		Pet petToRemove = getPet( petId );
		entityManager.remove( petToRemove );
		return petToRemove;
	}
}
