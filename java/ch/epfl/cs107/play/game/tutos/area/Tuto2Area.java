package ch.epfl.cs107.play.game.tutos.area;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.tutos.Tuto2Behaviour;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;

abstract public class Tuto2Area extends Area{
	private Window window;
	
	/**
     * Create the area by adding all its actors
     * called by the begin method, when the area starts to play
     */
	protected abstract void createArea();
	
	public boolean begin(Window window, FileSystem fileSystem) {
		setBehavior (new Tuto2Behaviour (window , getTitle ()));
	 	this.window = window;
	       if (super.begin(window, fileSystem)) {
	    	   // Set the behavior map
	           createArea();
	           return true;
	       }
	       return false;
	   }
	 
	public float getCameraScaleFactor() {
		return 10.f;
	}
}
