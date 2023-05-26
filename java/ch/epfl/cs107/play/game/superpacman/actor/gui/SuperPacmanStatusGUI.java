package ch.epfl.cs107.play.game.superpacman.actor.gui;

import java.awt.Color;

import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.game.superpacman.actor.SuperPacmanPlayer;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class SuperPacmanStatusGUI extends GUI{
	
	private int hp;
	private int score;
	private SuperPacmanPlayer player;
	
	/**
	 * Default RiddleGUI constructor
	 * @param player (SuperPacmanPlayer): 
	 */
	public SuperPacmanStatusGUI(SuperPacmanPlayer player) {
		hp = player.hp;
		score = player.score;
		this.player = player;
	}

	private final float DEPTH = 5;

	public void draw(Canvas canvas) {
		super.draw(canvas);
		
		ImageGraphics[] life = new ImageGraphics[SuperPacmanPlayer.MAX_HP];
		TextGraphics tab = new TextGraphics("Press 'Tab' to start again", 1.2f, Color.GRAY, Color.BLACK, 0.05f, true, true, anchor.add(new Vector((float)0.4, height-10.5f)));

		for (int i=0; i<this.hp; ++i) {
			life[i] = new ImageGraphics(ResourcePath.getSprite("superpacman/lifeDisplay"), 1.f, 1.f, new RegionOfInterest(0, 0, 64, 64), anchor.add(new Vector((float)0.5+i, height-1.375f)), 1, DEPTH);
			life[i].draw(canvas);
		}
		for (int i=this.hp; i<SuperPacmanPlayer.MAX_HP; ++i) {
			life[i] = new ImageGraphics(ResourcePath.getSprite("superpacman/lifeDisplay"), 1.f, 1.f, new RegionOfInterest(64, 0, 64, 64), anchor.add(new Vector((float)0.5+i, height-1.375f)), 1, DEPTH);
			life[i].draw(canvas);
		}
		
		TextGraphics scoreText = new TextGraphics("SCORE : "+Integer.toString(score), 1, Color.YELLOW, Color.BLACK, 0.05f, true, false, anchor.add(new Vector((float)6.5, height-1.375f)));
		scoreText.draw(canvas);
		
		if (player.pause == true && hp>0 && !player.gameFinished() && player.score>=0) {
			TextGraphics pauseText = new TextGraphics("PAUSE", 3, Color.YELLOW, Color.BLACK, 0.05f, true, false, anchor.add(new Vector((float)2.5, height-7)));
			pauseText.draw(canvas);
		}
		
		if (hp==0 || player.score<0) {
			TextGraphics Game = new TextGraphics("Game", 3, Color.GRAY, Color.BLACK, 0.05f, true, false, anchor.add(new Vector((float)2.5, height-6)));
			Game.draw(canvas);
			TextGraphics Over = new TextGraphics("Over", 3, Color.GRAY, Color.BLACK, 0.05f, true, false, anchor.add(new Vector((float)3.2, height-9)));
			Over.draw(canvas);
			tab.draw(canvas);
		}
		
		if (player.getWeapon()!=null) {
			ImageGraphics weapon = new ImageGraphics(player.getWeapon().getIcon(), 2.f, 2.f, new RegionOfInterest(0, 0, 64, 64), anchor.add(new Vector((float)0.1, height-3.5f)));
			weapon.draw(canvas);
		}
		
		if (player.inferiorWeapon) {
			TextGraphics inferior = new TextGraphics("This weapon is weaker than", 1f, Color.YELLOW, Color.BLACK, 0.05f, true, false, anchor.add(new Vector((float)0.5, height-11)));
			inferior.draw(canvas);
			TextGraphics inferior2 = new TextGraphics("the one you already have", 1f, Color.YELLOW, Color.BLACK, 0.05f, true, false, anchor.add(new Vector((float)0.5, height-12.1f)));
			inferior2.draw(canvas);

		}
		
		if (player.gameFinished()) {
			TextGraphics congrats = new TextGraphics("Congrats!!!!", 2.5f, Color.GRAY, Color.BLACK, 0.05f, true, false, anchor.add(new Vector((float)0.5, height-6)));
			congrats.draw(canvas);
			TextGraphics beat = new TextGraphics("You beat the game!", 1.5f, Color.GRAY, Color.BLACK, 0.05f, true, false, anchor.add(new Vector((float)0.2, height-8)));
			beat.draw(canvas);
			tab.draw(canvas);
		}
	}

}
