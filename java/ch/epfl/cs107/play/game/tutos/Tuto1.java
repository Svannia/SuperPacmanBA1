package ch.epfl.cs107.play.game.tutos;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.tutos.actor.SimpleGhost;
import ch.epfl.cs107.play.game.tutos.area.SimpleArea;
import ch.epfl.cs107.play.game.tutos.area.tuto1.Ferme;
import ch.epfl.cs107.play.game.tutos.area.tuto1.Village;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Window;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Keyboard;

public class Tuto1 extends AreaGame{

	public void createArea() {
		addArea(new Ferme());
		addArea(new Village());
	}
	
	SimpleGhost player = new SimpleGhost(new Vector(18,7), "ghost.1");
	
	public boolean begin(Window window, FileSystem fileSystem) {
		if (super.begin(window, fileSystem)) {
			createArea();
			setCurrentArea(new Village().getTitle(), true);
			registerAndCamera();
			return true;
		}
		else return false;
	}
	
	public void switchArea() {
		if (this.player.isWeak()) {
			if (this.getCurrentArea().getTitle() == "zelda/Ferme") {
				this.getCurrentArea().unregisterActor(this.player);
				setCurrentArea(new Village().getTitle(), true);
				switching();
			} else if (this.getCurrentArea().getTitle() == "zelda/Village") {
				this.getCurrentArea().unregisterActor(this.player);
				setCurrentArea(new Ferme().getTitle(), true);
				switching();
			}
		}
	}
	
	public void switching() {
		registerAndCamera();
		this.player.strenghten();
	}
	
	public void registerAndCamera() {
		this.getCurrentArea().registerActor(this.player);
		this.getCurrentArea().setViewCandidate(this.player);
	}
	
	public void end() {
	}
	public void update(float deltaTime) {
		super.update(deltaTime);
		float step=0.4f;
        Keyboard keyboard = getWindow().getKeyboard();
        Button keyUP = keyboard.get(Keyboard.UP);
        Button keyDOWN = keyboard.get(Keyboard.DOWN);
        Button keyLEFT = keyboard.get(Keyboard.LEFT);
        Button keyRIGHT = keyboard.get(Keyboard.RIGHT);
        if (keyUP.isDown()) this.player.moveUp(step);
        else if (keyDOWN.isDown()) this.player.moveDown(step);
        else if (keyLEFT.isDown()) this.player.moveLeft(step);
        else if (keyRIGHT.isDown()) this.player.moveRight(step);
        switchArea();
	}
	public String getTitle() {
		return "Tuto1";
	}
}
