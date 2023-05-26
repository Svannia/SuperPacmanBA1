package ch.epfl.cs107.play.game.superpacman.actor.gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class HiddenRoomRiddleGUI extends GUI{

	public void draw(Canvas canvas) {
		super.draw(canvas);
		
		List<TextGraphics> text0 = new ArrayList<TextGraphics>();
		
		text0.add(new TextGraphics("FINAL RIDDLE", 1, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)4.7, height-1.375f))));
		text0.add(new TextGraphics("What would you take on a ", 0.8f, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)2.5, height-3))));
		text0.add(new TextGraphics("deserted island ? ", 0.8f, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)3, height-4))));
		text0.add(new TextGraphics("A : Knife", 0.5f, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)2, height-6))));
		text0.add(new TextGraphics("B : Satellite phone", 0.5f, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)2, height-7))));
		text0.add(new TextGraphics("C : Your physics teacher", 0.5f, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)2, height-8))));
		
		for (int i=0; i<text0.size(); i++) {
			text0.get(i).draw(canvas);
		}
	}
}