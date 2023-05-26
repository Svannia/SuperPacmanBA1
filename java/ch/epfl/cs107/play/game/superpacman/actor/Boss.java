package ch.epfl.cs107.play.game.superpacman.actor;

import java.awt.Color;

import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Animation;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Boss extends Pinky {
	private Sprite[][] sprites_normal = RPGSprite.extractSprites("superpacman/pacmanBoss", 4, 1, 1, this , 64, 64, new Orientation[] {Orientation.DOWN, Orientation.LEFT, Orientation.UP, Orientation.RIGHT});
	private Animation[] animation_normal = Animation.createAnimations(ANIMATION_DURATION/4, sprites_normal);
	
	private TextGraphics hpText;
	private ImageGraphics rectangle;
	public int hpBoss;
	public boolean isVulnerable = false;	

	/**
	 * Default Boss constructor
	 * @param area        (Area): Owner area, not null
	 * @param orientation (Orientation): Initial orientation of the entity, not null
	 * @param position    (DiscreteCoordinate): Initial position of the entity, not null
	 */
	public Boss(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		this.hpBoss = 100;
		this.MIN_AFRAID_DISTANCE = 7;
		this.SPEED_WHEN_AFRAID = SuperPacmanPlayer.SPEED-3;
		this.SPEED_WHEN_NOT_AFRAID = SuperPacmanPlayer.SPEED+3;
		hpText = new TextGraphics("HP : "+Integer.toString(hpBoss), 0.5f, Color.RED, null, -1, true, false, null);
		hpText.setParent(this);
		this.hpText.setAnchor(new Vector(0f,1.1f));
		rectangle = new ImageGraphics(ResourcePath.getSprite("superpacman/rectangle"), 2, 0.5f);
		rectangle.setParent(this);
		this.rectangle.setAnchor(new Vector(0f,1.1f));
		sprites_afraid = RPGSprite.extractSprites("superpacman/pacmanBossAfraid", 4, 1, 1, this , 64, 64);
		animation_afraid = new Animation(ANIMATION_DURATION/4, sprites_afraid, true);
	}
	
	public void update(float deltaTime) {
		super.update(deltaTime);
		
		hpText.setText("HP : "+Integer.toString(hpBoss));
	}
	
	/**
	 * lowers the boss hp depending on weapon damage
	 * @param weapon (Weapon): weapon to get damage value from
	 */
	public void loosesHP(Weapon weapon) {
		hpBoss -= weapon.getDamage();
	}
	
	/**
	 * to know if the boss is definitely dead or not
	 * @return (boolean): true if the boss hp is 0 or lower
	 */
	public boolean isDead() {
		if (hpBoss<=0) return true;
		else return false;
	}
	
	public void draw(Canvas canvas) {
		super.draw(canvas);
		rectangle.draw(canvas);
		hpText.draw(canvas);
	}
	
	Animation animationNormal() {
		return animation_normal[getOrientation().ordinal()];
	}

	Orientation getNextOrientation() {
		return getNextOrientation(this.target);
	}

}
