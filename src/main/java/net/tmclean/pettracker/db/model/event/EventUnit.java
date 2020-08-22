package net.tmclean.pettracker.db.model.event;

public enum EventUnit {
	
	none( "" ),
	gram( "g" ),
	mm( "mm" );
	
	private final String label;
	
	private EventUnit( final String label ) {
		this.label = label;
	}
	
	public String getLabel() {
		return this.label;
	}
}
