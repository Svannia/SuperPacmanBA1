package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.superpacman.actor.Bonus;
import ch.epfl.cs107.play.game.superpacman.actor.Boss;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class BossRoom extends SuperPacmanArea {
	final static DiscreteCoordinates PLAYER_SPAWN_POSITION = new DiscreteCoordinates (12,3);

	public String getTitle() {
		return "superpacman/BossRoom";
	}

	protected void createArea() {
		registerActor(new Background(this,null,"superpacman/bossBackground"));
	}
	
	/**
	 * getter for the area's spawn position
	 * @return (DiscreteCoordinates)
	 */
	public static DiscreteCoordinates getSpawnPosition(){
		return PLAYER_SPAWN_POSITION;
	}

	public int[] getSpawnCoordinates() {
		int[] result = new int[2];
		result[0]=PLAYER_SPAWN_POSITION.x;
		result[1]=PLAYER_SPAWN_POSITION.y;
		return result;	
	}

	public void registerObjects(Area area) {
	}

}
