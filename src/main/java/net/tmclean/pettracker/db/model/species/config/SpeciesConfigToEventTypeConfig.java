package net.tmclean.pettracker.db.model.species.config;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import net.tmclean.pettracker.db.model.event.config.EventTypeConfig;

@Entity
@Table( name="species_config_to_event_type_config" )
public class SpeciesConfigToEventTypeConfig {

	@Id
	@GeneratedValue( generator="uuid" )
	@GenericGenerator( name="uuid", strategy="uuid2" )
	private String id;
	
	@ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	@JoinColumn( name="species_config_id" )
	private SpeciesConfig speciesConfig;

	@ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	@JoinColumn( name="event_type_config_id" )
	private EventTypeConfig eventTypeConfig;
	
	public String getId() { return id; }
	public SpeciesConfigToEventTypeConfig setId(String id) {
		this.id = id;
		return this;
	}
	
	public SpeciesConfig getSpeciesConfig() { return speciesConfig; }
	public SpeciesConfigToEventTypeConfig setSpeciesConfig(SpeciesConfig speciesConfig) {
		this.speciesConfig = speciesConfig;
		return this;
	}
	
	public EventTypeConfig getEventTypeConfig() { return eventTypeConfig; }
	public SpeciesConfigToEventTypeConfig setEventTypeConfig(EventTypeConfig eventTypeConfig) {
		this.eventTypeConfig = eventTypeConfig;
		return this;
	}
}
