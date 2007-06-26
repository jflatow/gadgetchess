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

public class King extends Piece {
  
  public King(int color) {
    super(color, "/" + (color == Piece.WHITE ? "w" : "b") + "k.gif", "k");
  }
  
  public void move(int xi, int yi, int xf, int yf, Board board) {
    if (isCastle(xi, yi, xf, yf, board)) {
      int myY = (_color == Piece.WHITE) ? 7 : 0;
      int side = (xf == 6 ? 7 : 0);
      int x2 = (xf == 6 ? 5 : 3);
      board.getSquare(side, myY).getPiece().move(side, myY, x2, myY, board);
    }
    board.getSquare(xf, yf).setPiece(this);
    board.getSquare(xi, yi).removePiece();
    this._hasMoved = true;
    if (board.getGame().getTurn() == Piece.WHITE) board._wk = new int[] {xf, yf};
    else board._bk = new int[] {xf, yf};
  }
  
  public boolean canMove(int xi, int yi, int xf, int yf, Board board) {
    int dx = xf - xi;
    int dy = yf - yi;
    if ((Math.abs(dx) == 1 || Math.abs(dy) == 1) && (Math.abs(dx) + Math.abs(dy) <= 2) && board.getSquare(xf, yf).getPiece().getColor() != _color) return true;
    if (isCastle(xi, yi, xf, yf, board)) return true;
    return false;
  }
  
  private boolean isCastle(int xi, int yi, int xf, int yf, Board board) {
    if (_hasMoved) return false;
    
    int myY = (_color == Piece.WHITE) ? 7 : 0;
    if (yi != myY || yf != myY || xi != 4) return false;
    
    if (!board.isClear(xi, yi, xf, yf)) return false;
    
    Piece b;
    boolean kingside = (xf == 6);
    if (kingside) {
      b = board.getSquare(7, myY).getPiece();
      if (!board.isClear(7, myY, 5, myY)) return false;    // make sure rook is clear to move
      if (board.isCheck(4, myY, 5, myY)) return false;     // check for check in between
    } else if (xf == 2) {
      b = board.getSquare(0, myY).getPiece();
      if (!board.isClear(0, myY, 3, myY)) return false;    // make sure rook is clear to move
      if (board.isCheck(4, myY, 3, myY)) return false;     // check for check in between
    } else return false;
    if (b.getName() != "r" || b.hasMoved()) return false;
    return true;
  }
}