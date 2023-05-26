package ch.epfl.cs107.play.game.superpacman;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.RPG;
import ch.epfl.cs107.play.game.superpacman.actor.AnimatedCollectable;
import ch.epfl.cs107.play.game.superpacman.actor.Ghost;
import ch.epfl.cs107.play.game.superpacman.actor.SuperPacmanPlayer;
import ch.epfl.cs107.play.game.superpacman.area.BossRoom;
import ch.epfl.cs107.play.game.superpacman.area.HiddenRoom;
import ch.epfl.cs107.play.game.superpacman.area.Level0;
import ch.epfl.cs107.play.game.superpacman.area.Level1;
import ch.epfl.cs107.play.game.superpacman.area.Level2;
import ch.epfl.cs107.play.game.superpacman.area.Level3;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.game.superpacman.area.text_areas.GameLaunch;
import ch.epfl.cs107.play.game.superpacman.area.text_areas.HiddenRoomRiddleArea;
import ch.epfl.cs107.play.game.superpacman.area.text_areas.InstructionArea;
import ch.epfl.cs107.play.game.superpacman.area.text_areas.RiddleArea;
import ch.epfl.cs107.play.game.superpacman.area.text_areas.TransitionRiddle;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

public class SuperPacman extends RPG{
	
	public boolean pause = false;
	private SuperPacmanPlayer player;
	private boolean end = false;
	public static int highestScore = 0;
	
	private final DiscreteCoordinates[] SPAWN_POSITION = {GameLaunch.getSpawnPosition(), Level0.getSpawnPosition(), Level1.getSpawnPosition(), Level2.getSpawnPosition(), Level3.getSpawnPosition(), HiddenRoom.getSpawnPosition(), BossRoom.getSpawnPosition()};
	public static RiddleArea[] riddleAreas = new RiddleArea[Level3.RIDDLES_NUMBER];
	
	public String getTitle() {
		return "Super Pac-Man";
	}
	
	/**
	 * creates all areas that are necessary to begin a new game
	 */
	private void createAreas() {
		addArea(new GameLaunch());
		addArea(new Level0());
		addArea(new Level1());
		addArea(new Level2());
		addArea(new Level3());
		riddleAreas[0] = new RiddleArea(1, 65, Level3.keys[0], "superpacman/RiddleArea0");
		riddleAreas[1] = new RiddleArea(3, 67, Level3.keys[1], "superpacman/RiddleArea1");
		riddleAreas[2] = new RiddleArea(4, 65, Level3.keys[2], "superpacman/RiddleArea2");
		riddleAreas[3] = new RiddleArea(1, 68, Level3.keys[3], "superpacman/RiddleArea3");
		riddleAreas[4] = new RiddleArea(2, 66, Level3.keys[4], "superpacman/RiddleArea4");
		riddleAreas[5] = new RiddleArea(1, 67, Level3.keys[5], "superpacman/RiddleArea5");
		for (int i=0; i<Level3.RIDDLES_NUMBER; i++) {
			addArea(riddleAreas[i]);

		}
		addArea(new BossRoom());
		addArea(new HiddenRoom());
		addArea(new HiddenRoomRiddleArea(HiddenRoom.weapon));
		addArea(new TransitionRiddle());
		addArea(new InstructionArea());
	}
	
	public void update(float deltaTime) {
		Keyboard keyboard = getCurrentArea().getKeyboard();
		
		if (keyboard.get(Keyboard.ENTER).isReleased()) {
			GameLaunch.signal = Logic.TRUE;
			player.start = true;			
		}
		
		if (!end) {
			super.update(deltaTime);
			if (keyboard.get(Keyboard.ESCAPE).isReleased()) {
				this.end();
				this.getWindow().dispose();
			}
			
			
			if (keyboard.get(Keyboard.SPACE).isReleased()) {
				pausingEveryone(false);
			}
			
			if (!pause)	{
				
				if (player.isInvincible || ((SuperPacmanArea)getCurrentArea()).vulnerableBoss()) {
					if (!player.isInvincible) {
						player.isInvincible = true;
					}
					((SuperPacmanArea) getCurrentArea()).scareGhosts();
				} else {
					((SuperPacmanArea) getCurrentArea()).normalGhosts();
				}
				
			}
			
			if (player.gameFinished()) {
				pausingEveryone(true);
				if (player.score>highestScore) highestScore = player.score;
				if (keyboard.get(Keyboard.TAB).isReleased()) {
					GameLaunch.signal = Logic.FALSE;
					pause = false;
					begin(this.getWindow(), this.getFileSystem());
				}

			}
			
			if (player.hp==0 || (!(getCurrentArea() instanceof RiddleArea) && player.score<0)) {
				this.player.Death();
				pausingEveryone(true);
				if (player.score>highestScore) highestScore = player.score;
				if (keyboard.get(Keyboard.TAB).isReleased()) {
					GameLaunch.signal = Logic.FALSE;
					pause = false;
					begin(this.getWindow(), this.getFileSystem());
				}
			}
		}
	}
	
	public boolean begin (Window window, FileSystem fileSystem) {
		if (super.begin( window , fileSystem )) {
			createAreas();
			Area area = setCurrentArea("superpacman/Level3", true);
			player = new SuperPacmanPlayer(area, Orientation.DOWN, SPAWN_POSITION[4]);
			initPlayer(player);
			player.start = true; //uncomment this line if not beginning the game in GameLaunch
			return true;
		}
		return false;
	}

	public void end() {
		super.end();
		this.end = true;
	}
	
	/**
	 * pauses or resumes everything that can be, in the area
	 * @param over (boolean): if true, pauses everything no matter what their original state is. If false, it pauses or resumes everything depending on their original state 
	 */
	private void pausingEveryone(boolean over) {
		if (!over) {
			pause = (pause) ? false : true;
			player.pause = (player.pause) ? false : true;
			SuperPacmanArea area = (SuperPacmanArea)getCurrentArea();
			for (AnimatedCollectable i : area.behavior.animated) {
				i.pause = (i.pause) ? false : true;
			}
			for (Ghost i : area.behavior.ghosts) {
				i.pause = (i.pause) ? false : true;
			}
		} else {
			pause = true;
			player.pause = true;
			SuperPacmanArea area = (SuperPacmanArea)getCurrentArea();
			for (AnimatedCollectable i : area.behavior.animated) {
				i.pause = true;
			}
			for (Ghost i : area.behavior.ghosts) {
				i.pause = true;
			}
		}
		
	}
}