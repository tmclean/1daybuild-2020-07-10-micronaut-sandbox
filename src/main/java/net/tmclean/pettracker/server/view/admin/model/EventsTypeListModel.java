package net.tmclean.pettracker.server.view.admin.model;

import java.util.List;

import net.tmclean.pettracker.db.model.event.EventItemType;
import net.tmclean.pettracker.db.model.event.config.EventTypeConfig;

public class EventsTypeListModel {
	private List<EventTypeConfig> eventTypeConfigs;
	private List<EventItemType> eventItemTypes;

	public List<EventTypeConfig> getEventTypeConfigs() { return eventTypeConfigs; }
	public EventsTypeListModel setEventTypeConfigs(List<EventTypeConfig> eventTypeConfigs) {
		this.eventTypeConfigs = eventTypeConfigs;
		return this;
	}
	
	public List<EventItemType> getEventItemTypes() { return eventItemTypes; }
	public EventsTypeListModel setEventItemTypes(List<EventItemType> eventItemTypes) {
		this.eventItemTypes = eventItemTypes;
		return this;
	}
}
