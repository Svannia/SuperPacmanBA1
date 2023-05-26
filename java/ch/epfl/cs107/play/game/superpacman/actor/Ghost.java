package ch.epfl.cs107.play.game.superpacman.actor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Animation;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public abstract class Ghost extends MovableAreaEntity implements Interactable, Interactor{
	
	public final int GHOST_SCORE = 500;
	private final int BOSS_SCORE = 10000;
	private final int FIELD_OF_VIEW = 5;
	protected final int ANIMATION_DURATION = 8;
	protected int speed = SuperPacmanPlayer.SPEED*6;
	public DiscreteCoordinates shelter;
	public boolean isEaten = false;
	private GhostHandler handler;
	protected SuperPacmanPlayer memoire = null;
	protected boolean isAfraid = false;
	public boolean pause = false;
	protected Sprite[] sprites_afraid = RPGSprite.extractSprites("superpacman/ghost.afraid", 2, 1, 1, this , 16, 16);
	protected Animation animation_afraid = new Animation(ANIMATION_DURATION, sprites_afraid, true);

	/**
	 * Default Ghost constructor
	 * @param area        (Area): Owner area, not null
	 * @param orientation (Orientation): Initial orientation of the entity, not null
	 * @param position    (DiscreteCoordinate): Initial position of the entity, not null
	 */
	public Ghost(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		this.shelter=position;
		handler = new GhostHandler();
		}
	
	public void update(float deltaTime) {
		
		if (!pause) {
			super.update(deltaTime);
			if (isDisplacementOccurs()) {
				if (isAfraid) animation_afraid.update(deltaTime);
				else animationNormal().update(deltaTime);
			} else {
				orientate(getNextOrientation());
				move(speed);
			}
			
			//if (isEaten) isEaten();
		}
	}
	
	/**
	 * getter for the score got after the boss is beaten
	 */
	public int bossScore() {
		return BOSS_SCORE;
	}
	
	/**
	 * setter for what the ghost keeps in memory
	 * @param player (SuperPacmanPlayer): player that the ghost remembers
	 */
	public void setMemoire(SuperPacmanPlayer player) {
		this.memoire = player;
	}
	
	/**
	 * setter for whether the ghost is afraid or not
	 * @param set (boolean): determines the state of isAfraid
	 */
	public void setIsAfraid(boolean set) {
		this.isAfraid = set;
	}
	
	/**
	 * make the ghost leave the cells it's occupying and respawn at its shelter position
	 */
	public void isEaten() {
		this.getOwnerArea().leaveAreaCells(this, this.getEnteredCells());
		this.setCurrentPosition(new Vector(this.shelter.x, this.shelter.y));
		this.resetMotion();
		this.getOwnerArea().enterAreaCells(this, this.getCurrentCells());
		this.setMemoire(null);
		//isEaten = false;
	}
	
	/**
	 * calculate the ghost's next orientation to manage its movement
	 * @return (Orientation): orientation the ghost will take to move in
	 */
	abstract Orientation getNextOrientation();
	
	/**
	 * chooses the ghost animation depending on its orientation
	 * @return (Animation): correct sequence of sprites to use for animation
	 */
	abstract Animation animationNormal();
	
	/**
	 * getter for the score ghosts give when they're eaten
	 */
	public int score() {
		return GHOST_SCORE;
	}
	
	public void draw(Canvas canvas) {
		if (isAfraid) animation_afraid.draw(canvas);
		else animationNormal().draw(canvas);
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

	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}

	public List<DiscreteCoordinates> getFieldOfViewCells() {
		List<DiscreteCoordinates> result = new ArrayList<DiscreteCoordinates>();
		for (int x=FIELD_OF_VIEW*(-1); x<=FIELD_OF_VIEW; ++x) {
			for (int y=FIELD_OF_VIEW*(-1); y<=FIELD_OF_VIEW; ++y) {
				if (((getCurrentCells().get(0).x)+x)<=getOwnerArea().getWidth() && ((getCurrentCells().get(0).y)+y)<=getOwnerArea().getHeight()) {
					result.add((getCurrentCells().get(0)).jump(x,y));
				}
			}
				
		}
		return result;
	}
	
	public void interactWith(Interactable other) {
		other.acceptInteraction(handler);
	}

	public boolean wantsCellInteraction() {
		return false;
	}

	public boolean wantsViewInteraction() {
		return true;
	}
	
	private class GhostHandler implements SuperPacmanInteractionVisitor{
		
		public void interactWith(SuperPacmanPlayer player) {
			setMemoire(player);
		}
		
		public void interactWith(Diamond diamond) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void interactWith(Bonus bonus) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void interactWith(Cherry cherry) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void interactWith(Key key) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void interactWith(Ghost ghost) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void interactWith(Heart heart) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void interactWith(Weapon weapon) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void interactWith(Doggo doggo) {
			// TODO Auto-generated method stub
			
		}
	}

}
