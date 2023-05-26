package ch.epfl.cs107.play.game.tutos;

import ch.epfl.cs107.play.game.areagame.Cell;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;

public class Tuto2Cell extends Cell{
	private final Tuto2Behaviour.Tuto2CellType type;

	public Tuto2Cell(int x, int y, Tuto2Behaviour.Tuto2CellType type){
		super(x, y);
		this.type = type;
	}

	public boolean isCellInteractable() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isViewInteractable() {
		// TODO Auto-generated method stub
		return false;
	}

	public void acceptInteraction(AreaInteractionVisitor v) {
		// TODO Auto-generated method stub
		
	}

	protected boolean canLeave(Interactable entity) {
		// TODO Auto-generated method stub
		return false;
	}

	protected boolean canEnter(Interactable entity) {
		// TODO Auto-generated method stub
		return false;
	}
}
