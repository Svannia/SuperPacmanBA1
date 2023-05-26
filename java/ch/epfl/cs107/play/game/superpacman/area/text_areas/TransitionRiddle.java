package ch.epfl.cs107.play.game.superpacman.area.text_areas;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.game.superpacman.area.HiddenRoom;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;

public class TransitionRiddle extends TextAreas {
	
	public static boolean activated = false;	
	private Door wrongDoor;
	private static int rightEntry = 66;
	
	/**
	 * getter for the riddle's right answer
	 * @return (int): number associated to the keyboard's entry
	 */
	public static int getRightEntry() {
		return rightEntry;
	}
	
	public String getTitle() {
		return "superpacman/TransitionRiddle";
	}

	protected void createArea() {
		wrongDoor = new Door("superpacman/Level3", new DiscreteCoordinates(19, 27), Logic.TRUE, this, Orientation.DOWN, new DiscreteCoordinates(1,0));
		registerActor(wrongDoor);
		door = new Door("superpacman/HiddenRoom", HiddenRoom.getSpawnPosition(), Logic.TRUE, this, Orientation.DOWN, new DiscreteCoordinates(0,1));
		registerActor(door);
	}
}
