package net.tmclean.pettracker.db.model.species.config;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import net.tmclean.pettracker.db.model.species.Species;

@Entity
@Table( name="species_config" )
public class SpeciesConfig {

	@Id
	@GeneratedValue( generator="uuid" )
	@GenericGenerator( name="uuid", strategy="uuid2" )
	private String id;

	@ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	private Species species;
	
	@OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "speciesConfig" )
	private List<SpeciesConfigToEventTypeConfig> eventTypeConfigs = new ArrayList<>();

	@OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "speciesConfig" )
	private List<SpeciesConfigToEventItemType> eventItemTypes = new ArrayList<>();
	
	public String getId() { return id; }
	public SpeciesConfig setId(String id) {
		this.id = id;
		return this;
	}
	
	public Species getSpecies() { return species; }
	public SpeciesConfig setSpecies(Species species) { 
		this.species = species; 
		return this;
	}
	
	public List<SpeciesConfigToEventTypeConfig> getEventTypeConfigs() { return eventTypeConfigs; }
	public SpeciesConfig setEventTypeConfigs(List<SpeciesConfigToEventTypeConfig> eventTypeConfigs) {
		this.eventTypeConfigs = eventTypeConfigs;
		return this;
	}
	public List<SpeciesConfigToEventItemType> getEventItemTypes() {	return eventItemTypes; }
	public SpeciesConfig setEventItemTypes(List<SpeciesConfigToEventItemType> eventItemTypes) {
		this.eventItemTypes = eventItemTypes;
		return this;
	}
}
