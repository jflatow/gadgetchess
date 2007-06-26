package com.flatown.client.chess;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.MouseListenerAdapter;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.Image;

public class Rook extends Piece {
  
  public Rook(int color) {
    super(color, "/" + (color == Piece.WHITE ? "w" : "b") + "r.gif", "r");
  }
  
  public boolean canMove(int xi, int yi, int xf, int yf, Board board) {
    int dx = xf - xi;
    int dy = yf - yi;
    if (Math.abs(dx) > 0 && Math.abs(dy) > 0) return false;
    if (board.isClear(xi, yi, xf, yf)) return true;
    return false;
  }
}