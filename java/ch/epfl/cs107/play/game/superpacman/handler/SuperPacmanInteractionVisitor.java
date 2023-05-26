package ch.epfl.cs107.play.game.superpacman.handler;

import ch.epfl.cs107.play.game.rpg.handler.RPGInteractionVisitor;
import ch.epfl.cs107.play.game.superpacman.actor.Bonus;
import ch.epfl.cs107.play.game.superpacman.actor.Cherry;
import ch.epfl.cs107.play.game.superpacman.actor.Diamond;
import ch.epfl.cs107.play.game.superpacman.actor.Doggo;
import ch.epfl.cs107.play.game.superpacman.actor.Ghost;
import ch.epfl.cs107.play.game.superpacman.actor.Heart;
import ch.epfl.cs107.play.game.superpacman.actor.Key;
import ch.epfl.cs107.play.game.superpacman.actor.SuperPacmanPlayer;
import ch.epfl.cs107.play.game.superpacman.actor.Weapon;

public interface SuperPacmanInteractionVisitor extends RPGInteractionVisitor{
	
	/**
	 * defines interaction with a player 
	 * @param player (SuperPacmanPlayer)
	 */
	public void interactWith(SuperPacmanPlayer player);
	
	/**
	 * defines interaction with a diamond
	 * @param diamond (Diamond)
	 */
	public void interactWith(Diamond diamond);
	
	/**
	 * defines interaction with a bonus coin
	 * @param bonus (Bonus)
	 */
	public void interactWith(Bonus bonus);
	
	/**
	 * defines interaction with a cherry
	 * @param cherry (Cherry)
	 */
	public void interactWith(Cherry cherry);
	
	/**
	 * defines interaction with a key
	 * @param key (Key)
	 */
	public void interactWith(Key key );
	
	/**
	 * defines interaction with a heart
	 * @param heart (Heart)
	 */
	public void interactWith(Heart heart);
	
	/**
	 * defines interaction with a weapon
	 * @param weapon (Weapon)
	 */
	public void interactWith(Weapon weapon);
	
	/**
	 * defines interaction with a ghost
	 * @param ghost (Ghost)
	 */
	public void interactWith(Ghost ghost);
	
	/**
	 * defines interaction with a doggo
	 * @param doog (Doggo)
	 */
	public void interactWith(Doggo doggo);

}
