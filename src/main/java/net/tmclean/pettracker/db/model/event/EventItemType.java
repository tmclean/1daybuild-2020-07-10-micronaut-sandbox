package net.tmclean.pettracker.db.model.event;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table( name="event_item_type" )
public class EventItemType {

	@Id
	@GeneratedValue( generator="uuid" )
	@GenericGenerator( name="uuid", strategy="uuid2" )
	private String id;
	
	@Column( name="name" )
	private String name;
	
	@Column( name="unit" )
	private EventUnit unit;;
	
	public String getId() { return id; }
	public EventItemType setId(String id) {
		this.id = id;
		return this;
	}
	
	public String getName() { return name; }
	public EventItemType setName(String name) { 
		this.name = name;
		return this;
	}
	
	public EventUnit getUnit() { return unit; }
	public EventItemType setUnit(EventUnit unit) { 
		this.unit = unit;
		return this;
	}
}
