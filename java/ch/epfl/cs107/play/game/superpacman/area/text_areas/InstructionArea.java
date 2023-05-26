package ch.epfl.cs107.play.game.superpacman.area.text_areas;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.game.superpacman.area.Level3;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;

public class InstructionArea extends TextAreas {

	public String getTitle() {
		return "superpacman/InstructionArea";
	}

	protected void createArea() {
		door = new Door("superpacman/Level3", Level3.getSpawnPosition(), Logic.TRUE, this, Orientation.DOWN, new DiscreteCoordinates(1,0));
		registerActor(door);
	}

}
