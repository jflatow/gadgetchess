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

public class Bishop extends Piece {
  
  public Bishop(int color) {
    super(color, "/" + (color == Piece.WHITE ? "w" : "b") + "b.gif", "b");
  }
  
  public boolean canMove(int xi, int yi, int xf, int yf, Board board) {
    int dx = xf - xi;
    int dy = yf - yi;
    if (Math.abs(dx) != Math.abs(dy)) return false;
    if (board.isClear(xi, yi, xf, yf)) return true;
    return false;
  }
}