package com.flatown.client.chess;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.MouseListenerAdapter;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.Image;

public class Queen extends Piece {
  
  public Queen(int color) {
    super(color, "/" + (color == Piece.WHITE ? "w" : "b") + "q.gif", "q");
  }
  
  public boolean canMove(int xi, int yi, int xf, int yf, Board board) {
    int dx = xf - xi;
    int dy = yf - yi;
    if (board.isClear(xi, yi, xf, yf)) return true;
    return false;
  }
}