package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.game.superpacman.actor.Doggo;
import ch.epfl.cs107.play.game.superpacman.actor.Weapon;
import ch.epfl.cs107.play.game.superpacman.area.text_areas.HiddenRoomRiddleArea;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;

public class HiddenRoom extends SuperPacmanArea{
	
	static final DiscreteCoordinates PLAYER_SPAWN_POSITION = new DiscreteCoordinates(3,1);
	public static Weapon weapon;
	private Door riddleDoor;
	
	/**
	 * Default HiddenRoom constructor
	 * creates the weapon necessary to the construction of the HiddenRoomRiddleArea on this level
	 */
	public HiddenRoom() {
		weapon = new Weapon (this, Orientation.DOWN, new DiscreteCoordinates(3,3));	
	}
	
	public void update(float deltaTime) {
		super.update(deltaTime);
		if (weapon.activated) {
			leaveAreaCells(riddleDoor, riddleDoor.getCurrentCells());
			unregisterActor(riddleDoor);
			riddleDoor = new Door("superpacman/HiddenRoomRiddleArea", HiddenRoomRiddleArea.getSpawnPosition(), Logic.FALSE, this, Orientation.DOWN, new DiscreteCoordinates(3,3));
			registerActor(riddleDoor);
		}
	}
	
	/**
	 * getter for the area's spawn position
	 * @return (DiscreteCoordinates)
	 */
	public static DiscreteCoordinates getSpawnPosition(){
		return PLAYER_SPAWN_POSITION;
	}

	public String getTitle() {
		return "superpacman/HiddenRoom";
	}

	protected void createArea() {
		registerActor(new Background(this,null,"superpacman/background"));
		Door door = new Door("superpacman/Level3", new DiscreteCoordinates(19,27), Logic.TRUE, this, Orientation.DOWN, new DiscreteCoordinates(3,0));
		registerActor(door);
		registerActor(weapon);
		
	}

	public void registerObjects(Area area) {
		riddleDoor = new Door("superpacman/HiddenRoomRiddleArea", HiddenRoomRiddleArea.getSpawnPosition(), Logic.TRUE, this, Orientation.DOWN, new DiscreteCoordinates(3,3));
		registerActor(riddleDoor);
		registerActor(new Doggo(this, Orientation.UP, new DiscreteCoordinates(1, 5), "superpacman/SternAhu", "Ahu"));
		registerActor(new Doggo(this, Orientation.UP, new DiscreteCoordinates(5, 5), "superpacman/SternCinq", "Cinq"));
	}

	@Override
	public int[] getSpawnCoordinates() {
		int[] result = new int[2];
		result[0] = PLAYER_SPAWN_POSITION.x;
		result[1] = PLAYER_SPAWN_POSITION.y;
		return result;
	}

}