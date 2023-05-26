package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class RiddleKey extends Key {
	private DiscreteCoordinates position;
	private int number;
	public boolean rightAnswer = false;

	/**
	 * Default RiddleKey constructor
	 * @param area        (Area): Owner area, not null
	 * @param orientation (Orientation): Initial orientation of the entity, not null
	 * @param position    (DiscreteCoordinate): Initial position of the entity, not null
	 * @param number (int): number to differentiate the riddle keys, will be used to display the right riddle
	 */
	public RiddleKey(Area area, Orientation orientation, DiscreteCoordinates position, int number) {
		super(area, orientation, position);	
		this.position = position;
		this.number = number;
	}
	
	/**
	 * returns the key number that can be associated with a riddle in a text area
	 * @return (int): number of the key
	 */
	public int getNumber() {
		return number;
	}
	
	/**
	 * getter for the key's position
	 * @return (DiscreteCoordinates): key's location
	 */
	public DiscreteCoordinates getCoordinates() {
		return position;
	}
}
