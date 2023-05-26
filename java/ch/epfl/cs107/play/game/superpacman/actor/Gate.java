package ch.epfl.cs107.play.game.superpacman.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

public class Gate extends AreaEntity {
	
	private Sprite sprite;
	private DiscreteCoordinates position;
	public Logic key = Logic.FALSE;

	/**
	 * Default Gate constructor
	 * @param area        (Area): Owner area, not null
	 * @param orientation (Orientation): Initial orientation of the entity, not null
	 * @param position    (DiscreteCoordinate): Initial position of the entity, not null
	 * @param key (Logic): associated key that will open the gate if collected
	 */
	public Gate(Area area, Orientation orientation, DiscreteCoordinates position, Logic key) {
		super(area, orientation, position);
		if (orientation == Orientation.UP || orientation == Orientation.DOWN) {
			sprite = new Sprite("superpacman/gate", 1, 1.f, this, new RegionOfInterest(0, 0, 64, 64));
		}
		if (orientation == Orientation.LEFT || orientation == Orientation.RIGHT) {
			sprite = new Sprite("superpacman/gate", 1, 1.f, this, new RegionOfInterest(0, 64, 64, 64));
		}
		key = this.key;
		if (key.isOff()) ((SuperPacmanArea)area).getGraph().setSignal(position, key);
		this.position = position;
	}
	
	public void update(float deltaTime) {
		if (this.key.isOn()) ((SuperPacmanArea)getOwnerArea()).getGraph().setSignal(position, key);
	}
	
	/**
	 * changes the gate's key into a TRUE logic signal and calls takeCellSpace()
	 */
	public void open() {
		this.key = Logic.TRUE;
		takeCellSpace();
	}

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}

	@Override
	public boolean takeCellSpace() {
		if (this.key == Logic.TRUE) {
			return false;
		}else {
		return true;
		}
	}
	

	@Override
	public boolean isCellInteractable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isViewInteractable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Canvas canvas) {
		if (takeCellSpace()) {
		sprite.draw(canvas);
		}
	}

}