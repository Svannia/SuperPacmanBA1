package ch.epfl.cs107.play.game.tutos.actor;

import java.awt.Color;
import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

public class GhostPlayer extends MovableAreaEntity {
	private final static int ANIMATION_DURATION=8;
	private Sprite sprite;
	private float hp;
		
	public boolean isWeak() {
		boolean isWeak=false;
		if (hp<=0) isWeak=true;
		return isWeak;
	}
		
	public void strengthen() {
		hp=10;
	}
	
	private TextGraphics hpText;
	
	public GhostPlayer (Area owner, Orientation orientation, DiscreteCoordinates coordinates, String spriteName) {
		super(owner, orientation, coordinates);
		sprite = new Sprite(spriteName, 1, 1.f, this);
		this.hp=10;
		hpText = new TextGraphics(Integer.toString((int)hp), 0.4f, Color.BLUE);
		hpText.setParent(this);
		hpText.setAnchor(new Vector(0f, 1.1f));
	}
	
	public void enterArea(Area area, DiscreteCoordinates position){
        area.registerActor(this);
        area.setViewCandidate(this);
        setOwnerArea(area);
        setCurrentPosition(position.toVector());
        resetMotion();
    }
	
	public void leaveArea(){
        getOwnerArea().unregisterActor(this);
    }
	
	public void update(float deltaTime) {
		super.update(deltaTime);
        hp = hp - deltaTime;
        this.hpText.setText(Integer.toString((int)hp));
        if (hp <=0) {
            hp = 0;
            this.hpText.setText(Integer.toString((int)hp));
        }
        
        Keyboard keyboard = getOwnerArea().getKeyboard();
        Button up = keyboard.get(Keyboard.UP);
        if (up.isDown()) {
        	if (getOrientation()==Orientation.UP) {
        		move(ANIMATION_DURATION);
        	} else {
        		orientate(Orientation.UP);
        	}
        }
        Button down = keyboard.get(Keyboard.DOWN);
        if (down.isDown()) {
        	if (getOrientation()==Orientation.DOWN) {
        		move(ANIMATION_DURATION);
        	} else {
        		orientate(Orientation.DOWN);
        	}
        }
        Button right = keyboard.get(Keyboard.RIGHT);
        if (right.isDown()) {
        	if (getOrientation()==Orientation.RIGHT) {
        		move(ANIMATION_DURATION);
        	} else {
        		orientate(Orientation.RIGHT);
        	}
        }
        Button left = keyboard.get(Keyboard.LEFT);
        if (left.isDown()) {
        	if (getOrientation()==Orientation.LEFT) {
        		move(ANIMATION_DURATION);
        	} else {
        		orientate(Orientation.LEFT);
        	}
        }
	}
	

	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());	}

	public boolean takeCellSpace() {
		// TODO Auto-generated method stub
		return false;
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

	public void draw(Canvas canvas) {
		this.sprite.draw(canvas);
		this.hpText.draw(canvas);
	}
}
