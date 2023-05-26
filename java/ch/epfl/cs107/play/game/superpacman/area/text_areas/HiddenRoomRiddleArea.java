package ch.epfl.cs107.play.game.superpacman.area.text_areas;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.game.superpacman.actor.Weapon;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;

public class HiddenRoomRiddleArea extends TextAreas{

	private DiscreteCoordinates respawn;
	private DiscreteCoordinates weaponCoordinates;

	/**
	 * Default HiddenRoomRiddleArea constructor
	 * @param weapon (Weapon): weapon used as a mark for the door leading to this area
	 */
	public HiddenRoomRiddleArea(Weapon weapon) {
		this.weaponCoordinates = weapon.getCoordinates();
		respawn = new DiscreteCoordinates(weaponCoordinates.x, weaponCoordinates.y-1);
	}

	public String getTitle() {
		return "superpacman/HiddenRoomRiddleArea";
	}
	
	/**
	 * returns a new weapon object
	 * @return (Weapon): sword
	 */
	public Weapon getSword() {
		return new Weapon(this, Orientation.DOWN, new DiscreteCoordinates(0,0), "superpacman/sword", 30);
	}
	
	/**
	 * returns a new weapon object
	 * @return (Weapon): axe
	 */
	public Weapon getAxe() {
		return new Weapon(this, Orientation.DOWN, new DiscreteCoordinates(0,0), "superpacman/Axe", 25);
	}
	
	/**
	 * returns a new weapon object
	 * @return (Weapon): hammer
	 */
	public Weapon getHammer() {
		return new Weapon(this, Orientation.DOWN, new DiscreteCoordinates(0,0), "superpacman/Hammer", 20);
	}

	protected void createArea() {
		door = new Door("superpacman/HiddenRoom", new DiscreteCoordinates(respawn.x, respawn.y), Logic.TRUE, this, Orientation.UP, new DiscreteCoordinates(1,0));
		registerActor(door);
		
	}	
}