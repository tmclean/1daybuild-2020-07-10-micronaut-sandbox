package net.tmclean.pettracker.db.model.event;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.tmclean.pettracker.db.model.pet.Pet;

@Entity
@Table( name="event" )
public class Event {

	@Id
	@GeneratedValue( generator="uuid" )
	@GenericGenerator( name="uuid", strategy="uuid2" )
	private String id;

	@JsonIgnore
	@ManyToOne( optional=false )
	private Pet pet;
	
	@ManyToOne( optional=false )
	private EventType eventType;
	
	@NotNull
	@Column( name="event_time" )
	private Timestamp eventTime;

	@Lob
	@Column( name="notes" )
	private String notes;
	
	@OneToMany( fetch = FetchType.EAGER )
	private List<EventItem> items = new ArrayList<>();

	public String getId() { return id; }
	public Event setId(String id) { 
		this.id = id; 
		return this;
	}

	public Pet getPet() { return pet; }
	public Event setPet(Pet pet) { 
		this.pet = pet; 
		return this;
	}
	
	public EventType getEventType() { return eventType; }
	public Event setEventType(EventType eventType) { 
		this.eventType = eventType; 
		return this;
	}

	public Timestamp getEventTime() { return eventTime; }
	public Event setEventTime(Timestamp eventTime) {
		this.eventTime = eventTime;
		return this;
	}
	
	public List<EventItem> getItems() { return items; }
	public Event setItems(List<EventItem> items) { 
		this.items = items; 
		return this;
	}
	
	public String getNotes() { return notes; }
	public Event setNotes(String notes) { 
		this.notes = notes; 
		return this;
	}
}
