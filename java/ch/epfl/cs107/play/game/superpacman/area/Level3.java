package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.game.superpacman.SuperPacman;
import ch.epfl.cs107.play.game.superpacman.actor.Gate;
import ch.epfl.cs107.play.game.superpacman.actor.RiddleKey;
import ch.epfl.cs107.play.game.superpacman.actor.Weapon;
import ch.epfl.cs107.play.game.superpacman.area.text_areas.RiddleArea;
import ch.epfl.cs107.play.game.superpacman.area.text_areas.TransitionRiddle;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.And;
import ch.epfl.cs107.play.signal.logic.Logic;

public class Level3 extends SuperPacmanArea {
	public static final DiscreteCoordinates PLAYER_SPAWN_POSITION = new DiscreteCoordinates(0,1);
	private int HiddenRoomAttempts =0;
	public static final int RIDDLES_NUMBER = 6;
	public static RiddleKey[] keys = new RiddleKey[RIDDLES_NUMBER];
	private Gate[] gates = new Gate[20];
	private Door[] doors = new Door[RIDDLES_NUMBER];
	private Door hiddenDoor;
	private Logic weaponSignal = Logic.FALSE;
	protected static DiscreteCoordinates[] keyPositions = {new DiscreteCoordinates(5,24), new DiscreteCoordinates(9,5), new DiscreteCoordinates(11,10), new DiscreteCoordinates(11,18), new DiscreteCoordinates(24,20), new DiscreteCoordinates(19,18)};

	/**
	 * Default Level3 constructor
	 * creates all the RiddleKeys necessary at the creation of riddle areas on this level
	 */
	public Level3() {
		for (int i=0; i<RIDDLES_NUMBER; i++) {
			keys[i] = new RiddleKey (this, Orientation.RIGHT, keyPositions[i], i);
		}
	}
	
	/**
	 * setter for HiddenRoomAttempts
	 * @param attempt (int): number of attempts from the player
	 */
	public void setHiddenRoomAttempts(int attempt) {
		HiddenRoomAttempts = attempt;
	}
	
	/**
	 * getter for HiddenRoomAttempts
	 * @return (int)
	 */
	public int getHiddenRoomAttempts() {
		return HiddenRoomAttempts;
	}
	
	/**
	 * turns on the logic signal for the gate activated through getting a weapon
	 */
	public void weaponSignalOn() {
		weaponSignal = Logic.TRUE;
	}
	
	public String getTitle() {
		return "superpacman/Level3";
	}
	
	protected void createArea() {
		registerActor(new Background(this,null,"superpacman/background"));
		registerActor(new Door("superpacman/BossRoom", BossRoom.PLAYER_SPAWN_POSITION, Logic.TRUE, this, Orientation.DOWN, new DiscreteCoordinates(28,29), new DiscreteCoordinates(27,29)));
		registerActor(new Weapon(this, Orientation.DOWN, new DiscreteCoordinates(26,24), "superpacman/dagger", 10));
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
	
	public void update(float deltaTime) {

		super.update(deltaTime);
		
		//TODO uncomment to free passage to finish level without having to play through it entirely
		//gates[16].open();
		//gates[18].open();
			
		if (HiddenRoomAttempts>=2) {
			leaveAreaCells(hiddenDoor, hiddenDoor.getCurrentCells());
			unregisterActor(hiddenDoor);
			hiddenDoor = new Door("superpacman/TransitionRiddle", TransitionRiddle.getSpawnPosition(), Logic.FALSE, this, Orientation.DOWN, new DiscreteCoordinates(19,28));
			registerActor(hiddenDoor);
		}
		
		if (TransitionRiddle.activated) {
			leaveAreaCells(hiddenDoor, hiddenDoor.getCurrentCells());
			unregisterActor(hiddenDoor);
			hiddenDoor = new Door("superpacman/HiddenRoom", HiddenRoom.getSpawnPosition(), Logic.TRUE, this, Orientation.DOWN, new DiscreteCoordinates(19,28));
			registerActor(hiddenDoor);
		}
		
		for (int i=0; i<RIDDLES_NUMBER; i++) {
			if (keys[i].rightAnswer) {
				leaveAreaCells(doors[i], doors[i].getCurrentCells());
				unregisterActor(doors[i]);
				doors[i] = new Door("superpacman/RiddleArea", RiddleArea.getSpawnPosition(), Logic.FALSE, this, Orientation.UP, new DiscreteCoordinates((int)keys[i].getPosition().x, (int)keys[i].getPosition().y));
				registerActor(doors[i]);
			}
		}
		if (keys[0].isOn()) {
			for (int i=0; i<=1; i++) {
				gates[i].open();
			}
		}
		if (keys[1].isOn()) {
			for (int i=2; i<=4; i++) {
				gates[i].open();
			}
		}
		if (keys[2].isOn()) gates[5].open();
		
		if (keys[1].isOn() && keys[4].isOn()) {
			for (int i=6; i<=7; i++) {
				gates[i].open();
			}
		}
		if (keys[3].isOn()) {
			for (int i=8; i<=11; i++) {
				gates[i].open();
			}
		}
		
		if (keys[4].isOn()) gates[12].open();

		if (keys[5].isOn()) {
			for (int i=13; i<=15; i++) {
				gates[i].open();
			}
		}
		if (this.allCollected().isOn()) {
			for (int i=16; i<=17; i++) {
				gates[i].open();
			}
		}
		if (weaponSignal.isOn()) {
			for (int i=18; i<=19; i++) {
				gates[i].open();
			}
		}
		
	}
	
	public void registerObjects(Area area) {
		for (int i=0; i<RIDDLES_NUMBER; i++) {
			doors[i] = new Door(SuperPacman.riddleAreas[i].getTitle(), RiddleArea.getSpawnPosition(), Logic.TRUE, this, Orientation.UP, new DiscreteCoordinates((int)keys[i].getPosition().x, (int)keys[i].getPosition().y));
		}
		gates[0] = new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(1,24), keys[0]);
		gates[1] = new Gate(this, Orientation.DOWN, new DiscreteCoordinates(5,28), keys[0]);
		gates[2] = new Gate(this, Orientation.DOWN, new DiscreteCoordinates(12,5), keys[1]);
		gates[3] = new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(14,3), keys[1]);
		gates[4] = new Gate(this, Orientation.DOWN, new DiscreteCoordinates(16,5), keys[1]);
		gates[5] = new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(14,7), keys[2]);
		gates[6] = new Gate(this, Orientation.DOWN, new DiscreteCoordinates(9,14), new And(keys[1],keys[4]));
		gates[7] = new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(15,20), new And(keys[1],keys[4]));
		gates[8] = new Gate(this, Orientation.DOWN, new DiscreteCoordinates(2,22), keys[3]);
		gates[9] = new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(5,20), keys[3]);
		gates[10] = new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(7,27), keys[3]);
		gates[11] = new Gate(this, Orientation.DOWN, new DiscreteCoordinates(9,24), keys[3]);
		gates[12] = new Gate(this, Orientation.DOWN, new DiscreteCoordinates(22,15), keys[4]);
		gates[13] = new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(24,17), keys[5]);
		gates[14] = new Gate(this, Orientation.DOWN, new DiscreteCoordinates(26,15), keys[5]);
		gates[15] = new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(24,13), keys[5]);
		gates[16] = new Gate(this, Orientation.DOWN, new DiscreteCoordinates(22,24), this.allCollected());
		gates[17] = new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(27,22), this.allCollected());
		gates[18] = new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(27,27), weaponSignal);
		gates[19] = new Gate(this, Orientation.RIGHT, new DiscreteCoordinates(28,27), weaponSignal);
		for (int i=0; i<RIDDLES_NUMBER; i++) {
			registerActor(doors[i]);
			registerActor(keys[i]);
		}
		for (int i=0; i<gates.length; i++) {
			registerActor(gates[i]);
		}
		hiddenDoor = new Door("superpacman/TransitionRiddle", TransitionRiddle.getSpawnPosition(), Logic.TRUE, this, Orientation.DOWN, new DiscreteCoordinates(19,28));
		registerActor(hiddenDoor);
	}

}
