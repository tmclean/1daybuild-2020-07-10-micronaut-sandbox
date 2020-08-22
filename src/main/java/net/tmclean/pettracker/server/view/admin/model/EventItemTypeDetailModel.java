package net.tmclean.pettracker.server.view.admin.model;

import net.tmclean.pettracker.db.model.event.EventItemType;
import net.tmclean.pettracker.db.model.event.EventUnit;

public class EventItemTypeDetailModel {
	
	public enum Mode {
		create,
		readOnly,
		edit;
	}
	
	private Mode mode;
	private EventItemType itemType;
	private EventUnit[] units = EventUnit.values();

	public Mode getMode() { return mode; }
	public EventItemTypeDetailModel setMode(Mode mode) {
		this.mode = mode;
		return this;
	}
	
	public EventItemType getItemType() { return itemType; }
	public EventItemTypeDetailModel setItemType(EventItemType itemType) {
		this.itemType = itemType;
		return this;
	}
	
	public EventUnit[] getUnits() { return this.units; }
}
