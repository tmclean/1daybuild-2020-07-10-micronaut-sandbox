package net.tmclean.pettracker;

import io.micronaut.runtime.Micronaut;
import net.tmclean.pettracker.db.DBInitializer;

public class Application {
    public static void main( String[] args ) {
        Micronaut
        	.build( args )
        	.eagerInitSingletons( true )
        	.mainClass( Application.class )
        	.start();
    }
    
    public Application( final DBInitializer dbInitializer ) {}
}
