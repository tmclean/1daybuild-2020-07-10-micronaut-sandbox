package net.tmclean.pettracker.db.model.event.config;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import net.tmclean.pettracker.db.model.event.EventItemType;

@Entity
@Table( name="event_type_config_to_event_item_type" )
public class EventToEventItemTypeConfig {

	@Id
	@GeneratedValue( generator="uuid" )
	@GenericGenerator( name="uuid", strategy="uuid2" )
	private String id;

	@ManyToOne( fetch = FetchType.EAGER )
	@JoinColumn( name="event_type_config_id" )
	private EventTypeConfig eventTypeConfig;
	
	@ManyToOne( fetch = FetchType.EAGER )
	@JoinColumn( name="event_item_type_id" )
	private EventItemType eventItemType;
	
	@Column( name="color" )
	private String color;

	public String getId() { return id; }
	public EventToEventItemTypeConfig setId(String id) {
		this.id = id;
		return this;
	}

	public EventTypeConfig getEventTypeConfig() { return eventTypeConfig; }
	public EventToEventItemTypeConfig setEventTypeConfig(EventTypeConfig eventTypeConfig) {
		this.eventTypeConfig = eventTypeConfig;
		return this;
	}
	
	public EventItemType getEventItemType() { return eventItemType; }
	public EventToEventItemTypeConfig setEventItemType(EventItemType eventItemType) {
		this.eventItemType = eventItemType;
		return this;
	}

	public String getColor() { return color; }
	public EventToEventItemTypeConfig setColor(String color) {
		this.color = color;
		return this;
	}
}
