package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Animation;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class AnimatedCollectable extends SuperPacmanCollectable{
	public boolean pause = false;
	protected Sprite [] sprites;
	protected Animation animations;
	protected static final int ANIMATION_DURATION = 8;

	/**
	 * Default AnimatedCollectable constructor
	 * @param area        (Area): Owner area, not null
	 * @param orientation (Orientation): Initial orientation of the entity, not null
	 * @param position    (DiscreteCoordinate): Initial position of the entity, not null
	 */
	public AnimatedCollectable(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
	}
	
	public void draw(Canvas canvas) {
		animations.draw(canvas);
    }
	    
	public void update(float deltaTime) {
    	if (!pause) {
	    	super.update(deltaTime);
	    	animations.update(deltaTime);
    	}
    }

}
