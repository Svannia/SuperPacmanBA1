package ch.epfl.cs107.play.game.superpacman.actor.gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class InstructionGUI extends GUI{
	
	public void draw(Canvas canvas) {
		super.draw(canvas);
		
		List<TextGraphics> text0 = new ArrayList<TextGraphics>();
		
		text0.add(new TextGraphics("INSTRUCTIONS", 1, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)3, height-1.375f))));
		text0.add(new TextGraphics("From now on, please pay", 0.8f, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)2.5, height-4))));
		text0.add(new TextGraphics("special attention to the", 0.8f, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)2.5, height-5))));
		text0.add(new TextGraphics("riddles, their number and", 0.8f, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)2.5, height-6))));
		text0.add(new TextGraphics("the letter representing the", 0.8f, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)2, height-7))));
		text0.add(new TextGraphics("answers.", 0.8f, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)4.5, height-8))));
		text0.add(new TextGraphics("PRESS 'ENTER' TO CONTINUE", 1, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)0.8, height-11))));

		for (int i=0; i<text0.size(); i++) {
			text0.get(i).draw(canvas);
		}
	}
}