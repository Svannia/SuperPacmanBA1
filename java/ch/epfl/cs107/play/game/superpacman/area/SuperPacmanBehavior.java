package ch.epfl.cs107.play.game.superpacman.area;

import java.util.ArrayList;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.AreaGraph;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.superpacman.actor.AnimatedCollectable;
import ch.epfl.cs107.play.game.superpacman.actor.Blinky;
import ch.epfl.cs107.play.game.superpacman.actor.Bonus;
import ch.epfl.cs107.play.game.superpacman.actor.Boss;
import ch.epfl.cs107.play.game.superpacman.actor.Cherry;
import ch.epfl.cs107.play.game.superpacman.actor.Diamond;
import ch.epfl.cs107.play.game.superpacman.actor.Ghost;
import ch.epfl.cs107.play.game.superpacman.actor.Heart;
import ch.epfl.cs107.play.game.superpacman.actor.Inky;
import ch.epfl.cs107.play.game.superpacman.actor.Pinky;
import ch.epfl.cs107.play.game.superpacman.actor.walls.InvisibleWall;
import ch.epfl.cs107.play.game.superpacman.actor.walls.StoneWall;
import ch.epfl.cs107.play.game.superpacman.actor.walls.Wall;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class SuperPacmanBehavior extends AreaBehavior{
	public enum SuperPacmanCellType{
		NONE (0) , // never used as real content
		WALL (-16777216) , // black
		INVISIBLE_WALL(-65281), //bright pink
		STONE_WALL (-16711681), //pure cyan
		FREE_WITH_DIAMOND (-1) , // white
		FREE_WITH_BLINKY ( -65536) , // red
		FREE_WITH_PINKY ( -157237) , // pink
		FREE_WITH_INKY ( -16724737) , // cyan
		FREE_WITH_BOSS (-16776961), //blue
		FREE_WITH_CHERRY ( -36752) , // light red
		FREE_WITH_BONUS ( -16478723) , // light blue
		FREE_WITH_HEART (-256), //yellow
		FREE_EMPTY ( -6118750) ;

		final int type;

		SuperPacmanCellType(int type){
			this.type = type;
		}
		
		/**
		 * turns a number into its corresponding cell type
		 * @param type (int): number representation of the cell type
		 * @return (SuperPacmanCellType): corresponding cell type
		 */
		public static SuperPacmanCellType toType(int type){
			for(SuperPacmanCellType ict : SuperPacmanCellType.values()){
				if(ict.type == type)
					return ict;
			}
			// When you add a new color, you can print the int value here before assign it to a type
			System.out.println(type);
			return NONE;
		}
	}
	private AreaGraph graph;
	/**
	 * Default Tuto2Behavior Constructor
	 * @param window (Window), not null
	 * @param name (String): Name of the Behavior, not null
	 */
	public SuperPacmanBehavior(Window window, String name){
		super(window, name);
		int height = getHeight();
		int width = getWidth();
		for(int y = 0; y < height; y++) {
			for (int x = 0; x < width ; x++) {
				SuperPacmanCellType color = SuperPacmanCellType.toType(getRGB(height-1-y, x));
				setCell(x,y, new SuperPacmanCell(x,y,color));
			}
		}
		graph = new AreaGraph();
		nodes(graph);
		
	}
	
	/**
	 * add in the area graph nodes for all the cells that are not walls
	 * @param graph (AreaGraph): area graph where the nodes are added
	 */
	private void nodes(AreaGraph graph) {
		SuperPacmanCellType w = SuperPacmanCellType.WALL;
		SuperPacmanCellType i = SuperPacmanCellType.INVISIBLE_WALL;
		SuperPacmanCellType s = SuperPacmanCellType.STONE_WALL;
		int height = getHeight();
		int width = getWidth();
		for (int y=0; y<height; y++) {
			for (int x=0; x<width; x++) {
				if (type(x,y)!=w && type(x,y)!=i && type(x,y)!=s) {
					boolean l = false;
					boolean r = false;
					boolean u = false;
					boolean d = false;
					if (x>0 && (type(x-1,y)!=w && type(x-1,y)!=i && type(x-1,y)!=s)) l = true;
					if (x<width-1 && (type(x+1,y)!=w && type(x+1,y)!=i && type(x+1,y)!=s)) r = true;
					if (y<height-1 && (type(x,y+1)!=w && type(x,y+1)!=i && type(x,y+1)!=s)) u = true;
					if (y>0 && (type(x,y-1)!=w && type(x,y-1)!=i && type(x,y-1)!=s)) d = true;
					graph.addNode(new DiscreteCoordinates(x,y), l, u, r, d);
				}
			}
		}
	}
	
	/**
	 * getter for a specified cell's type
	 * @param int (x): x coordinate of the cell
	 * @param int (y): y coordinate of the cell
	 * @return (SuperPacmanCellType): cell type of the tested cell
	 */
	private SuperPacmanCellType type(int x, int y) {
		return ((SuperPacmanCell)getCell(x,y)).type;
	}
	
	/**
	 * getter for the area graph
	 * @return (AreaGraph)
	 */
	public AreaGraph getGraph() {
		return graph;
	}
	
	public List<Ghost> ghosts = new ArrayList<Ghost>();
	public List<Diamond> totalDiamonds = new ArrayList<Diamond>();
	public List<AnimatedCollectable> animated = new ArrayList<AnimatedCollectable>();
	
	
	/**
	 * register all actors that can be defined by the cell type
	 * @param area (Area): area where the actors are registered 
	 */
	protected void registerActors(Area area) {
		int height = getHeight();
		int width = getWidth();
		for (int y=0; y<height; y++) {
			for (int x=0; x<width; x++) {
				if (((SuperPacmanCell)getCell(x,y)).type==SuperPacmanCellType.WALL) {
					area.registerActor(new Wall(area, new DiscreteCoordinates(x, y), wallNeighborhood(x, y)));
				}
				if (((SuperPacmanCell)getCell(x,y)).type==SuperPacmanCellType.INVISIBLE_WALL) {
					area.registerActor(new InvisibleWall(area, Orientation.DOWN, new DiscreteCoordinates(x, y)));
				}
				if (((SuperPacmanCell)getCell(x,y)).type==SuperPacmanCellType.STONE_WALL) {
					area.registerActor(new StoneWall(area, Orientation.DOWN, new DiscreteCoordinates(x, y)));
				}
				if (((SuperPacmanCell)getCell(x,y)).type==SuperPacmanCellType.FREE_WITH_DIAMOND) {
					Diamond diamond = new Diamond(area, Orientation.RIGHT,new DiscreteCoordinates(x,y));
					area.registerActor(diamond);
					totalDiamonds.add(diamond);
				}
			
				if (((SuperPacmanCell)getCell(x,y)).type==SuperPacmanCellType.FREE_WITH_CHERRY) {
					area.registerActor(new Cherry(area, Orientation.RIGHT, new DiscreteCoordinates(x,y)));
				}
				if (((SuperPacmanCell)getCell(x,y)).type==SuperPacmanCellType.FREE_WITH_BONUS) {
					Bonus bonus = new Bonus(area, Orientation.RIGHT,new DiscreteCoordinates(x,y));
					area.registerActor(bonus);
					animated.add(bonus);
				}
				if (((SuperPacmanCell)getCell(x,y)).type==SuperPacmanCellType.FREE_WITH_HEART) {
					Heart heart = new Heart(area, Orientation.RIGHT,new DiscreteCoordinates(x,y));
					area.registerActor(heart);
					animated.add(heart);
				}
				if (((SuperPacmanCell)getCell(x,y)).type==SuperPacmanCellType.FREE_WITH_BLINKY) {
					Ghost ghost = new Blinky(area, Orientation.DOWN, new DiscreteCoordinates(x,y));
					area.registerActor(ghost);
					ghosts.add(ghost);
				}
				if (((SuperPacmanCell)getCell(x,y)).type==SuperPacmanCellType.FREE_WITH_INKY) {
					Ghost ghost = new Inky(area, Orientation.DOWN, new DiscreteCoordinates(x,y));
					area.registerActor(ghost);
					ghosts.add(ghost);
				}
				if (((SuperPacmanCell)getCell(x,y)).type==SuperPacmanCellType.FREE_WITH_BOSS) {
					Ghost boss = new Boss(area, Orientation.DOWN, new DiscreteCoordinates(x,y));
					area.registerActor(boss);
					ghosts.add(boss);
				}
				if (((SuperPacmanCell)getCell(x,y)).type==SuperPacmanCellType.FREE_WITH_PINKY) {
					Ghost ghost = new Pinky(area, Orientation.DOWN, new DiscreteCoordinates(x,y));
					area.registerActor(ghost);
					ghosts.add(ghost);
				}
			}
		}
	}
	
	/**
	 * calculates the neighborhood of a wall cell type to determine the sprite it will display
	 * @param x (int): x coordinate of the cell 
	 * @param y (int): y coordinate of the cell 
	 * @return (boolean[][]): each 'case' of the two-dimensional table is a cell next to the tested cell ((1,1) is the tested cell itself) returns true if the neighboor cell is a wall
	 */
	private boolean[][] wallNeighborhood(int x, int y){
		boolean[][] neighborhood = new boolean[3][3];
		neighborhood[1][1]=true;
		
		SuperPacmanCellType wall = SuperPacmanCellType.toType(-16777216);
		boolean leftBorder = (x == 0);
		boolean rightBorder = (x == this.getWidth()-1);
		boolean upperBorder = (y == this.getHeight()-1);
		boolean lowerBorder = (y ==0);
		
		if (!leftBorder && !upperBorder && ((SuperPacmanCell)getCell(x-1,y+1)).type==wall) neighborhood[0][0]=true;
		if (!leftBorder && ((SuperPacmanCell)getCell(x-1,y)).type==wall) neighborhood[0][1]=true;
		if (!leftBorder && !lowerBorder && ((SuperPacmanCell)getCell(x-1,y-1)).type==wall) neighborhood[0][2]=true;
		if (!upperBorder && ((SuperPacmanCell)getCell(x,y+1)).type==wall) neighborhood[1][0]=true;
		if (!lowerBorder && ((SuperPacmanCell)getCell(x,y-1)).type==wall) neighborhood[1][2]=true;
		if (!rightBorder && !upperBorder && ((SuperPacmanCell)getCell(x+1,y+1)).type==wall) neighborhood[2][0]=true;
		if (!rightBorder && ((SuperPacmanCell)getCell(x+1,y)).type==wall) neighborhood[2][1]=true;
		if (!rightBorder && !lowerBorder && ((SuperPacmanCell)getCell(x+1,y-1)).type==wall) neighborhood[2][2]=true;
		
		return neighborhood;
	}
	
	/**
	 * Cell adapted to the Tuto2 game
	 */
	public class SuperPacmanCell extends AreaBehavior.Cell {
		/// Type of the cell following the enum
		private final SuperPacmanCellType type;
		
		/**
		 * Default Tuto2Cell Constructor
		 * @param x (int): x coordinate of the cell
		 * @param y (int): y coordinate of the cell
		 * @param type (EnigmeCellType), not null
		 */
		public  SuperPacmanCell(int x, int y, SuperPacmanCellType type){
			super(x, y);
			this.type = type;
		}
	
		@Override
		protected boolean canLeave(Interactable entity) {
			return true;
		}

		@Override
		protected boolean canEnter(Interactable entity) {
			if (!hasNonTraversableContent()) {
				return true;
	    	} return false;
		}

	    
		@Override
		public boolean isCellInteractable() {
			return true;
		}

		@Override
		public boolean isViewInteractable() {
			return false;
		}

		@Override
		public void acceptInteraction(AreaInteractionVisitor v) {
		}

	}

}
