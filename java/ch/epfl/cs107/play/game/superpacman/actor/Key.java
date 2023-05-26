package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

public class Key extends SuperPacmanCollectable implements Logic{
	
	/**
	 * Default Key constructor
	 * @param area        (Area): Owner area, not null
	 * @param orientation (Orientation): Initial orientation of the entity, not null
	 * @param position    (DiscreteCoordinate): Initial position of the entity, not null
	 */
	public Key(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		sprite = new Sprite ( "superpacman/key" , 1, 1.f, this);
	}

	private Sprite sprite; 
	protected Logic collected = Logic.FALSE;
	
	public void collect() {
	super.collect();
	this.collected = Logic.TRUE;
	}
	
	public boolean isOn() {
		if (this.collected == Logic.TRUE) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isOff() {
		return ( collected == Logic.FALSE);
	}
	
	public void draw(Canvas canvas) {
		sprite.draw(canvas);
	}
	
	public void update(float deltaTime) {
		super.update(deltaTime);
	}
	
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((SuperPacmanInteractionVisitor)v).interactWith(this);
    }

	@Override
	public float getIntensity() {
		if (collected == Logic.TRUE) {
			return (float)1.0;
		}else {
		return (float)0.0;
		}
	}
}