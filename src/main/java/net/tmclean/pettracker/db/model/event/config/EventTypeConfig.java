package net.tmclean.pettracker.db.model.event.config;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import net.tmclean.pettracker.db.model.event.EventType;

@Entity
@Table( name="event_type_config" )
public class EventTypeConfig {

	@Id
	@GeneratedValue( generator="uuid" )
	@GenericGenerator( name="uuid", strategy="uuid2" )
	private String id;
	
	@OneToOne( cascade = CascadeType.ALL, fetch = FetchType.EAGER )
	private EventType eventType;
	
	@OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "eventTypeConfig" )
	private List<EventToEventItemTypeConfig> eventItemTypeConfigs = new ArrayList<>();

	public String getId() { return id; }
	public EventTypeConfig setId(String id) {
		this.id = id;
		return this;
	}

	public EventType getEventType() { return eventType; }
	public EventTypeConfig setEventType(EventType eventType) {
		this.eventType = eventType;
		return this;
	}
	
	public List<EventToEventItemTypeConfig> getEventItemTypeConfigs() { return eventItemTypeConfigs; }
	public EventTypeConfig setEventItemTypeConfigs(List<EventToEventItemTypeConfig> eventItemTypeConfigs) {
		this.eventItemTypeConfigs = eventItemTypeConfigs;
		return this;
	}
}
