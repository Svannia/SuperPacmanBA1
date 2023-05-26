package ch.epfl.cs107.play.game.superpacman.area.text_areas;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public abstract class TextAreas extends SuperPacmanArea {
	
	protected final static DiscreteCoordinates PLAYER_SPAWN_POSITION = new DiscreteCoordinates(0,0);
	protected Door door;
	
	public int[] getSpawnCoordinates() {
		int[] result = new int[2];
		result[0] = PLAYER_SPAWN_POSITION.x;
		result[1] = PLAYER_SPAWN_POSITION.y;
		return result;
	}
	
	/**
	 * getter for the spawn position
	 * @return (DiscreteCoordinates): coordinates of the position
	 */
	public static  DiscreteCoordinates getSpawnPosition(){
		return PLAYER_SPAWN_POSITION;
	}
	
	public void registerObjects(Area area) {	
	}


}
