package net.tmclean.pettracker.db;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import net.tmclean.pettracker.db.model.event.Event;
import net.tmclean.pettracker.db.model.event.EventItem;
import net.tmclean.pettracker.db.model.event.EventItemType;
import net.tmclean.pettracker.db.model.event.EventType;
import net.tmclean.pettracker.db.model.event.EventUnit;
import net.tmclean.pettracker.db.model.event.config.EventToEventItemTypeConfig;
import net.tmclean.pettracker.db.model.event.config.EventTypeConfig;
import net.tmclean.pettracker.db.model.pet.Pet;
import net.tmclean.pettracker.db.model.pet.PetSex;
import net.tmclean.pettracker.db.model.species.Species;
import net.tmclean.pettracker.db.model.species.config.SpeciesConfig;
import net.tmclean.pettracker.db.model.species.config.SpeciesConfigToEventItemType;
import net.tmclean.pettracker.db.model.species.config.SpeciesConfigToEventTypeConfig;
import net.tmclean.pettracker.db.service.EventConfigService;
import net.tmclean.pettracker.db.service.PetEventService;
import net.tmclean.pettracker.db.service.PetService;
import net.tmclean.pettracker.db.service.SpeciesService;

@Singleton
public class DBInitializer {

	private static Timestamp utcTimestampNow() {
		return Timestamp.valueOf( LocalDateTime.ofInstant( Instant.now(), ZoneId.of( "UTC" ) ) );
	}
	
	private final SessionFactory     sessionFactory;
	
	private final SpeciesService     speciesService;
	private final PetService         petService;
	private final PetEventService    petEventService;
	private final EventConfigService eventConfigService;
	
	public DBInitializer( 
		final SessionFactory     sessionFactory,
		final SpeciesService     speciesService, 
		final PetService         petService, 
		final PetEventService    petEventSerivce,
		final EventConfigService eventConfigService
	) {
		this.sessionFactory = sessionFactory;
		
		this.speciesService     = speciesService;
		this.petService         = petService;
		this.petEventService    = petEventSerivce;
		this.eventConfigService = eventConfigService;
	}
	
	@PostConstruct
	public void init() {
//		Session session = sessionFactory.openSession();
//		try {
			load();
//		}
//		finally {
//			if( session.isOpen() ) {
//				session.clear();
//			}
//		}
	}

	@Transactional
	public void load() {

		/*
		 * Item Types
		 */
		EventItemType lengthItemType = eventConfigService.addEventItemType(
			new EventItemType()
				.setName( "Length" )
				.setUnit( EventUnit.mm )
		);
		
		EventItemType weightItemType = eventConfigService.addEventItemType(
			new EventItemType()
				.setName( "Weight" )
				.setUnit( EventUnit.gram )
		);

		EventItemType ratEatenItemType = eventConfigService.addEventItemType(
			new EventItemType()
				.setName( "Rat (eaten)" )
				.setUnit( EventUnit.gram )
		);
		
		EventItemType ratRejectedItemType = eventConfigService.addEventItemType(
			new EventItemType()
				.setName( "Rat (rejected)" )
				.setUnit( EventUnit.gram )
		);

		EventItemType mouseEatenItemType = eventConfigService.addEventItemType(
			new EventItemType()
				.setName( "Mouse (eaten)" )
				.setUnit( EventUnit.gram )
		);

		EventItemType mouseRejectedItemType = eventConfigService.addEventItemType(
			new EventItemType()
				.setName( "Mouse (rejected)" )
				.setUnit( EventUnit.gram )
		);

		/*
		 * Event types
		 */
		EventType lengthEventType = eventConfigService.addEventType(
			new EventType()
				.setName( "Length" )
			);
			
		EventType weightEventType = eventConfigService.addEventType(
			new EventType()
				.setName( "Weight" )
		);
		
		EventType feedingEventType = eventConfigService.addEventType(
			new EventType()
				.setName( "Feeding" )
		);
		
		EventType vetEventType = eventConfigService.addEventType(
			new EventType()
				.setName( "Vet Visit" )
		);
		
		/*
		 * Event configs
		 */

		EventTypeConfig lengthEventTypeConfig = this.eventConfigService.addEventTypeConfig(
			new EventTypeConfig()
				.setEventType( lengthEventType )
				.setEventItemTypeConfigs(
					Arrays.asList(
						new EventToEventItemTypeConfig()
							.setColor( "FF0000" )
							.setEventItemType( lengthItemType )
					)
				)
		);

		EventTypeConfig weightEventTypeConfig = this.eventConfigService.addEventTypeConfig(
			new EventTypeConfig()
				.setEventType( weightEventType )
				.setEventItemTypeConfigs(
					Arrays.asList(
						new EventToEventItemTypeConfig()
							.setColor( "00FF00" )
							.setEventItemType( weightItemType )
					)
				)
		);

		EventTypeConfig feedingEventTypeConfig = this.eventConfigService.addEventTypeConfig(
			new EventTypeConfig()
				.setEventType( feedingEventType )
				.setEventItemTypeConfigs(
					Arrays.asList(
						new EventToEventItemTypeConfig()
							.setColor( "FF0000" )
							.setEventItemType( ratRejectedItemType ),

						new EventToEventItemTypeConfig()
							.setColor( "00FF00" )
							.setEventItemType( ratEatenItemType ),

						new EventToEventItemTypeConfig()
							.setColor( "FF0000" )
							.setEventItemType( mouseRejectedItemType ),

						new EventToEventItemTypeConfig()
							.setColor( "00FF00" )
							.setEventItemType( ratEatenItemType )
					)
				)
		);

		EventTypeConfig vetEventTypeConfig = this.eventConfigService.addEventTypeConfig(
			new EventTypeConfig()
				.setEventType( vetEventType )
				.setEventItemTypeConfigs(
					Arrays.asList(
						new EventToEventItemTypeConfig()
							.setColor( "0000FF" )
							.setEventItemType( weightItemType ),

						new EventToEventItemTypeConfig()
							.setColor( "0000FF" )
							.setEventItemType( lengthItemType )
					)
				)
		);

		/*
		 * Species
		 */
		SpeciesConfig ballPythonSpeciesConfig = this.speciesService.addSpeciesConfig(
			new SpeciesConfig()
				.setSpecies(
					new Species()
						.setName( "Ball Python" )
						.setScientificName( "Python Regius" ) 
				)
				.setEventTypeConfigs(
					Arrays.asList(
						new SpeciesConfigToEventTypeConfig()
							.setEventTypeConfig( lengthEventTypeConfig ),

						new SpeciesConfigToEventTypeConfig()
							.setEventTypeConfig( weightEventTypeConfig ),

						new SpeciesConfigToEventTypeConfig()
							.setEventTypeConfig( feedingEventTypeConfig ),

						new SpeciesConfigToEventTypeConfig()
							.setEventTypeConfig( vetEventTypeConfig )
					)
				)
		);
		
		Species ballPythonSpecies = ballPythonSpeciesConfig.getSpecies();
		
		/*
		 * Pets
		 */
		Pet gozer = this.petService.addPet( 
			new Pet()
				.setName( "Gozer" )
				.setSex( PetSex.male )
				.setSpecies( ballPythonSpecies )
		 );


		this.petEventService.addEventForPet( 
			gozer.getId(), 
			new Event()
				.setEventType( feedingEventType )
				.setEventTime( utcTimestampNow() )
				.setNotes( "Very hungry!" )
				.setItems(  Arrays.asList( 
					new EventItem()
						.setItemType( ratEatenItemType )
						.setValue( 140 )
						.setNotes( "Ate the rat" ), 
						
					new EventItem()
						.setItemType( ratEatenItemType )
						.setValue( 150 ) 
						.setNotes( "Ate the ratto. :D" )
					) 
				)
		);
		
		this.petEventService.addEventForPet(
			gozer.getId(),
			new Event()
				.setEventType( weightEventType )
				.setEventTime( utcTimestampNow() )
				.setItems( 
					Arrays.asList(
						new EventItem()
							.setItemType( weightItemType )
							.setNotes( "Thiccc" )
							.setValue( 1357.9 )
					)
				)
				.setNotes( "Thicc boi...." )
		);
		
		
		this.petEventService.addEventForPet(
			gozer.getId(),
			new Event()
				.setEventType( vetEventType )
				.setEventTime( utcTimestampNow() )
				.setItems(
					Arrays.asList(
						new EventItem()
							.setItemType( lengthItemType )
							.setValue( 1219.2 )
							.setNotes( "Long boi!" ),

						new EventItem()
							.setItemType( weightItemType )
							.setValue( 1234.5 )
							.setNotes( "Thicc boi!" )
					)
				)
		);

		this.petEventService.addEventForPet( 
			gozer.getId(), 
			new Event()
				.setEventType( feedingEventType )
				.setEventTime( utcTimestampNow() )
				.setNotes( "Not very hungry..." )
				.setItems(  
					Arrays.asList( 
						new EventItem()
							.setItemType( mouseEatenItemType )
							.setValue( 70 )
							.setNotes( "Ate the mouse"), 
							
						new EventItem()
							.setItemType( ratRejectedItemType )
							.setValue( 150 ) 
							.setNotes( "Rejected the ratto. :(" ),
							
						new EventItem()
							.setItemType( mouseRejectedItemType )
							.setValue( 78 )
							.setNotes( "Rejected the second mouse. :(" )
					)		
				)
		);
		
		this.petEventService.addEventForPet(
			gozer.getId(),
			new Event()
				.setEventType( lengthEventType )
				.setEventTime( utcTimestampNow() )
				.setItems(
					Arrays.asList(
						new EventItem()
							.setItemType( lengthItemType )
							.setValue( 1250.2 )
							.setNotes( "Long boi!" )
					)
				)
		);
		
		/*
		 * TEST data access
		 */
		
		//
		// List species 
		//
		for( Species s : this.speciesService.getSpecies() ) {
			System.out.println( "----------------------------------------" );
			System.out.println( s );
			System.out.println( "----------------------------------------" );
		}
		
		//
		// List species config
		//
		for( SpeciesConfig s : this.speciesService.getSpeciesConfigs() ) {
			System.out.println( "----------------------------------------" );
			System.out.println( "ID: " + s.getId() );
			System.out.println( "Species: " + s.getSpecies().getName() );
			System.out.println( "Event Types: ");
			for( SpeciesConfigToEventTypeConfig c : s.getEventTypeConfigs() ) {
				System.out.println( "   " + c.getEventTypeConfig().getEventType().getName() );
				System.out.println( "   Event Item Types: " );
				for( EventToEventItemTypeConfig eventItemConfig : c.getEventTypeConfig().getEventItemTypeConfigs() ) {
					EventItemType eventItemType = eventItemConfig.getEventItemType();
					System.out.println( "      " + eventItemType.getName() + " | unit=" + eventItemType.getUnit() );
				}
			}
			System.out.println( "Event Item Types: ");
			for( SpeciesConfigToEventItemType c : s.getEventItemTypes() ) {
				EventItemType eventItemType = c.getEventItemType();
				System.out.println( "   " + eventItemType.getName() + " | unit=" + eventItemType.getUnit() );
			}
			System.out.println( "----------------------------------------" );
		}
	}
}
