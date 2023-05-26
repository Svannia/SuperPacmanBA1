package ch.epfl.cs107.play.game.tutos.actor;

import java.awt.Color;

import ch.epfl.cs107.play.game.actor.Entity;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;
;


public class SimpleGhost extends Entity {
	
	private Sprite sprite;
	
	float hp;
		
	public boolean isWeak() {
		boolean isWeak=false;
		if (hp<=0) isWeak=true;
		return isWeak;
	}
		
	public void strenghten() {
		hp=10;
	}
	
	private TextGraphics hpText;
	
	public SimpleGhost(Vector position , String spriteName) {
		super(position);
		sprite = new Sprite(spriteName, 1, 1.f, this);
		hp=10;
		hpText = new TextGraphics(Integer.toString((int)hp), 0.4f, Color.BLUE);
		hpText.setParent(this);
		this.hpText.setAnchor(new Vector(0f, 1.1f));
	}
	
	
	
	public void draw(Canvas canvas) {
		this.sprite.draw(canvas);
		this.hpText.draw(canvas);
	}
	

	public void update(float deltaTime) {
        hp = hp - deltaTime;
        this.hpText.setText(Integer.toString((int)hp));
        if (hp <=0) {
            hp = 0;
            this.hpText.setText(Integer.toString((int)hp));
        }
	}
	
	public void moveUp(float delta) {
		setCurrentPosition(getPosition().add(0.f, delta));
	}
	public void moveDown(float delta) {
		setCurrentPosition(getPosition().add(0.f, -delta));
	}
	public void moveLeft(float delta) {
		setCurrentPosition(getPosition().add(-delta, 0.f));
	}
	public void moveRight(float delta) {
		setCurrentPosition(getPosition().add(delta, 0.f));
	}
}
