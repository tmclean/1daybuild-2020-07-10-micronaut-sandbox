package net.tmclean.pettracker;

import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.annotation.MicronautTest;
import net.tmclean.pettracker.db.model.event.EventItemType;
import net.tmclean.pettracker.db.model.event.EventType;
import net.tmclean.pettracker.db.model.event.EventUnit;
import net.tmclean.pettracker.db.model.event.config.EventTypeConfig;
import net.tmclean.pettracker.db.model.species.Species;
import net.tmclean.pettracker.db.model.species.config.SpeciesConfig;
import net.tmclean.pettracker.db.model.species.config.SpeciesConfigToEventItemType;
import net.tmclean.pettracker.db.model.species.config.SpeciesConfigToEventTypeConfig;
import net.tmclean.pettracker.db.service.SpeciesService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

@MicronautTest
public class PetTrackerTest {

    @Inject
    EmbeddedServer application;
    
    @Inject
    SpeciesService speciesService;

    @Inject
    @Client( "/" )
    HttpClient client;
    
    @Test
    void testItWorks() {
        Assertions.assertTrue(application.isRunning());
    }

    @Test
    void speciesServiceTest() {
    	
    	EventItemType itemType1 = new EventItemType().setName( "test-item-1" ).setUnit( EventUnit.none );
    	EventItemType itemType2 = new EventItemType().setName( "test-item-2" ).setUnit( EventUnit.mm   );
    	EventItemType itemType3 = new EventItemType().setName( "test-item-3" ).setUnit( EventUnit.gram );

    	EventTypeConfig eventType1 = new EventTypeConfig()
			.setEventType(
				new EventType().setName( "test-event-type-1" )
			);
    	
    	EventTypeConfig eventType2 = new EventTypeConfig()
			.setEventType(
				new EventType().setName( "test-event-type-2" )
			);
    	
    	EventTypeConfig eventType3 = new EventTypeConfig()
			.setEventType(
				new EventType().setName( "test-event-type-3" )
			);
    	
    	Species species = new Species();
    	species.setName( "test-species" );
    	species.setScientificName( "test-species-scienttific-name" );
    	
    	List<SpeciesConfigToEventTypeConfig> eventTypeConfigs = Arrays.asList(
    		new SpeciesConfigToEventTypeConfig()
    			.setEventTypeConfig( eventType1 ),

    		new SpeciesConfigToEventTypeConfig()
    			.setEventTypeConfig( eventType2 ),

    		new SpeciesConfigToEventTypeConfig()
    			.setEventTypeConfig( eventType3 )
    	);
    	
    	List<SpeciesConfigToEventItemType> itemTypeConfigs = Arrays.asList(
			new SpeciesConfigToEventItemType().setEventItemType( itemType1 ),
			new SpeciesConfigToEventItemType().setEventItemType( itemType2 ),
			new SpeciesConfigToEventItemType().setEventItemType( itemType3 )
		);
    	
    	SpeciesConfig speciesConfig = new SpeciesConfig()
	    	.setSpecies( species )
	    	.setEventTypeConfigs( eventTypeConfigs )
	    	.setEventItemTypes( itemTypeConfigs );
    	
    	SpeciesConfig newConfig = this.speciesService.addSpeciesConfig( speciesConfig );
    	
    	assertNotNull( newConfig );
    	assertNotNull( newConfig.getId() );
    	assertNotNull( species.getId() );
    	
    	assertEquals( species.getName(),           newConfig.getSpecies().getName()           );
    	assertEquals( species.getScientificName(), newConfig.getSpecies().getScientificName() );
    	
    	assertNotNull( newConfig.getEventTypeConfigs() );
    	assertTrue( newConfig.getEventTypeConfigs().size() == eventTypeConfigs.size() );
    	
    	for( SpeciesConfigToEventTypeConfig config : newConfig.getEventTypeConfigs() ) {
    		assertNotNull( config );
    		assertNotNull( config.getId() );
    		assertNotNull( config.getEventTypeConfig() );
    		assertNotNull( config.getEventTypeConfig().getId() );
    		assertNotNull( config.getEventTypeConfig().getEventType() );
    		assertNotNull( config.getEventTypeConfig().getEventType().getId() );
    	}
    	
    	assertNotNull( newConfig.getEventItemTypes() );
    	assertTrue( newConfig.getEventItemTypes().size() == itemTypeConfigs.size() );
    	
    	for( SpeciesConfigToEventItemType config : newConfig.getEventItemTypes() ) {
    		assertNotNull( config );
    		assertNotNull( config.getId() );
    		assertNotNull( config.getEventItemType() );
    		assertNotNull( config.getEventItemType().getId() );
    		assertNotNull( config.getEventItemType().getUnit() );
    	}
    }
}
