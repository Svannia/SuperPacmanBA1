package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.game.superpacman.actor.Gate;
import ch.epfl.cs107.play.game.superpacman.actor.Key;
import ch.epfl.cs107.play.game.superpacman.area.text_areas.GameLaunch;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;

public class Level0 extends SuperPacmanArea{
	public static final DiscreteCoordinates PLAYER_SPAWN_POSITION = new DiscreteCoordinates(10 ,1);
	public Key key;
	private Gate gate1;
	private Gate gate2;
	private Door door;
	
	public void registerObjects(Area area) {
		key = new Key (this, Orientation.RIGHT, new DiscreteCoordinates(3,4));
		gate1 = new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(5,8), key);
		gate2 = new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(6,8), key);
		registerActor(key);
		registerActor(gate1);
		registerActor(gate2);
	}
	
	public static  DiscreteCoordinates getSpawnPosition(){
		return PLAYER_SPAWN_POSITION;
	}
	
	public int[] getSpawnCoordinates() {
		int[] result = new int[2];
		result[0] = PLAYER_SPAWN_POSITION.x;
		result[1] = PLAYER_SPAWN_POSITION.y;
		return result;
	}
	
	public String getTitle() {
		return "superpacman/Level0";
	}
	
	public void update(float deltaTime) {
		super.update(deltaTime);
		if (key.isOn()) {
			gate1.open();
			gate2.open();
		}
		
	}
	
	protected void createArea() {
		GameLaunch.signal = Logic.FALSE;
		door = new Door("superpacman/Level1", Level1.PLAYER_SPAWN_POSITION, Logic.TRUE, this, Orientation.UP, new DiscreteCoordinates(5,9), new DiscreteCoordinates(6,9));
		registerActor(door);
		registerActor(new Background(this,null,"superpacman/background"));
	}
	
	

}
