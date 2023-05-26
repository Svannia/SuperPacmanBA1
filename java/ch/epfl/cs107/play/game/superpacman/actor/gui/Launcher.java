package ch.epfl.cs107.play.game.superpacman.actor.gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.superpacman.SuperPacman;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Launcher extends GUI {
	
	public void draw(Canvas canvas) {
		super.draw(canvas);
		
		List<TextGraphics> text = new ArrayList<TextGraphics>();
		
		text.add(new TextGraphics("WELCOME", 1, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)4.7, height-1.375f))));
		text.add(new TextGraphics("HIGHEST SCORE : "+Integer.toString(SuperPacman.highestScore), 1, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)2, height-3))));
		text.add(new TextGraphics("At any moment during the game, you can press : ", 0.6f, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, false, anchor.add(new Vector((float)0.1f, height-6))));
		text.add(new TextGraphics("- 'SPACE' to pause/resume the game", 0.6f, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, false, anchor.add(new Vector((float)0.1f, height-7  ))));
		text.add(new TextGraphics("- 'ESCAPE' to quit the game", 0.6f, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, false, anchor.add(new Vector((float)0.1f, height-8))));
		text.add(new TextGraphics("PRESS 'ENTER' TO START", 1, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)2, height-11))));
		
		for (int i=0; i<text.size(); i++) {
			text.get(i).draw(canvas);
		}
	}
}
