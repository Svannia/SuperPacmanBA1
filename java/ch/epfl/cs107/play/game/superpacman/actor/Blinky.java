package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Animation;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RandomGenerator;

public class Blinky extends Ghost {
	private final int MAX=4;
	public Sprite[][] sprites_normal = RPGSprite.extractSprites("superpacman/ghost.blinky", 2, 1, 1, this , 16, 16, new Orientation[] {Orientation.UP, Orientation.RIGHT, Orientation.DOWN, Orientation.LEFT});
	public Animation[] animation_normal = Animation.createAnimations(ANIMATION_DURATION, sprites_normal);

	/**
	 * Default constructor
	 * @param area        (Area): Owner area, not null
	 * @param orientation (Orientation): Initial orientation of the entity, not null
	 * @param shelter    (DiscreteCoordinate): Initial and shelter position of the entity, not null
	 */
	public Blinky(Area area, Orientation orientation, DiscreteCoordinates shelter) {
		super(area, orientation, shelter);
	}

	Orientation getNextOrientation() {
		int randomInt = RandomGenerator.getInstance().nextInt(MAX);
		return Orientation.fromInt(randomInt);
	}

	Animation animationNormal() {
		return animation_normal[getOrientation().ordinal()];
	}

}
