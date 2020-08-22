package net.tmclean.pettracker.db.service;

import java.util.List;

import net.tmclean.pettracker.db.model.event.EventItemType;
import net.tmclean.pettracker.db.model.event.EventType;
import net.tmclean.pettracker.db.model.event.config.EventTypeConfig;

public interface EventConfigService {

	List<EventTypeConfig> getEventConfigs();
	EventTypeConfig getEventConfig( String eventConfigId );
	EventTypeConfig addEventTypeConfig( EventTypeConfig config );
	
	EventType addEventType( EventType event );
	
	List<EventItemType> getEventItemTypes();
	EventItemType getEventItemType( String eventItemTypeId );
	EventItemType addEventItemType( EventItemType itemType );	
}
