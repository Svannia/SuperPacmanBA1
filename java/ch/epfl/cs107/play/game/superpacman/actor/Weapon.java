package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class Weapon extends SuperPacmanCollectable {
	private Sprite sprite;
	private String icon;
	private int damage;
	DiscreteCoordinates coordinates;
	public boolean activated = false;
	
	/**
	 * Complementary Weapon constructor
	 * @param area        (Area): Owner area, not null
	 * @param orientation (Orientation): Initial orientation of the entity, not null
	 * @param position    (DiscreteCoordinate): Initial position of the entity, not null
	 */
	public Weapon(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		sprite = new Sprite("superpacman/closedChest", 1, 1.f, this);
		this.coordinates = position;
	}
	
	/**
	 * Default Weapon constructor
	 * @param area        (Area): Owner area, not null
	 * @param orientation (Orientation): Initial orientation of the entity, not null
	 * @param position    (DiscreteCoordinate): Initial position of the entity, not null
	 * @param icon (String): resource sprite of the weapon icon that will be displayed in the player's status
	 * @param damage (int): damage inflicted by the weapon
	 */
	public Weapon(Area area, Orientation orientation, DiscreteCoordinates position, String icon, int damage) {
		super(area, orientation, position);
		this.icon = ResourcePath.getSprite(icon);
		this.damage = damage;
		sprite = new Sprite("superpacman/closedChest", 1, 1.f, this);
		this.coordinates = position;
	}
	
	public void collect() {
		sprite = new Sprite("superpacman/openedChest", 1, 1.f, this);
	}
	
	/**
	 * getter for the position of the chest
	 * @return (DiscreteCoordinates): chest's location
	 */
	public DiscreteCoordinates getCoordinates() {
		return coordinates;
	}
	
	/**
	 * getter for the weapon's sprite resource
	 * @return (String): icon's sprite resource
	 */
	public String getIcon() {
		return icon;
	}
	
	/**
	 * getter for the damage the instanciated weapon can inflict
	 * @return (int): weapon's damage
	 */
	public int getDamage() {
		return damage;
	}
	
	public void draw(Canvas canvas) {
		if (coordinates!=null) sprite.draw(canvas);
	}
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((SuperPacmanInteractionVisitor)v).interactWith(this);
    }
    
	public void update(float deltaTime) {
		super.update(deltaTime);
	}

}
