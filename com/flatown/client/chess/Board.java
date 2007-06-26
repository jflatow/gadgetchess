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
import com.google.gwt.user.client.ui.AbsolutePanel;

public class Board extends AbsolutePanel {
  
  private Square[][] _squares;
  private int _startX, _startY;
  public int[] _wk, _bk;
  private Game _game;
  
  public Board(Game game) {
    this.addStyleName("board");
    _game = game;
    _wk = new int[] {4, 7};
    _bk = new int[] {4, 0};
    _startX = -1; 
    _startY = -1;
    _squares = new Square[8][8];
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        _squares[i][j] = new Square((i%2 == j%2) ? 0 : 1);
        this.add(_squares[i][j], i*25, j*25);
      }
    }
  }
  
  public Game getGame() {
    return _game;
  }
  
  public Square getSquare(int i, int j) {
    return _squares[i][j];
  }
  
  public void setSquare(int i, int j, Piece p) {
    _squares[i][j].setPiece(p);
  }
  
  public boolean isCheck(int xi, int yi, int xf, int yf) {
    int color = _squares[xi][yi].getPiece().getColor();
    Piece tmp = _squares[xf][yf].getPiece();
    _squares[xf][yf]._piece = _squares[xi][yi].getPiece();
    _squares[xi][yi]._piece = new Piece();
    int[] king = (color == Piece.WHITE) ? _wk : _bk; 
    if (_squares[xf][yf].getPiece().getName() == "k") king = new int[] {xf, yf};
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        Piece p = _squares[i][j].getPiece();
        if (p.getColor() != color && p.canMove(i, j, king[0], king[1], this)) {
          _squares[xi][yi]._piece = _squares[xf][yf].getPiece();
          _squares[xf][yf]._piece = tmp;
          return true;
        }
      }
    }
    _squares[xi][yi]._piece = _squares[xf][yf].getPiece();
    _squares[xf][yf]._piece = tmp;
    return false;
  }
  
  public boolean isClear(int xi, int yi, int xf, int yf) {
    int colora = _squares[xi][yi].getPiece().getColor(), colorb = _squares[xf][yf].getPiece().getColor();
    if (colora == colorb) return false;
    int dx = (xf - xi), dy = (yf - yi);
    if (dx != 0) dx /= Math.abs(xf - xi); 
    if (dy != 0) dy /= Math.abs(yf - yi);
    for (int i = xi+dx, j = yi+dy; inBounds(i, j); i += dx, j += dy) {
      if (i == xf && j == yf) return true;
      if (!_squares[i][j].isEmpty()) return false;
    }
    return false;
  }
  
  public void grabPiece(int x, int y) {
    int i = shiftX(x), j = shiftY(y);
    if (inBounds(i, j)) {
      _startX = i;
      _startY = j;
    DOM.setStyleAttribute(_squares[i][j].getPiece().getElement(), "z-index", "-1");
    }
  }
  
  public void movePiece(int x, int y) {
    if (!inBounds(_startX, _startY)) return;
    int endX = shiftX(x), endY = shiftY(y);
    if (inBounds(endX, endY)) {
      Piece p = _squares[_startX][_startY].getPiece();
      _squares[endX][endY].add(p);
    }
  }
  
  public void releasePiece(int x, int y) {
    if (!inBounds(_startX, _startY)) return;
    int endX = shiftX(x), endY = shiftY(y);
    Piece p = _squares[_startX][_startY].getPiece();
    DOM.setStyleAttribute(p.getElement(), "z-index", "-2");
    if (tryMove(p, _startX, _startY, endX, endY)) {
      p.move(_startX, _startY, endX, endY, this);
      _game.setTurn((_game.getTurn() == Piece.WHITE ? Piece.BLACK : Piece.WHITE));
    } else {
      _squares[_startX][_startY].setPiece(p);
    }
    _startX = -1; 
    _startY = -1;
  }
  
  public boolean tryMove(Piece p, int xi, int yi, int xf, int yf) {
    if (inBounds(xf, yf) && (xi != xf || yi != yf) && _game.getTurn() == p.getColor() &&
        p.canMove(xi, yi, xf, yf, this) && !isCheck(xi, yi, xf, yf)) return true;
    return false;
  }
  
  private int shiftX(int x) {
    x += this.getParent().getAbsoluteLeft() - this.getAbsoluteLeft();
    return (x > 0) ? x / 25 : -1;
  }
  
  private int shiftY(int y) {
    y += this.getParent().getAbsoluteTop() - this.getAbsoluteTop();
    return (y > 0) ? y / 25 : -1;
  }
  
  public boolean inBounds(int x, int y) {
    return (x >= 0 && x < 8 && y >= 0 && y < 8) ? true : false;
  }
}