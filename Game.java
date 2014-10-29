package polartictactoe;

public class Game {

	// TODO: both should be gotten through command-line input
	// number of circles
	private int numX;
	// number of radial lines
	private int numY;
	// players; types of which gotten through command line
	// TODO: AI player should be assigned to p1 or p2 randomly?
	Player pX;
	Player pO;
	boolean firstMove;
	Node[][] nodes;

	// TODO: finish constructor
	public Game(int numX, int numY, Player pX, Player pO) {
		this.numX = numX;
		this.numY = numY;
		this.pX = pX;
		this.pO = pO;

		firstMove = true;
		nodes = new Node[numX][numY];
	}

	/** Creates Nodes for the graph, all initialized to empty */
	public void createNodesAndNeighbors() {
		// TODO: finish
		// create edge nodes
		for (int y = 0; y < numY; y++) {
			nodes[numX - 1][y] = new Node(numX - 1, y);
			nodes[0][y] = new Node(0, y);
		}
		// create inner nodes
		for (int y = 0; y < numY; y++) {
			for (int x = 1; x < numX - 1; x++) {
				nodes[x][y] = new Node(x, y);
			}
		}
		
		// sets all neighbors
		for (int i = 0; i < numX; i++){
			for(int j = 0; j < numY; j++){
				setNeighbors(nodes[i][j]);
			}
		}
	}

	/** Sets neighbors for a node in the graph */
	public void setNeighbors(Node a) {
		int counterclockwise = a.getY() - 1;
		int clockwise = a.getY() + 1;

		// special case: on "nth" radial line of grid
		if (a.getY() == (numY - 1)) {
			clockwise = 0;
		}

		// special case: on "zeroth" radial line of grid
		if (a.getY() == 0) {
			counterclockwise = numY - 1;
		}

		// for all
		a.addNeighbor(nodes[a.getX()][clockwise]);
		a.addNeighbor(nodes[a.getX()][counterclockwise]);
		// for those not on outermost arc
		if (a.getX() != (numX - 1)) {
			a.addNeighbor(nodes[a.getX() + 1][a.getY()]);
			a.addNeighbor(nodes[a.getX() + 1][counterclockwise]);
			a.addNeighbor(nodes[a.getX() + 1][clockwise]);

		}
		// for those not on innermost arc
		if (a.getX() != 0) {
			a.addNeighbor(nodes[a.getX() - 1][a.getY()]);
			a.addNeighbor(nodes[a.getX() - 1][counterclockwise]);
			a.addNeighbor(nodes[a.getX() - 1][clockwise]);
		}

	}

	public boolean isValidMove(Node chosen) {
		if (firstMove == true){
			System.out.println("It's the first move");
			return true;
		}
		
		// TODO: call this before move() using user/AI input
		for (Node i: chosen.getNeighbors()){
			if (i.getPlayer() != 0){
				// returns true if the node has a neighbor that is played
				System.out.println("Played neighbor: " + i.toString());
				return true;
			}
		}
		// all neighbors are blank/unplayed
		return false;
	}

	/**
	 * Modifies the board state with a specific move, which MUST be evaluated to
	 * be valid before being passed in as an argument
	 */
	public void move(Node changed, Player currentPlayer) {
		if (firstMove == true) {
			firstMove = false;
		}
		// sets the Node to belong to the current player
		changed.setPlayer(currentPlayer.getPlayerNum());

		// create and add any new edges as applicable
		for (Node i : changed.getNeighbors()) {
			if (i.getPlayer() == currentPlayer.getPlayerNum()) {
				Edge possibleNewEdge = new Edge(changed, i, changed.getPlayer());
				if (!currentPlayer.hasEdge(possibleNewEdge)) {
					currentPlayer.addEdge(possibleNewEdge);
				}
			}
		}

	}

	public boolean checkIfWin() {
		// TODO: all this
		return false;
	}

	public void draw() {
		// does nothing I'm involved with
	}

	/**
	 * 
	 * 
	 * 
	 * 
	 * Below here be constructors
	 * 
	 * 
	 * 
	 * 
	 * */

	public int getNumX() {
		return numX;
	}

	public void setNumX(int numX) {
		this.numX = numX;
	}

	public int getNumY() {
		return numY;
	}

	public void setNumY(int numY) {
		this.numY = numY;
	}

	public Node[][] getNodes() {
		return nodes;
	}
	
	public boolean isFirstMove(){
		return firstMove;
	}

}
