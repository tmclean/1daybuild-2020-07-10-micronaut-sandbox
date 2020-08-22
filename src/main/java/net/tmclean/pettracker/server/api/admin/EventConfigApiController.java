package net.tmclean.pettracker.server.api.admin;

import java.util.List;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import net.tmclean.pettracker.db.model.event.config.EventTypeConfig;
import net.tmclean.pettracker.db.service.EventConfigService;

@Controller( "/api/config/event" )
public class EventConfigApiController {

	private final EventConfigService eventConfigService;
	
	public EventConfigApiController( final EventConfigService eventConfigService ) {
		this.eventConfigService = eventConfigService;
	}
	
	@Get( produces=MediaType.APPLICATION_JSON )
	public List<EventTypeConfig> getAllEventConfigs() {
		return eventConfigService.getEventConfigs();
	}
	
	@Get( value="/{eventConfigId}", produces=MediaType.APPLICATION_JSON )
	public EventTypeConfig getEventConfig( @PathVariable( "eventConfigId" ) String eventConfigId ) {
		return eventConfigService.getEventConfig( eventConfigId );
	}
}
