package com.flatown.client.chess;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.MouseListenerAdapter;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Widget;

public class ChessPanel extends FocusPanel {

  private Board _board;
  private AbsolutePanel _layout;
  private Label _header;
  private Game _game;
  
  public ChessPanel() {
    addListeners();
    DOM.setInnerHTML(this.getElement(), "");
    this.addStyleName("chess");
    
    _layout = new AbsolutePanel();
    _layout.addStyleName("chess");
    
    _header = new Label("Google Chess 2.0!");
    _header.addStyleName("head");
    
    _game = new Game();
    _board = _game.getBoard();
    
    AbsolutePanel numbers = new AbsolutePanel();
    AbsolutePanel letters = new AbsolutePanel();
    numbers.addStyleName("numbers");
    letters.addStyleName("letters");
    
    for (int i = 0; i < 8; i++) {
      numbers.add(new Label(Integer.toString(i+1)), 0, 10+i*25);
      letters.add(new Label(int2char(i)), i*25, 5);
    }
    
    _layout.add(_header);
    _layout.add(numbers, 0, 25);
    _layout.add(_board, 10, 25);
    _layout.add(letters, 20, 225);
    this.add(_layout);
  }

  
  protected void addListeners() {
    this.addMouseListener(new MouseListenerAdapter() {
      public void onMouseMove(Widget source, int x, int y) {
        _board.movePiece(x, y);
      }
      
      public void onMouseDown(Widget source, int x, int y) {
        DOM.setCapture(source.getElement());
        _board.grabPiece(x, y);
      }
      
      public void onMouseUp(Widget source, int x, int y) {
        DOM.releaseCapture(source.getElement());
        _board.releasePiece(x, y);
      }
    });
  }
  
  public static native String int2char(int x) /*-{
      return String.fromCharCode('a'.charCodeAt(0) + x);
   }-*/;
}