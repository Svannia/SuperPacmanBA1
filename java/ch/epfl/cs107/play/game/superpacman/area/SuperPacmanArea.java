package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaGraph;
import ch.epfl.cs107.play.game.superpacman.actor.Boss;
import ch.epfl.cs107.play.game.superpacman.actor.Ghost;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Window;

public abstract class SuperPacmanArea extends Area{
	
	
	public final static float CAMERA_SCALE_FACTOR = 15.f;
	public SuperPacmanBehavior behavior;
	public int diamondsTaken=0;

    /**
     * Create the area by adding it all actors
     * called by begin method
     * Note it set the Behavior as needed !
     */
    protected abstract void createArea();

    /// EnigmeArea extends Area

    @Override
    public final float getCameraScaleFactor() {
        return CAMERA_SCALE_FACTOR;
    }
    
    /**
	 * getter for the spawn position x and y coordinates
	 * @return (int[]): table with coordinates
	 */
    public abstract int[] getSpawnCoordinates();
    
    /**
	 * checks the amount of diamonds collected
	 * @return (Logic): returns TRUE if all diamonds of a map equals the number of diamonds collected on the same map
	 */
    public Logic allCollected() {
    	if (diamondsTaken == behavior.totalDiamonds.size()) return Logic.TRUE;
    	else return Logic.FALSE;
    }


    /// Demo2Area implements Playable

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        if (super.begin(window, fileSystem)) {
            // Set the behavior map
        	behavior = new SuperPacmanBehavior(window, getTitle());
            setBehavior(behavior);
            createArea();
            behavior.registerActors(this);
            registerObjects(this);
            return true;
        }
        return false;
    }
    
    /**
	 * registration of all objects on the map that are not registered through the bahavior map
	 * @param area (Area): area where objects are registered 
	 */
    public abstract void registerObjects(Area area);
    
    /**
	 * getter for the total amount of diamonds in an area
	 * @return (int): number of diamonds
	 */
    public int getDiamondsTotal() {
    	return behavior.totalDiamonds.size();
    }
    
    /**
	 * makes all ghosts of an area afraid
	 */
    public void scareGhosts() {
    	for (Ghost i : behavior.ghosts) {
    		i.setIsAfraid(true);
    	}
    }
    
    /**
	 * cancels the afraid state of all ghosts in an area
	 */
    public void normalGhosts() {
    	for (Ghost i : behavior.ghosts) {
    		i.setIsAfraid(false);
    		if (i instanceof Boss) ((Boss)i).isVulnerable = false;
    	}
    }
    
    /**
	 * returns all ghosts of an area to their shelter position
	 */
    public void backToShelter() {
    	for (Ghost i : behavior.ghosts) {
    		i.isEaten();
    	}
    }
    
    /**
	 * make all ghosts of an area forget about the player
	 */
    public void ghostsForget() {
    	for (Ghost i :behavior.ghosts) {
    		i.setMemoire(null);
    	}
    }
    
    /**
	 * checks the state of the boss
	 * @return (boolean): true if an area's ghost is a boss and is vulnerable
	 */
    public boolean vulnerableBoss() {
    	boolean result = false;
    	for (Ghost i : behavior.ghosts) {
    		if (i instanceof Boss && ((Boss)i).isVulnerable) result = true;
    	}
    	return result;
    }
    
    /**
	 * getter for the area behavior's AreaGraph
	 * @return (AreaGraph): returns the AreaGraph of an area
	 */
    public AreaGraph getGraph() {
    	return behavior.getGraph();
    }
}
