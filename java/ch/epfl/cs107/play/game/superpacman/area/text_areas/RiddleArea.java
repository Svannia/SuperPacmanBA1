package ch.epfl.cs107.play.game.superpacman.area.text_areas;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.game.superpacman.actor.RiddleKey;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;

public class RiddleArea extends TextAreas {
	
	private DiscreteCoordinates respawn;
	private int rightEntry;
	private RiddleKey key;
	private String title;
	public final int scoreMinus = 30;
	
	/**
	 * Default RiddleArea constructor
	 * @param i (int): indice that determines at which location the player will respawn when it exists the riddleArea. Can only be 1 to 4.
	 * 1 : right of the cell
	 * 2 : above the cell
	 * 3 : left of the cell
	 * 4 : under the cell
	 * @param rightEntry (int): number associated to the keyboard's entry that gives the right answer
	 * @param title (String): resource path of the area behavior map image
	 */
	public RiddleArea(int i, int rightEntry, RiddleKey key, String title) {
		this.title=title;
		this.rightEntry = rightEntry;
		this.key = key;
		if (i>=1 && i<=4) {
			if (i==1) {
				int x = this.key.getCoordinates().x+1;
				respawn = new DiscreteCoordinates(x,this.key.getCoordinates().y);
			} if (i==2) {
				int y = this.key.getCoordinates().y+1;
				respawn = new DiscreteCoordinates(this.key.getCoordinates().x, y);
			} if (i==3) {
				int x = this.key.getCoordinates().x-1;
				respawn = new DiscreteCoordinates(x,this.key.getCoordinates().y);
			} if (i==4) {
				int y = this.key.getCoordinates().y-1;
				respawn = new DiscreteCoordinates(this.key.getCoordinates().x, y);
			}
		} else System.out.println("This integer for spawnLocation is not valid.");
	}
	
	public String getTitle() {
		return title;
	}
	
	/**
	 * getter for the key associated to a riddle
	 * @return (RiddleKey): associated RiddleKey
	 */
	public RiddleKey getKey() {
		return key;
	}
	
	/**
	 * getter for the riddle's right answer
	 * @return (int): number associated to keyboard's entry
	 */
	public int rightEntry() {
		return this.rightEntry;
	}
	
	protected void createArea() {
		door = new Door("superpacman/Level3", new DiscreteCoordinates(this.respawn.x, this.respawn.y), Logic.TRUE, this, Orientation.UP, new DiscreteCoordinates(1,0));
		registerActor(door);
	}
}
