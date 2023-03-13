package fr.isep.tp3.piece;

import fr.isep.tp3.Cell;
import fr.isep.tp3.Chess;


public class Bishop extends Piece {

	public Bishop(boolean color) {
		super(color);
	}

	@Override
	public String name() {
		return "B"+colorName();
	}

	

}

