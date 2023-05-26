package ch.epfl.cs107.play.game.superpacman.actor.gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.superpacman.actor.SuperPacmanPlayer;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class RiddleGUI extends GUI{
	
	private int number;
	
	/**
	 * Default RiddleGUI constructor
	 * @param player (SuperPacmanPlayer): 
	 */
	public RiddleGUI(SuperPacmanPlayer player) {
		number = player.collectedKey.getNumber();
	}
	
	public void draw(Canvas canvas) {
		super.draw(canvas);
		
		//65 = A, 66 = B, 67 = C, 68 = D
		
		List<TextGraphics> text0 = new ArrayList<TextGraphics>();
		
		text0.add(new TextGraphics("RIDDLE 1", 1, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)4.7, height-1.375f))));
		text0.add(new TextGraphics("Disney Movies", 0.8f, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)4, height-3))));
		text0.add(new TextGraphics("Who is the villain no one", 0.8f, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)3, height-5))));
		text0.add(new TextGraphics("likes no matter what ?", 0.8f, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)3.5, height-6))));
		text0.add(new TextGraphics("A : The hunter who killed Bambi's mom", 0.5f, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)2, height-8))));
		text0.add(new TextGraphics("B : Captain Hook, from Peter Pan", 0.5f, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)2, height-9))));
		text0.add(new TextGraphics("C : Maleficent, from Sleeping Beauty", 0.5f, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)2, height-10))));
		text0.add(new TextGraphics("D : Anton Ego Ego, culinary critic in Ratatouille", 0.5f, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)2, height-11))));
		
		
		List<TextGraphics> text1 = new ArrayList<TextGraphics>();
		
		text1.add(new TextGraphics("RIDDLE 2", 1, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)4.7, height-1.375f))));
		text1.add(new TextGraphics("Philosophy", 0.8f, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)4.7, height-3))));
		text1.add(new TextGraphics("What is the 'Answer to the", 0.8f, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)2, height-5))));
		text1.add(new TextGraphics("Ultimate Question of Life, ", 0.8f, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)2, height-6))));
		text1.add(new TextGraphics("the Universe and Everything' ?", 0.8f, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)2, height-7))));
		text1.add(new TextGraphics("A : Void", 0.5f, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)2, height-9))));
		text1.add(new TextGraphics("B : Idk man, I only have 3 neurones left", 0.5f, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)2, height-10))));
		text1.add(new TextGraphics("C : 42", 0.5f, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)2, height-11))));
		text1.add(new TextGraphics("D : Study at the EPFL in IC", 0.5f, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)2, height-12))));
		
		List<TextGraphics> text2 = new ArrayList<TextGraphics>();
		
		text2.add(new TextGraphics("RIDDLE 3", 1, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)4.7, height-1.375f))));
		text2.add(new TextGraphics("Harry Potter", 0.8f, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)4.5, height-3))));
		text2.add(new TextGraphics("How do you officially get to", 0.8f, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)2.2, height-5))));
		text2.add(new TextGraphics("Hogwarts ?", 0.8f, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)4.7, height-6))));
		text2.add(new TextGraphics("A : Magical platform in London King's Cross Station", 0.5f, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)2, height-8))));
		text2.add(new TextGraphics("B : Knight bus", 0.5f, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)2, height-9))));
		text2.add(new TextGraphics("C : Hidden platform in London's Central Station", 0.5f, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)2, height-10))));
		text2.add(new TextGraphics("D : Fly your dad's car", 0.5f, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)2, height-11))));
		
		List<TextGraphics> text3 = new ArrayList<TextGraphics>();
		
		text3.add(new TextGraphics("RIDDLE 4", 1, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)4.7, height-1.375f))));
		text3.add(new TextGraphics("Anime", 0.8f, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)5.3, height-3))));
		text3.add(new TextGraphics("Who is a Shinigami who likes", 0.8f, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)1.8, height-5))));
		text3.add(new TextGraphics("to eat apples ?", 0.8f, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)4.4, height-6))));
		text3.add(new TextGraphics("A : Ichigo Kurosaki, from Bleach", 0.5f, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)2, height-8))));
		text3.add(new TextGraphics("B : Death the Kid, from Soul Eater", 0.5f, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)2, height-9))));
		text3.add(new TextGraphics("C : Grell Sutcliff, from Black Butler", 0.5f, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)2, height-10))));
		text3.add(new TextGraphics("D : Ryuk, from Death Note", 0.5f, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)2, height-11))));
		
		List<TextGraphics> text4 = new ArrayList<TextGraphics>();
		
		text4.add(new TextGraphics("RIDDLE 5", 1, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)4.7, height-1.375f))));
		text4.add(new TextGraphics("Quantum Physics", 0.8f, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)3.5, height-3))));
		text4.add(new TextGraphics("Which of the following", 0.8f, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)2.5, height-5))));
		text4.add(new TextGraphics("is false ?", 0.8f, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)4.8, height-6))));
		text4.add(new TextGraphics("A : Electrons are probability waves", 0.5f, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)2, height-8))));
		text4.add(new TextGraphics("B : Linked electrons have the same spin", 0.5f, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)2, height-9))));
		text4.add(new TextGraphics("C : An electron's precise location is unknown until", 0.5f, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)2, height-10))));
		text4.add(new TextGraphics("it is measured", 0.5f, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)2, height-11))));
		text4.add(new TextGraphics("D : Electrons leap between orbitals without passing", 0.5f, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)2, height-12))));
		text4.add(new TextGraphics("through the space in between", 0.5f, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)2, height-13))));

		
		List<TextGraphics> text5 = new ArrayList<TextGraphics>();
		
		text5.add(new TextGraphics("RIDDLE 6", 1, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)4.7, height-1.375f))));
		text5.add(new TextGraphics("Music", 0.8f, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)5.3, height-3))));
		text5.add(new TextGraphics("'Boom Boom Boom Boom,", 0.8f, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)2, height-5))));
		text5.add(new TextGraphics(" I want ", 0.8f, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)5, height-6))));
		text5.add(new TextGraphics("A : to break free from exams'", 0.5f, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)2, height-8))));
		text5.add(new TextGraphics("B : nutella on my bread'", 0.5f, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)2, height-9))));
		text5.add(new TextGraphics("C : you in my room'", 0.5f, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)2, height-10))));
		text5.add(new TextGraphics("D : no errors in my code'", 0.5f, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.05f, true, true, anchor.add(new Vector((float)2, height-11))));
		
		switch(number) {
		case 0:
			for (int i=0; i<text0.size(); i++) {
				text0.get(i).draw(canvas);
			}
			break;
		case 1:
			for (int i=0; i<text1.size(); i++) {
				text1.get(i).draw(canvas);
			}
			break;
		case 2:
			for (int i=0; i<text2.size(); i++) {
				text2.get(i).draw(canvas);
			}
			break;
		case 3:
			for (int i=0; i<text3.size(); i++) {
				text3.get(i).draw(canvas);
			}
			break;
		case 4:
			for (int i=0; i<text4.size(); i++) {
				text4.get(i).draw(canvas);
			}
			break;
		case 5:
			for (int i=0; i<text5.size(); i++) {
				text5.get(i).draw(canvas);
			}
			break;
		}
	}
}