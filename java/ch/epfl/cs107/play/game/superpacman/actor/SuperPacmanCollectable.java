package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.CollectableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class SuperPacmanCollectable extends CollectableAreaEntity {

	/**
	 * Default SuperPacmanCollectable constructor
	 * @param area        (Area): Owner area, not null
	 * @param orientation (Orientation): Initial orientation of the entity, not null
	 * @param position    (DiscreteCoordinate): Initial position of the entity, not null
	 */
	public SuperPacmanCollectable(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
	}
	
	/**
	 * unregisters the collected object
	 */
	public void collect() {
		getOwnerArea().unregisterActor(this);
	}
}
