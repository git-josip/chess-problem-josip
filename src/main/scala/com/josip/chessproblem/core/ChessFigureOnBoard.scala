package com.josip.chessproblem.core

import com.josip.chessproblem.core.utils.Asserts
import com.josip.chessproblem.figures.ChessFigureType

case class ChessFigureOnBoard
(
  figureType  : ChessFigureType.ChessFigureType,
  position    : ChessBoardPosition
)
{
  Asserts.argumentIsNotNull(figureType)
  Asserts.argumentIsNotNull(position)

  def isValidMoveOnChessBoard(chessBoard: ChessBoard) =
  {
    Asserts.argumentIsNotNull(chessBoard)

    chessBoard.placedFiguresOnBoard.isEmpty ||
    !chessBoard.placedFiguresOnBoard.exists(x => this.isPositionValidMove(x.position))
  }

  def isPositionValidMove(chessBoardPosition: ChessBoardPosition) =
  {
    this.figureType.figureMovementValidator.isPositionValidMove(this.position, chessBoardPosition)
  }
}

object ChessFigureOnBoard
{
  def apply(figureType: ChessFigureType.Val, x: Int, y: Int): ChessFigureOnBoard =
  {
    ChessFigureOnBoard(
      figureType = figureType,
      position   = ChessBoardPosition(x = x, y = y)
    )
  }
}
