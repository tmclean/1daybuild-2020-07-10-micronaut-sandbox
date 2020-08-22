package net.tmclean.pettracker.db.model.event;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table( name="event_item" )
public class EventItem {

	@Id
	@GeneratedValue( generator="uuid" )
	@GenericGenerator( name="uuid", strategy="uuid2" )
	private String id;

	@ManyToOne
	private EventItemType itemType;
	
	@Column( name="value" )
	private double value;
	
	@Lob
	@Column( name="notes" )
	private String notes;
	
	public String getId() { return id; }
	public EventItem setId(String id) { 
		this.id = id;
		return this;
	}
	
	public EventItemType getItemType() { return itemType; }
	public EventItem setItemType(EventItemType itemType) {
		this.itemType = itemType;
		return this;
	}
	
	public double getValue() { return value; }
	public EventItem setValue(double value) { 
		this.value = value; 
		return this;
	}
	
	public String getNotes() { return notes; }
	public EventItem setNotes(String notes) { 
		this.notes = notes; 
		return this;
	}
}
