package ch.epfl.cs107.play.game.superpacman.area.text_areas;


import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.game.superpacman.area.Level0;
import ch.epfl.cs107.play.game.superpacman.area.Level3;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;

public class GameLaunch extends TextAreas {
	
	public static Logic signal = Logic.FALSE;
	
	public void update(float deltaTime) {
		super.update(deltaTime);
		door = new Door("superpacman/Level0", Level0.PLAYER_SPAWN_POSITION, signal, this, Orientation.UP, new DiscreteCoordinates(0,0));
		registerActor(door);
	}
	
	public String getTitle() {
		return "superpacman/GameLaunch";
	}

	protected void createArea() {
	}
}
