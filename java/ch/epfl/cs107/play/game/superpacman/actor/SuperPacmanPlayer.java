package ch.epfl.cs107.play.game.superpacman.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Animation;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.game.rpg.actor.Player;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.game.superpacman.actor.gui.HiddenRoomRiddleGUI;
import ch.epfl.cs107.play.game.superpacman.actor.gui.InstructionGUI;
import ch.epfl.cs107.play.game.superpacman.actor.gui.Launcher;
import ch.epfl.cs107.play.game.superpacman.actor.gui.RiddleGUI;
import ch.epfl.cs107.play.game.superpacman.actor.gui.SuperPacmanStatusGUI;
import ch.epfl.cs107.play.game.superpacman.actor.gui.TransitionRiddleGUI;
import ch.epfl.cs107.play.game.superpacman.area.HiddenRoom;
import ch.epfl.cs107.play.game.superpacman.area.Level3;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.game.superpacman.area.text_areas.HiddenRoomRiddleArea;
import ch.epfl.cs107.play.game.superpacman.area.text_areas.InstructionArea;
import ch.epfl.cs107.play.game.superpacman.area.text_areas.RiddleArea;
import ch.epfl.cs107.play.game.superpacman.area.text_areas.TextAreas;
import ch.epfl.cs107.play.game.superpacman.area.text_areas.TransitionRiddle;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

public class SuperPacmanPlayer extends Player{
	public final static int SPEED = 4;
	private final int ANIMATION_DURATION = 4;
	public final static int MAX_HP = 5;
	public boolean start = false;
	public int hp;
	public int score;
	private Orientation desiredOrientation;
	private boolean gameFinished = false;
	public boolean isInvincible = false;
	public boolean isDead = false;
	public float timer;
	public boolean pause;
	public RiddleKey collectedKey;
	private Weapon weaponPlayer = null;
	public boolean inferiorWeapon=false;
	private int HiddenRoomAttempts = 0;
	private Sprite[][] sprites = RPGSprite.extractSprites("superpacman/pacman", 4, 1, 1, this , 64, 64, new Orientation [] { Orientation.DOWN, Orientation.LEFT, Orientation.UP, Orientation.RIGHT});
	private Animation [] animations = Animation.createAnimations(ANIMATION_DURATION/2, sprites);
	private SuperPacmanPlayerHandler handler;
	private SuperPacmanStatusGUI status;
	private RiddleGUI riddle;
	private HiddenRoomRiddleGUI HDriddle;
	private TransitionRiddleGUI transitionRiddle;
	private InstructionGUI instructions;
	private Launcher launcher = new Launcher();
	
	/**
	 * Default SuperPacmanPlayer constructor
	 * @param area        (Area): Owner area, not null
	 * @param orientation (Orientation): Initial orientation of the entity, not null
	 * @param position    (DiscreteCoordinate): Initial position of the entity, not null
	 */
	public SuperPacmanPlayer(Area area, Orientation orientation, DiscreteCoordinates position){
		super(area, orientation, position);
		this.hp = 5;
		this.score = 0;
		this.timer = 0;
		handler = new SuperPacmanPlayerHandler();
		desiredOrientation = null;
	}

	 public void update(float deltaTime) {
		super.update(deltaTime);
		Keyboard keyboard= getOwnerArea().getKeyboard();
						
		if(getOwnerArea() instanceof RiddleArea) {
			RiddleArea a = (RiddleArea)getOwnerArea();
			riddle = new RiddleGUI(this);
			if (keyboard.get(Keyboard.A).isReleased() || keyboard.get(Keyboard.B).isReleased() || keyboard.get(Keyboard.C).isReleased() || keyboard.get(Keyboard.D).isReleased()) {
				solving();
				if (keyboard.get(a.rightEntry()).isReleased()) {
					a.getKey().collect();
					a.getKey().rightAnswer=true;
				}
				else score -= ((RiddleArea)getOwnerArea()).scoreMinus; 
			}
		}
		
		if (getOwnerArea() instanceof HiddenRoomRiddleArea) {
			HDriddle = new HiddenRoomRiddleGUI();
			if (keyboard.get(Keyboard.A).isReleased() || keyboard.get(Keyboard.B).isReleased() || keyboard.get(Keyboard.C).isReleased() || keyboard.get(Keyboard.D).isReleased()) {
				if (keyboard.get(Keyboard.A).isReleased()) {
					weaponPlayer = ((HiddenRoomRiddleArea)getOwnerArea()).getAxe();
				}else if (keyboard.get(Keyboard.B).isReleased()) {
					weaponPlayer = ((HiddenRoomRiddleArea)getOwnerArea()).getHammer();
					
				}else if (keyboard.get(Keyboard.C).isReleased()) {
					weaponPlayer = ((HiddenRoomRiddleArea)getOwnerArea()).getSword();
				}
				HiddenRoom.weapon.activated = true;
				solving();
			}
		}
		
		if (getOwnerArea() instanceof TransitionRiddle) {
			transitionRiddle = new TransitionRiddleGUI();
			if (keyboard.get(Keyboard.A).isReleased() || keyboard.get(Keyboard.B).isReleased() || keyboard.get(Keyboard.C).isReleased() || keyboard.get(Keyboard.D).isReleased()) {
				if (keyboard.get(TransitionRiddle.getRightEntry()).isReleased()) {
					hiddenRoomSolving();
				} else {
					HiddenRoomAttempts++;
					solving(); 
				}
			}
		}
		
		if (getOwnerArea() instanceof Level3 && ((Level3)getOwnerArea()).getHiddenRoomAttempts()!=HiddenRoomAttempts) {
			((Level3)getOwnerArea()).setHiddenRoomAttempts(HiddenRoomAttempts);
		}
		
		if (getOwnerArea() instanceof InstructionArea) {
			instructions = new InstructionGUI();
			if (keyboard.get(Keyboard.ENTER).isReleased()) solving();
		}
					
		if (!pause && !(getOwnerArea() instanceof TextAreas)) {
				
			if (keyboard.get(Keyboard.LEFT).isDown()) desiredOrientation = Orientation.LEFT;
			if (keyboard.get(Keyboard.RIGHT).isDown()) desiredOrientation = Orientation.RIGHT;
			if (keyboard.get(Keyboard.DOWN).isDown()) desiredOrientation = Orientation.DOWN;
			if (keyboard.get(Keyboard.UP).isDown()) desiredOrientation = Orientation.UP;
			if (desiredOrientation!=null && !isDisplacementOccurs() && getOwnerArea().canEnterAreaCells(this, Collections.singletonList( getCurrentMainCellCoordinates().jump( desiredOrientation.toVector ())))) {
				orientate(desiredOrientation);
				move(SPEED);
			}
				
			if (isDisplacementOccurs()) {
				animations[getOrientation().ordinal()].update(deltaTime);
			} else{
				animations[getOrientation().ordinal()].reset();
			}
								
			if (isDead) Death();
				
			if (timer>0) {
				timer -= deltaTime; 
			} else if (timer <= 0) {
				isInvincible = false;
				inferiorWeapon = false;
				timer = 0;
			}
		}
		status = new SuperPacmanStatusGUI(this);

	 }	
	 
	 /**
		 * moves the player inside of a text area to the exit door
		 */
	 private void solving() {
			 orientate(Orientation.RIGHT);
			 move(SPEED);
	 }
	 
	 /**
		 * moves the player inside of a text area to a different door, located above the spawn position
		 */
	 private void hiddenRoomSolving() {
		 orientate(Orientation.UP);
		 move(SPEED);
		 TransitionRiddle.activated = true;
	 }
	 
	 /**
		 * getter for gameFinished
		 * @return (boolean): true if the game is finished
		 */
	 public boolean gameFinished() {
		 return gameFinished;
	 }
	 
	 /**
		 * getter for weaponPlayer
		 * @return (Weapon): weapon that the player has in possession
		 */
	 public Weapon getWeapon() {
		 return weaponPlayer;
	 }
	 
	 /**
		 * respawns the player to the area's spawn position by unregistering and re-registering it to the right location. ghosts of the area are also taken back to their shelter and their memory of the player is erased
		 */
	 public void Death() {
		 desiredOrientation=null;
		 getOwnerArea().leaveAreaCells(this, getCurrentCells());
		 getOwnerArea().unregisterActor(this);
		 int[] respawn = ((SuperPacmanArea)getOwnerArea()).getSpawnCoordinates();
		 setCurrentPosition(new Vector(respawn[0], respawn[1]));
		 getOwnerArea().registerActor(this);
		 getOwnerArea().enterAreaCells(this, getCurrentCells());
		 animations[getOrientation().ordinal()].reset();
		 resetMotion();
		 ((SuperPacmanArea)getOwnerArea()).backToShelter();
		 ((SuperPacmanArea)getOwnerArea()).ghostsForget();
		 isDead = false;
	 }
	 
	 /**
		 * respawns the player without making the ghosts forget
		 */
	 public void Respawn() {
		 desiredOrientation=null;
		 getOwnerArea().leaveAreaCells(this, getCurrentCells());
		 int[] respawn = ((SuperPacmanArea)getOwnerArea()).getSpawnCoordinates();
		 setCurrentPosition(new Vector(respawn[0], respawn[1]));
		 getOwnerArea().enterAreaCells(this, getCurrentCells());
		 animations[getOrientation().ordinal()].reset();
		 resetMotion();
	 }
	 
	 /**
		 * add a collected diamond to the current area's total of collected diamonds
		 */
	 public void addDiamond() {
		((SuperPacmanArea)this.getOwnerArea()).diamondsTaken++;
	 }
	 
	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}

	@Override
	public List<DiscreteCoordinates> getFieldOfViewCells() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean wantsCellInteraction() {
		return true;
	}

	@Override
	public boolean wantsViewInteraction() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void interactWith(Interactable other) {
		other.acceptInteraction(handler);
			
	}

	@Override
	public boolean takeCellSpace() {
		return false;
	}

	@Override
	public boolean isCellInteractable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isViewInteractable() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((SuperPacmanInteractionVisitor)v).interactWith(this);		
	}

	@Override
	public void draw(Canvas canvas) {
		if (start && !(getOwnerArea() instanceof TextAreas)) {
			animations[getOrientation().ordinal()].draw(canvas);
			status.draw(canvas);
		} else if (getOwnerArea() instanceof RiddleArea) {
			riddle.draw(canvas);
		} else if (getOwnerArea() instanceof HiddenRoomRiddleArea) {
			HDriddle.draw(canvas);
		} else if (getOwnerArea() instanceof TransitionRiddle) {
			transitionRiddle.draw(canvas);
		} else if (getOwnerArea() instanceof InstructionArea) {
			instructions.draw(canvas);
		} else launcher.draw(canvas);
	}
	
	private class SuperPacmanPlayerHandler implements SuperPacmanInteractionVisitor{
		public void interactWith(Door door) {
			setIsPassingADoor(door);
			desiredOrientation=null;
		}
		public void interactWith(Cherry cherry) {
			score += cherry.score();
			cherry.collect();
		} 
		public void interactWith(Diamond diamond) {
			score += diamond.score();
			diamond.collect();
			addDiamond();
		}
		public void interactWith(SuperPacmanPlayer player) {
		}
		public void interactWith(Bonus bonus) {
			bonus.collect();
			isInvincible = true;
			timer = 10;
		}
		public void interactWith(Key key) {
			if (!(key instanceof RiddleKey)) key.collect();
			else {
				collectedKey = (RiddleKey) key;
			}
		}
		public void interactWith(Heart heart) {
			heart.collect();
			if (hp<MAX_HP) hp++;
		}
		public void interactWith(Ghost ghost) {
			if (isInvincible) {
				if (!(ghost.getCurrentCells().get(0)==ghost.shelter)) ghost.isEaten();

				if (ghost instanceof Boss) {
					((Boss)ghost).loosesHP(weaponPlayer);
					Respawn();
				}
				if (!(ghost instanceof Boss)) score += ghost.score();
				else if (ghost instanceof Boss && ((Boss)ghost).isDead()) {
					score += ghost.bossScore();
					gameFinished = true;
				}
			} else {
				isDead=true;
				if (hp>0) hp-=1;
				else hp=0;
			}
		}
		public void interactWith(Weapon weapon) {
			if (getOwnerArea() instanceof HiddenRoom && !weapon.activated) {
				weapon.collect();
				weaponPlayer = weapon;
			} else if (getOwnerArea() instanceof Level3) {
				if (weaponPlayer==null) {
					weapon.collect();
					weaponPlayer = weapon;
					((Level3)getOwnerArea()).weaponSignalOn();
				}
				if (weaponPlayer!=null && weaponPlayer.getDamage()>weapon.getDamage()) {
					inferiorWeapon = true;
					timer = 3;
				}
			}
		}
		public void interactWith(Doggo doggo) {
			doggo.smile();
		}
		
	}
}

