package ch.epfl.cs107.play.game.superpacman.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class Doggo extends AreaEntity implements Interactable{
	
	private Sprite sprite;
	private String doggo;
	private DiscreteCoordinates coordinates;

	/**
	 * Default Doggo constructor
	 * @param area        (Area): Owner area, not null
	 * @param orientation (Orientation): Initial orientation of the entity, not null
	 * @param position    (DiscreteCoordinate): Initial position of the entity, not null
	 * @param spritename (String): sprite resource of the object to be diplayed
	 * @param doggo (String): name of the object
	 */
	public Doggo(Area area, Orientation orientation, DiscreteCoordinates position, String spritename, String doggo) {
		super(area, orientation, position);
		sprite = new Sprite(spritename, 1, 1.f, this);
		this.doggo = doggo;
		this.coordinates = position;
	}
	
	/**
	 * used to differentiate different doggo instances
	 * @return (String): resource of the doggo's sprite
	 */
	private String whichDoggo() {
		return doggo;
	}
	
	/**
	 * changes the doggo's sprite another (smiling face) sprite 
	 */
	public void smile() {
		if (whichDoggo() == "Cinq") sprite = new Sprite ("superpacman/SmileCinq", 1, 1.f, this);
		else sprite = new Sprite ("superpacman/SmileAhu", 1, 1.f, this);
	}

	@Override
	public void draw(Canvas canvas) {
		sprite.draw(canvas);
	}
	
	public void update(float deltaTime) {
		super.update(deltaTime);
	}
	
	public DiscreteCoordinates getCoordinates() {
		return coordinates;
	}

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}

	@Override
	public boolean takeCellSpace() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCellInteractable() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isViewInteractable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((SuperPacmanInteractionVisitor)v).interactWith(this);	
		
	}

}