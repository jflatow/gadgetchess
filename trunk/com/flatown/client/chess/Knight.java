/* Copyright (c) Jared Flatow 2007.
 *
 * You may distribute and modify this library under the terms of either
 * the Clarified Artistic License or the GNU LGPL, version 2.1 or later.
 */

package com.flatown.client.chess;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.MouseListenerAdapter;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.Image;

public class Knight extends Piece {
  
  public Knight(int color) {
    super(color, "/" + (color == Piece.WHITE ? "w" : "b") + "n.gif", "n");
  }
  
  public boolean canMove(int xi, int yi, int xf, int yf, Board board) {
    int dx = xf - xi;
    int dy = yf - yi;
    if (((Math.abs(dx) == 2 && Math.abs(dy) == 1) || 
        (Math.abs(dy) == 2 && Math.abs(dx) == 1)) && 
        board.getSquare(xf, yf).getPiece().getColor() != _color) return true;
    return false;
  }
}