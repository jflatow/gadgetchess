/* Copyright (c) Jared Flatow 2007.
 *
 * You may distribute and modify this library under the terms of either
 * the Clarified Artistic License or the GNU LGPL, version 2.1 or later.
 */

package com.flatown.client.chess;

public class Game {
  
  private Board _board;
  private int _turn;
  
  public Game() {
    _board = new Board(this);
    resetBoard();
  }
  
  public int getTurn() {
    return _turn;
  }
  
  public void setTurn(int turn) {
    _turn = turn;
  }
  
  public Board getBoard() {
    return _board;
  }
  
  public void resetBoard() {
    _turn = Piece.WHITE;
    
    _board.setSquare(0, 0, new Rook(Piece.BLACK));
    _board.setSquare(1, 0, new Knight(Piece.BLACK));
    _board.setSquare(2, 0, new Bishop(Piece.BLACK));
    _board.setSquare(3, 0, new Queen(Piece.BLACK));
    _board.setSquare(4, 0, new King(Piece.BLACK));
    _board.setSquare(5, 0, new Bishop(Piece.BLACK));
    _board.setSquare(6, 0, new Knight(Piece.BLACK));
    _board.setSquare(7, 0, new Rook(Piece.BLACK));
    
    _board.setSquare(0, 7, new Rook(Piece.WHITE));
    _board.setSquare(1, 7, new Knight(Piece.WHITE));
    _board.setSquare(2, 7, new Bishop(Piece.WHITE));
    _board.setSquare(3, 7, new Queen(Piece.WHITE));
    _board.setSquare(4, 7, new King(Piece.WHITE));
    _board.setSquare(5, 7, new Bishop(Piece.WHITE));
    _board.setSquare(6, 7, new Knight(Piece.WHITE));
    _board.setSquare(7, 7, new Rook(Piece.WHITE));
    
    for (int i = 0; i < 8; i++) {
      _board.setSquare(i, 1, new Pawn(Piece.BLACK));
      _board.setSquare(i, 6, new Pawn(Piece.WHITE)); 
    }
    
  }
}