package ch.epfl.cs107.play.game.superpacman.actor;

import java.util.Queue;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaGraph;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RandomGenerator;
import ch.epfl.cs107.play.window.Canvas;

public abstract class IntelligentGhost extends Ghost{
	
	protected DiscreteCoordinates target;
	protected int SPEED_WHEN_AFRAID;
	protected int SPEED_WHEN_NOT_AFRAID;

	/**
	 * Default IntelligentGhost constructor
	 * @param area        (Area): Owner area, not null
	 * @param orientation (Orientation): Initial orientation of the entity, not null
	 * @param position    (DiscreteCoordinate): Initial position of the entity, not null
	 */
	public IntelligentGhost(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
	}
	
	public void update(float deltaTime) {
		super.update(deltaTime);

		if (isDisplacementOccurs()) {
		if(this.isAfraid) this.speed = SPEED_WHEN_AFRAID;
		if(!this.isAfraid) this.speed = SPEED_WHEN_NOT_AFRAID;
		}
	}
		
	public Orientation getNextOrientation(DiscreteCoordinates target) {
		resetMotion();
		AreaGraph graph = ((SuperPacmanArea)getOwnerArea()).getGraph();
		Queue<Orientation> path = graph.shortestPath(getCurrentMainCellCoordinates(), target);
		if (path==null) {
			int randomInt = RandomGenerator.getInstance().nextInt(4);
			return Orientation.fromInt(randomInt);
		}
		return path.poll();
	}
	
	public void draw(Canvas canvas) {
		super.draw(canvas);
	}

}
