package ch.epfl.cs107.play.game.superpacman.actor;

import java.util.ArrayList;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaGraph;
import ch.epfl.cs107.play.game.areagame.actor.Animation;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RandomGenerator;
import ch.epfl.cs107.play.signal.logic.Logic;

public class Pinky extends IntelligentGhost {
	protected final int DEACTIVATION_RADIUS = 5;
	protected final int MAX_ATTEMPTS = 100;
	protected int MIN_AFRAID_DISTANCE;
	private Sprite[][] sprites_normal = RPGSprite.extractSprites("superpacman/ghost.pinky", 2, 1, 1, this , 16, 16, new Orientation[] {Orientation.UP, Orientation.RIGHT, Orientation.DOWN, Orientation.LEFT});
	private Animation[] animation_normal = Animation.createAnimations(ANIMATION_DURATION, sprites_normal);

	/**
	 * Default Pinky constructor
	 * @param area        (Area): Owner area, not null
	 * @param orientation (Orientation): Initial orientation of the entity, not null
	 * @param position    (DiscreteCoordinate): Initial position of the entity, not null
	 */
	public Pinky(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		this.SPEED_WHEN_AFRAID = SuperPacmanPlayer.SPEED+4;
		this.SPEED_WHEN_NOT_AFRAID = SuperPacmanPlayer.SPEED+6;
		this.MIN_AFRAID_DISTANCE = 5;
	}
	
	
	public void update(float deltaTime) {
		super.update(deltaTime);
		List<DiscreteCoordinates> deactivatedNodes = new ArrayList<DiscreteCoordinates>();
		
		if (this.isAfraid && this.memoire !=null) {
			for (int i=0; i<DEACTIVATION_RADIUS; i++) {
				DiscreteCoordinates a = memoire.getCurrentCells().get(0);
				DiscreteCoordinates b = new DiscreteCoordinates(a.x+i, a.y+i);
				if (((SuperPacmanArea)getOwnerArea()).getGraph().nodeExists(b)) {
					((SuperPacmanArea)getOwnerArea()).getGraph().setSignal(b, Logic.FALSE);
					deactivatedNodes.add(b);
				}
			}
			this.target = randomCellFromRadius(memoire.getCurrentCells().get(0), MIN_AFRAID_DISTANCE, ((SuperPacmanArea)getOwnerArea()).getGraph());
		} else if (!this.isAfraid && this.memoire != null){
			this.target = memoire.getCurrentCells().get(0);
		} else {
			this.target = randomCell(this.getCurrentMainCellCoordinates(), ((SuperPacmanArea)getOwnerArea()).getGraph());
		}
		
		for (DiscreteCoordinates i : deactivatedNodes) {
			((SuperPacmanArea)getOwnerArea()).getGraph().setSignal(i, Logic.TRUE);
		}
	}

	Orientation getNextOrientation() {
		return getNextOrientation(this.target);
	}
	
	/**
	 * calculates the target when it has to be away from a given point
	 * @param center (DiscreteCoordinates): coordinates of reference point to stay away from
	 * @param radius (int): radius around the reference point to not be into 
 	 * @param graph (Areagraph): ensures the target is an existing node of the area's graph
	 * @return (DiscreteCoordinates): coordinates of the target
	 */
	protected DiscreteCoordinates randomCellFromRadius(DiscreteCoordinates center, int radius, AreaGraph graph) {
		DiscreteCoordinates result;
		
		int reps = 0;
		do {
			reps++;
			int x = RandomGenerator.getInstance().nextInt(this.getOwnerArea().getWidth()-1);
			int y = RandomGenerator.getInstance().nextInt(this.getOwnerArea().getHeight()-1);
			result = new DiscreteCoordinates(x,y);
			if (reps>=MAX_ATTEMPTS) break;
		} while (result.x<center.x-radius && result.x>center.x+radius && result.y<center.y-radius && result.y>center.y+radius  && !graph.nodeExists(result));
		if (!graph.nodeExists(result)) return randomCell(this.getCurrentMainCellCoordinates(), ((SuperPacmanArea)getOwnerArea()).getGraph());
		return result;
	}
	
	/**
	 * calculates the target when it can be anywhere on the map
	 * @param posiion (DiscreteCoordinates): position of the ghost, since the algorithm checks that the target isn't the current ghost's position
	 * @param graph (AreaGraph): ensures the target is an existing node of the area's graph
	 * @return (DiscreteCoordinates): coordinates of the target
	 */
	protected DiscreteCoordinates randomCell(DiscreteCoordinates position, AreaGraph graph) {
		DiscreteCoordinates result;
		do{
			int x = RandomGenerator.getInstance().nextInt(this.getOwnerArea().getWidth()-1);
			int y = RandomGenerator.getInstance().nextInt(this.getOwnerArea().getHeight()-1);
			result = new DiscreteCoordinates(x,y);
		} while (!graph.nodeExists(result) || result == position);
		return result;
	}

	Animation animationNormal() {
		return animation_normal[getOrientation().ordinal()];
	}
	

}
