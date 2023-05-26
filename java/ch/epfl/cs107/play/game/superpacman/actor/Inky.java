package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaGraph;
import ch.epfl.cs107.play.game.areagame.actor.Animation;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RandomGenerator;

public class Inky extends IntelligentGhost{
	private final int MAX_DISTANCE_WHEN_SCARED = 5;
	private final int MAX_DISTANCE_WHEN_NOT_SCARED = 10;
	public Sprite[][] sprites_normal = RPGSprite.extractSprites("superpacman/ghost.inky", 2, 1, 1, this , 16, 16, new Orientation[] {Orientation.UP, Orientation.RIGHT, Orientation.DOWN, Orientation.LEFT});
	public Animation[] animation_normal = Animation.createAnimations(ANIMATION_DURATION, sprites_normal);

	/**
	 * Default Inky constructor
	 * @param area        (Area): Owner area, not null
	 * @param orientation (Orientation): Initial orientation of the entity, not null
	 * @param position    (DiscreteCoordinate): Initial position of the entity, not null
	 */
	public Inky(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		this.target = randomCellinRadius(this.shelter, MAX_DISTANCE_WHEN_NOT_SCARED, ((SuperPacmanArea)getOwnerArea()).getGraph());
		this.SPEED_WHEN_AFRAID = SuperPacmanPlayer.SPEED+4;
		this.SPEED_WHEN_NOT_AFRAID = SuperPacmanPlayer.SPEED+6;
	}
	
	public void update(float deltaTime) {
		super.update(deltaTime);
		
		if (isAfraid) {
			this.target = randomCellinRadius(this.shelter, MAX_DISTANCE_WHEN_SCARED, ((SuperPacmanArea)getOwnerArea()).getGraph());
		} else if (!isAfraid && this.memoire != null){
			this.target = memoire.getCurrentCells().get(0);
		} else {
			this.target = randomCellinRadius(this.shelter, MAX_DISTANCE_WHEN_NOT_SCARED, ((SuperPacmanArea)getOwnerArea()).getGraph());
		}
	}
	
	/**
	 * calculates the target if it has to be close to a point
	 * @param center (DiscreteCoordinates): coordinates of the reference point to stay close to
	 * @param radius (int): radius around the center to stay into
	 * @param graph (AreaGraph): to ensure the target is an existing node of the area's graph
	 * @return (DiscreteCoordinates): coordinates of the target
	 */
	private DiscreteCoordinates randomCellinRadius(DiscreteCoordinates center, int radius, AreaGraph graph) {
		DiscreteCoordinates result;
		do{
			int x = RandomGenerator.getInstance().nextInt(radius);
			int y = RandomGenerator.getInstance().nextInt(radius);
			int signX = RandomGenerator.getInstance().nextInt(2);
			int signY = RandomGenerator.getInstance().nextInt(2);
			int coeffX;
			int coeffY;
			if (signX==0) coeffX = 1;
			else coeffX = -1;
			if (signY==0) coeffY = 1;
			else coeffY = -1;
			result = new DiscreteCoordinates(center.x+(coeffX*x),center.y+(coeffY*y));
		} while (!graph.nodeExists(result));
		return result;
	}

	Animation animationNormal() {
		return animation_normal[getOrientation().ordinal()];
	}

	Orientation getNextOrientation() {
		return getNextOrientation(this.target);
	}
	

}
