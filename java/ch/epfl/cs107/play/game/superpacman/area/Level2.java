package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.game.superpacman.actor.Gate;
import ch.epfl.cs107.play.game.superpacman.actor.Key;
import ch.epfl.cs107.play.game.superpacman.area.text_areas.InstructionArea;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.And;
import ch.epfl.cs107.play.signal.logic.Logic;

public class Level2 extends SuperPacmanArea{
	
	final static DiscreteCoordinates PLAYER_SPAWN_POSITION = new DiscreteCoordinates (15,29);
	private Key[] keys = new Key[4];
	private Gate[] gates = new Gate[14];
	
	public void registerObjects(Area area) {
		keys[0] = new Key (this, Orientation.RIGHT, new DiscreteCoordinates(3,16));
		keys[1] = new Key (this, Orientation.RIGHT, new DiscreteCoordinates(26,16));
		keys[2] = new Key (this, Orientation.RIGHT, new DiscreteCoordinates(2,8));
		keys[3] = new Key (this, Orientation.RIGHT, new DiscreteCoordinates(27,8));
		gates[0] = new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(8,14), keys[0]);
		gates[1] = new Gate(this, Orientation.DOWN, new DiscreteCoordinates(5,12), keys[0]);
		gates[2] = new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(8,10), keys[0]);
		gates[3] = new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(8,8), keys[0]);
		gates[4] = new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(21,14), keys[1]);
		gates[5] = new Gate(this, Orientation.DOWN, new DiscreteCoordinates(24,12), keys[1]);
		gates[6] = new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(21,10), keys[1]);
		gates[7] = new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(21,8), keys[1]);
		gates[8] = new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(10,2), new And(keys[2],keys[3]));
		gates[9] = new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(19,2), new And(keys[2],keys[3]));
		gates[10] = new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(12,8), new And(keys[2],keys[3]));
		gates[11] = new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(17,8), new And(keys[2],keys[3]));
		gates[12] = new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(14,3), this.allCollected());
		gates[13] = new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(15,3), this.allCollected());
		for (int i=0; i<keys.length; i++) {
			registerActor(keys[i]);
		}
		for (int i=0; i<gates.length; i++) {
			registerActor(gates[i]);
		}
	}

	public void update(float deltaTime) {
		super.update(deltaTime);
		if (keys[0].isOn()) {
			for (int i=0; i<=3; i++) {
				gates[i].open();
			}
		}
		if (keys[1].isOn()) {
			for (int i=4; i<=7; i++) {
				gates[i].open();
			}
		}
		if (keys[2].isOn() && keys[3].isOn()) {
			for (int i=8; i<=11; i++) {
				gates[i].open();
			}
		}
		if (this.allCollected().isOn()) {
			for (int i=12; i<=13; i++) {
				gates[i].open();
			}
		}
	}
	
	public static DiscreteCoordinates getSpawnPosition(){
		return PLAYER_SPAWN_POSITION;
	}
	
	protected void createArea() {
		registerActor(new Background(this,null,"superpacman/background"));
		Door door = new Door("superpacman/InstructionArea", InstructionArea.getSpawnPosition(), Logic.TRUE, this, Orientation.UP, new DiscreteCoordinates(15,0), new DiscreteCoordinates(14,0));
		registerActor(door);
	}
	
	public String getTitle() {
		return "superpacman/Level2";
	}
	public int[] getSpawnCoordinates() {
		int[] result = new int[2];
		result[0]=PLAYER_SPAWN_POSITION.x;
		result[1]=PLAYER_SPAWN_POSITION.y;
		return result;
	}
}