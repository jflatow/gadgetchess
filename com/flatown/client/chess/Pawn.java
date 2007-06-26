package com.flatown.client.chess;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.MouseListenerAdapter;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.Image;

public class Pawn extends Piece {
  
  public Pawn(int color) {
    super(color, "/" + (color == Piece.WHITE ? "w" : "b") + "p.gif", "p");
  }
  
  public boolean canMove(int xi, int yi, int xf, int yf, Board board) {
    int dx = xf - xi;
    int dy = yf - yi;
    int ydir = (_color == Piece.WHITE ? -1 : 1);
    if (dx == 0 && board.getSquare(xf, yf).isEmpty()) {
      if (dy == ydir) return true;
      if (dy == 2*ydir && !this.hasMoved() && board.getSquare(xf, yf - ydir).isEmpty()) return true;
    } else if (Math.abs(dx) == 1 && dy == ydir) {
      if (board.getSquare(xf, yf).isEmpty()) ; //check e.p.
      else if (board.getSquare(xf, yf).getPiece().getColor() != _color) return true;
    }
    // still needs pawn promotion somewhere
    return false;
  }
}