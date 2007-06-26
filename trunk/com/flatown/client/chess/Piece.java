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
import com.google.gwt.user.client.ui.HTML;

public class Piece extends HTML {
  public static final String image_url = "http://jflatow.googlepages.com";
  public static final int WHITE = 0;
  public static final int BLACK = 1;
  
  protected String _imagePath;
  protected int _color;
  protected String _name;
  protected boolean _hasMoved;
  
  public Piece() {
    this(-1, "/empty.gif", "");
  }
  
  public Piece(int color, String imagePath, String name) {
    _color = color;
    _imagePath = imagePath;
    _name = name;
    _hasMoved = false;
    this.addStyleName((_color == WHITE) ? "whitepiece" : "blackpiece");
    this.setHTML("<img src='" + image_url + _imagePath + "' alt='" + this + "' />");
    DOM.setStyleAttribute(this.getElement(), "z-index", "-2");
  }
  
  public int getColor() {
    return _color;
  }
  
  public String getName() {
    return _name;
  }
  
  public boolean hasMoved() {
    return _hasMoved;
  }
  
  public void move(int xi, int yi, int xf, int yf, Board board) {
    board.getSquare(xf, yf).setPiece(this);
    board.getSquare(xi, yi).removePiece();
    this._hasMoved = true;
  }
  
  public boolean canMove(int xi, int yi, int xf, int yf, Board board) {
    return false;
  }
  
  public String toString() {
    return (_name.equals("") ? "" : (_color == WHITE) ? "w" : "b") + _name;
  }
  
}