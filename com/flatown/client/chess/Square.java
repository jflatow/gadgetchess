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

public class Square extends AbsolutePanel {
  
  public Piece _piece;
  
  public Square(int color) {
    this.addStyleName((color == 0) ? "whitesquare" : "blacksquare");
    _piece = new Piece();
    this.add(_piece, 0, 0);
  }
  
  public Piece getPiece() {
    return _piece;
  }
  
  public boolean isEmpty() {
    return _piece.getName().equals("");
  }
  
  public void setPiece(Piece piece) {
      this.clear();
      _piece = piece;
      this.add(_piece, 0, 0);
  }
  
  public void removePiece() {
    this.setPiece(new Piece());
  }
  
}