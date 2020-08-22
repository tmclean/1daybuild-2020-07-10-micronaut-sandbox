package net.tmclean.pettracker.server.view.admin;

import java.util.List;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.views.ModelAndView;
import net.tmclean.pettracker.db.model.event.EventItemType;
import net.tmclean.pettracker.db.model.event.config.EventTypeConfig;
import net.tmclean.pettracker.db.service.EventConfigService;
import net.tmclean.pettracker.server.view.admin.model.EventItemTypeDetailModel;
import net.tmclean.pettracker.server.view.admin.model.EventItemTypeDetailModel.Mode;
import net.tmclean.pettracker.server.view.admin.model.EventTypeConfigDetailModel;
import net.tmclean.pettracker.server.view.admin.model.EventsTypeListModel;

@Controller( "/admin/events" )
public class AdminEventConfigController {
	
	private final EventConfigService eventConfigService;
	
	public AdminEventConfigController( 
		final EventConfigService eventConfigService
	) {
		this.eventConfigService = eventConfigService;
	}

	@Get( "/" )
	public ModelAndView<EventsTypeListModel> getAdminEventTypeConfigList() {
		
		List<EventTypeConfig> eventTypeConfigs = this.eventConfigService.getEventConfigs();
		List<EventItemType> eventItemTypes = this.eventConfigService.getEventItemTypes();
		
		return new ModelAndView<EventsTypeListModel>( 
			"admin/event-type-config-list", 
			new EventsTypeListModel()
				.setEventTypeConfigs( eventTypeConfigs )
				.setEventItemTypes( eventItemTypes )
		);
	}
	
	@Get( "/{eventConfigId}" )
	public ModelAndView<EventTypeConfigDetailModel> getAdminEventTypeConfigDetail(
		@PathVariable( "eventConfigId" ) String eventConfigId 
	) {
		EventTypeConfig eventTypeConfig = this.eventConfigService.getEventConfig( eventConfigId );
		return new ModelAndView<EventTypeConfigDetailModel>( 
			"admin/event-type-config-detail", 
			new EventTypeConfigDetailModel()
				.setEventTypeConfig( eventTypeConfig )
		);
	}
	
	/*
	 * Event item type methods
	 */
	@Get( "/itemType/create" )
	public ModelAndView<EventItemTypeDetailModel> showCreateEventItemType() {
		return new ModelAndView<EventItemTypeDetailModel>( 
			"admin/event-item-type-detail", 
			new EventItemTypeDetailModel()
				.setMode( Mode.create )
		);
	}

	@Post( 
		value="/itemType/create", 
		consumes=MediaType.APPLICATION_JSON, 
		produces=MediaType.APPLICATION_JSON 
	)
	public EventItemType doEditEventItemType( @Body EventItemTypeDetailModel model ) {
		EventItemType itemType = model.getItemType();
		if( model.getMode() == Mode.create ) {
			itemType.setId( null );
			return this.eventConfigService.addEventItemType( itemType );
		}
		
		return null;
	}
}
