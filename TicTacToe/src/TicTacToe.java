import java.awt.Color;

import doodlepad.Text;

public class TicTacToe extends Board{
	private Piece[][] pieces;	

	public TicTacToe() {
		super(3,3);
		pieces = new Piece[3][3];
		putPieces();
	}
	
	public void putPieces() {
		for (int r = 0;r<pieces.length;r++) {
			for (int c =0;c<3;c++) {
				pieces[r][c] = new Piece(getPosX(c), getPosY(r), getTileWidth(), this);
			}
		}
	}
	
	public void endGame(Color co) {
		String s = "won the game!";
		if (co.equals(Color.BLUE)) {
			s = "Blue "+s;
		}
		else if (co.equals(Color.RED)) {
			s = "Red "+s;
		}
		else {
			s="TIE";
		}
		Text t = new Text(s,500,800,30);
		t.setLocation(5, 5);
		this.setEventsEnabled(false);
	}
	
	public void computerTurn() {
		 int r = (int)(Math.random()*3);
		int c = (int)(Math.random()*3);
		while (!pieces[r][c].getColor().equals(Color.GRAY)){
			 r = (int)(Math.random()*3);
			 c = (int)(Math.random()*3);
		}
		pieces[r][c].onMouseClicked(getPosX(c), getPosY(r),0);
		
		/*
		int lastX=0;
		int lastY=0;
		boolean already=false;
		if (pieces[2][0].getColor().equals(Color.GRAY)) {
			lastX=2;
			lastY=0;
		}
		else if (pieces[2][2].getColor().equals(Color.GRAY)) {
			lastX=2;
			lastY=2;
		}
		
		if (lastX!=0 || lastY!=0) {
			pieces[lastX][lastY].onMouseClicked(getPosX(lastY),getPosY(lastX),0);
			already=true;
		}
//x-1, y+1, y-1
		//row 0-2, col 0-2
		if ((lastX+1)<pieces.length && pieces[lastX+1][lastY].getColor().equals(Color.GRAY)) {
			lastX++;
		}
		else if ((lastY+1)<pieces.length && pieces[lastX][lastY+1].getColor().equals(Color.GRAY)){
			lastY++;
		}
		else if ((lastX-1)>=0 && pieces[lastX-1][lastY].getColor().equals(Color.GRAY)){
			lastX--;
		}

		else if ((lastY-1)>=0 && pieces[lastX][lastY-1].getColor().equals(Color.GRAY)) {
			lastY--;
		}
		if (!already) {
			pieces[lastX][lastY].onMouseClicked(getPosX(lastY), getPosY(lastX), 0);
		}
		*/
		
		
		//check horizontal 
		//for (int r=0;r<pieces.length;r++) {
			//for (int c=0;c<pieces[r].length;c++) {
				//if (pieces[r][c]).
			//}
		//}
	}
	
	public boolean gameOver() {
		int Cocount=0; //color pieces count
		int Rcount=0; //row color pieces count
		int Ccount=0; //column color pieces count
		int Dcount=0; //Diagonal color pieces count
		int RDcount=0; //Reverse Diagonal color pieces count
		Color temp =null;
		Color Coltemp=null;
		Color Diatemp=null;
		Color RDiatemp=null;
		//check for the amount of pieces placed on the board
		for (Piece[] row : pieces) {
			for (Piece col : row) {
				if (!col.getColor().equals(Color.GRAY)){
					Cocount++;
				}
			}
		}
		
		//check to see if any connected pieces in ROW and COLUMN
		for (int r=0;r<pieces.length;r++) {
			for (int c =0; c<pieces[r].length;c++) {
				temp = pieces[r][0].getColor();
				if (!pieces[r][c].getColor().equals(Color.GRAY) && pieces[r][c].getColor().equals(temp)) {
						Rcount++;
				}
				Coltemp= pieces[0][r].getColor();
				if (!pieces[c][r].getColor().equals(Color.GRAY)&&pieces[c][r].getColor().equals(Coltemp)) {
					Ccount++;
				}	
			}
			//return any ROW winning
			if (Cocount>=5 && Rcount==3) {
				endGame(temp);
				return true;
			}
			//return any COLUMN winning
			if (Cocount>=5 && Ccount==3) {
				endGame(Coltemp);
				return true;
			}
			Rcount=0;
			Ccount=0;
		}
		
		//check for diagonal winning
		int column=2;
		for (int n=0;n<pieces.length;n++) {
			Diatemp=pieces[0][0].getColor();
			if (!pieces[n][n].getColor().equals(Color.GRAY) && pieces[n][n].getColor().equals(Diatemp)) {
				Dcount++;
			}
			
			RDiatemp=pieces[0][2].getColor();
			if (!pieces[n][column].getColor().equals(Color.GRAY) && pieces[n][column].getColor().equals(RDiatemp)) {
					RDcount++;
			}
				column--;
			
		}
		
		if (Cocount>=5 && Dcount==3) {
			endGame(Diatemp);
			return true;
		}
		
		if (Cocount>=5 && RDcount==3) {
			endGame(RDiatemp);
			return true;
		}
		
		//a tie
		if (Cocount==9) {
			endGame(Color.GRAY);
			return true;
		}
		
		return false;
	}
}
