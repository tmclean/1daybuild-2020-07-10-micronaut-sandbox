package net.tmclean.pettracker.server.view.admin.model;

import net.tmclean.pettracker.db.model.event.config.EventTypeConfig;

public class EventTypeConfigDetailModel {
	private EventTypeConfig eventTypeConfig;

	public EventTypeConfig getEventTypeConfig() { return eventTypeConfig; }
	public EventTypeConfigDetailModel setEventTypeConfig(EventTypeConfig eventTypeConfig) {
		this.eventTypeConfig = eventTypeConfig;
		return this;
	}
}
