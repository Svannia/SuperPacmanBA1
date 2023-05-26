package ch.epfl.cs107.play.game.tutos;

import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.Cell;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.tutosSolution.Tuto2Behavior.Tuto2Cell;
import ch.epfl.cs107.play.game.tutosSolution.Tuto2Behavior.Tuto2CellType;
import ch.epfl.cs107.play.window.Window;

public class Tuto2Behaviour extends AreaBehavior{
	public enum Tuto2CellType {
		NULL (0, false),
		WALL (-16777216, false), // #000000 RGB code of black
		IMPASSABLE (-8750470, false), // #7 A7A7A , RGB color of gray
		INTERACT (-256, true), // #FFFF00 , RGB color of yellow
		DOOR (-195580, true), // #FD0404 , RGB color of red
		WALKABLE (-1, true); // #FFFFFF , RGB color of white
		final int type;
		final boolean isWalkable ;
		Tuto2CellType (int type , boolean isWalkable ){
			this.type = type ;
			this. isWalkable = isWalkable ;
		}
		static Tuto2CellType toType(int type) {
			Tuto2CellType a=NULL;
			for (Tuto2CellType i : Tuto2CellType.values()) {
				if (i.type==type) {
					a=i;
				}
			}
			return a;
		}
	}
	public Tuto2Behaviour(Window window, String name){
		super(window, name);
		int height = getHeight();
		int width = getWidth();
		for(int y = 0; y < height; y++) {
			for (int x = 0; x < width ; x++) {
				Tuto2CellType color = Tuto2CellType.toType(getRGB(height-1-y, x));
				setCell(x,y, new Tuto2Cell(x,y,color));
			}
		}
	}
	
	public class Tuto2Cell extends Cell{
		private final Tuto2Behaviour.Tuto2CellType type;

		public Tuto2Cell(int x, int y, Tuto2Behaviour.Tuto2CellType type){
			super(x, y);
			this.type = type;
		}

		public boolean isCellInteractable() {
			return true;
		}

		public boolean isViewInteractable() {
			return false;
		}

		public void acceptInteraction(AreaInteractionVisitor v) {			
		}

		protected boolean canLeave(Interactable entity) {
			return true;
		}

		protected boolean canEnter(Interactable entity) {
			return type.isWalkable;
		}
	}

}
