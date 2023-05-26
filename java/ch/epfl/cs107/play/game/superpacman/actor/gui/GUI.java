package ch.epfl.cs107.play.game.superpacman.actor.gui;

import ch.epfl.cs107.play.game.actor.Graphics;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public abstract class GUI implements Graphics {
	
	
	protected Vector anchor;
	protected float width;
	protected float height;

	public void draw(Canvas canvas) {
		width = canvas.getScaledWidth ();
		height = canvas.getScaledHeight ();
		anchor = canvas.getTransform().getOrigin().sub(new Vector(width/2, height/2));
	}

}
