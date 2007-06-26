package com.flatown.client;

import com.flatown.client.chess.*;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Label;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class gchess implements EntryPoint {
  
  private ChessPanel _chessPanel;
  private Label _header;
  private RootPanel _container;
  
  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    _container = RootPanel.get("container");
    _chessPanel = new ChessPanel();
    _container.add(_chessPanel);
  }
}
