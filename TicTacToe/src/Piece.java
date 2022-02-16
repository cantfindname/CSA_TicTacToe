import java.awt.Color;

import doodlepad.Oval;

public class Piece extends Oval{
	private Board board;
	private static int numPieces=0;
	
	public Piece(int x, int y, int r, Board b) {
		super(x,y,r,r,b);
		setFillColor(Color.GRAY);
		board = b;
	}
	
	public Color getColor() { return getFillColor();}
	
	public boolean equals(Object p) {
		Piece b = (Piece)p;
		return b.getColor().equals(getColor());
	}
	
	public static int getCount() {return numPieces;}
	
	public void onMouseClicked(double x, double y, int b) {
		board.onMouseClicked(x, y, b);
		if (!getColor().equals(Color.GRAY)) {
			System.out.println("invalid choice");
		}
		else {
			numPieces++;
			if (numPieces%2==0) {
				setFillColor(Color.BLUE);
			}
			else if (numPieces%2==1) {
				setFillColor(Color.RED);
			}
		}
		if (!((TicTacToe) board).gameOver()&& numPieces%2==1) {
			((TicTacToe) board).computerTurn();
		}
	}
}
