package ch.epfl.cs107.play.game.superpacman.actor.gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class TransitionRiddleGUI extends GUI{
	
	public void draw(Canvas canvas) {
		super.draw(canvas);
		
		//65 = A, 66 = B, 67 = C, 68 = D
		
		List<TextGraphics> text0 = new ArrayList<TextGraphics>();
		//Correct Answer = ACACBD
		
		text0.add(new TextGraphics("SPECIAL RIDDLE", 1, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)3.5, height-1.375f))));
		text0.add(new TextGraphics("What is the correct sequence", 0.8f, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)1.5, height-4))));
		text0.add(new TextGraphics("of answers from 'RIDDLE 1' to", 0.8f, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)1.5, height-5))));
		text0.add(new TextGraphics("'RIDDLE 6' ?", 0.8f, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)4.5, height-6))));
		text0.add(new TextGraphics("A : DABBCD", 0.8f, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)1.5, height-8))));
		text0.add(new TextGraphics("B : ACACBD", 0.8f, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)1.5, height-9))));
		text0.add(new TextGraphics("C : CBDCAD", 0.8f, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)1.5, height-10))));
		text0.add(new TextGraphics("D : BCADAB", 0.8f, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)1.5, height-11))));
	
		for (int i=0; i<text0.size(); i++) {
			text0.get(i).draw(canvas);
		}
	}

}