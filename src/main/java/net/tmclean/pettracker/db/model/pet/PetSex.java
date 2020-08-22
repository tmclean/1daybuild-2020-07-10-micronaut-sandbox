package net.tmclean.pettracker.db.model.pet;

public enum PetSex {
	male(    "Male",    "M" ),
	female(  "Female",  "F" ),
	unknown( "Unknown", "?" );
	
	private final String label;
	private final String shortLabel;
	
	private PetSex( final String label, final String shortLabel ) {
		this.label = label;
		this.shortLabel = shortLabel;
	}
	
	public String getLabel() { return label; }
	public String getShortLabel() { return shortLabel; }
}
