package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.game.superpacman.actor.Gate;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;

public class Level1 extends SuperPacmanArea{
	static final DiscreteCoordinates PLAYER_SPAWN_POSITION = new DiscreteCoordinates(15 ,6);
	private Gate gate1;
	private Gate gate2;
	
	public static DiscreteCoordinates getSpawnPosition(){
		return PLAYER_SPAWN_POSITION;
	}
	
	public int[] getSpawnCoordinates() {
		int[] result = new int[2];
		result[0] = PLAYER_SPAWN_POSITION.x;
		result[1] = PLAYER_SPAWN_POSITION.y;
		return result;
	}
	
	public void registerObjects(Area area) {
		gate1 = new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(14,3), this.allCollected());
		gate2 = new Gate(this, Orientation.LEFT, new DiscreteCoordinates(15,3), this.allCollected());
		area.registerActor(gate1);
		area.registerActor(gate2);
	}
	
	public void update(float deltaTime) {
		super.update(deltaTime);
		if (this.allCollected().isOn()) {
			gate1.open();
			gate2.open();
		}
	}
	
	public String getTitle() {
		return "superpacman/Level1";
	}

	protected void createArea() {
		Door door = new Door("superpacman/Level2", Level2.PLAYER_SPAWN_POSITION, Logic.TRUE, this, Orientation.DOWN, new DiscreteCoordinates(14,0), new DiscreteCoordinates(15,0));
		registerActor(door);
		registerActor(new Background(this,null,"superpacman/background"));
	}

}
