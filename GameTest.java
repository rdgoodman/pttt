package polartictactoe;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

public class GameTest {

	Player player1 = new HumanPlayer(1);
	Player player2 = new HumanPlayer(2);
	Player player3 = new HumanPlayer(3);
	Player player4 = new HumanPlayer(4);
	
	Game game = new Game(3, 4, player1, player2);
	Game game2 = new Game(3,4, player3, player4);
	Node[][] gameNodes = game.getNodes();
	Node[][] gameNodes2 = game2.getNodes();

	@Test
	public void testNodeCreation() {
		game.createNodesAndNeighbors();

		System.out.println();
		System.out.println("Circles: " + game.getNumX());
		System.out.println("Lines: " + game.getNumY());

		System.out.println("Nodes:");
		for (int i = 0; i < game.getNumX(); i++) {
			for (int j = 0; j < game.getNumY(); j++) {
				System.out.print(gameNodes[i][j].toString() + "   ");
			}
			System.out.println();
		}

	}

	@Test
	public void testSetNeighbors() {
		game.createNodesAndNeighbors();
		
		
		LinkedList<Node> neighbors1 = gameNodes[1][game.getNumY() - 1]
				.getNeighbors();
		System.out.println();
		System.out.println("Neighbors for (1, 3):");
		for (Node i : neighbors1) {
			System.out.print(i.toString() + "  ");
		}
		
		System.out.println();
		LinkedList<Node> neighbors2 = gameNodes[1][0]
				.getNeighbors();
		System.out.println();
		System.out.println("Neighbors for (1, 0):");
		for (Node i : neighbors2) {
			System.out.print(i.toString() + "  ");
		}
		
		System.out.println();
		LinkedList<Node> neighbors3 = gameNodes[1][1]
				.getNeighbors();
		System.out.println();
		System.out.println("Neighbors for (1, 1):");
		for (Node i : neighbors3) {
			System.out.print(i.toString() + "  ");
		}
		
		System.out.println();
		LinkedList<Node> neighbors4 = gameNodes[0][3]
				.getNeighbors();
		System.out.println();
		System.out.println("Neighbors for (0, 3):");
		for (Node i : neighbors4) {
			System.out.print(i.toString() + "  ");
		}
		
		System.out.println();
		LinkedList<Node> neighbors5 = gameNodes[2][0]
				.getNeighbors();
		System.out.println();
		System.out.println("Neighbors for (2, 0):");
		for (Node i : neighbors5) {
			System.out.print(i.toString() + "  ");
		}
		
		System.out.println();
	}
	
	@Test
	public void testEdgeCreation(){
		game.createNodesAndNeighbors();
		
		game.move(gameNodes[2][0], player1);
		game.move(gameNodes[1][3], player1);
		game.move(gameNodes[2][1], player1);

		
		assertTrue(player1.getPlayerNum() == 1);
		assertTrue(gameNodes[2][0].getNeighbors().contains(gameNodes[2][1]));
		assertTrue(gameNodes[2][0].getPlayer() == gameNodes[2][1].getPlayer());
		
		
		System.out.println(player1.getEdges().size());
		
		for (Edge i : player1.getEdges()){
			System.out.println(i.toString());
		}	
	}
	
	@Test
	public void testMoveValidity(){
		game2.createNodesAndNeighbors();
		
		game2.move(gameNodes2[1][3], player3);
		assertTrue(game2.isValidMove(gameNodes2[1][2]));
		assertFalse(game2.isFirstMove());
		assertFalse(game2.isValidMove(gameNodes2[2][1]));
		game2.move(gameNodes2[1][0], player4);
		assertTrue(game2.isValidMove(gameNodes2[2][1]));

	}

}
