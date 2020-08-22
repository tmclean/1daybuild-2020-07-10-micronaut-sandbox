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

import net.tmclean.pettracker.db.model.event.EventItemType;

@Entity
@Table( name="species_config_to_event_item_type" )
public class SpeciesConfigToEventItemType {

	@Id
	@GeneratedValue( generator="uuid" )
	@GenericGenerator( name="uuid", strategy="uuid2" )
	private String id;
	
	@ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	@JoinColumn( name="species_config_id" )
	private SpeciesConfig speciesConfig;

	@ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	@JoinColumn( name="event_item_type_id" )
	private EventItemType eventItemType;
	
	public String getId() { return id; }
	public SpeciesConfigToEventItemType setId(String id) {
		this.id = id;
		return this;
	}
	
	public SpeciesConfig getSpeciesConfig() { return speciesConfig; }
	public SpeciesConfigToEventItemType setSpeciesConfig(SpeciesConfig speciesConfig) {
		this.speciesConfig = speciesConfig;
		return this;
	}
	
	public EventItemType getEventItemType() { return eventItemType; }
	public SpeciesConfigToEventItemType setEventItemType(EventItemType eventItemType) {
		this.eventItemType = eventItemType;
		return this;
	}
}
