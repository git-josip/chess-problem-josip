package com.josip.chessproblem.core

import com.josip.chessproblem.core.utils.Asserts

case class ChessBoard
(
  height              : Int,
  width               : Int,
  freeBoardPositions  : List[ChessBoardPosition],
  placedFiguresOnBoard: Set[ChessFigureOnBoard]
)
{
  Asserts.argumentIsNotNull(freeBoardPositions)
  Asserts.argumentIsNotNull(placedFiguresOnBoard)

  def asList = List(this)

  def tryMakeFigureMove(chesFigureOnBoard: ChessFigureOnBoard): List[ChessBoard] =
  {
    Asserts.argumentIsNotNull(chesFigureOnBoard)

    if(chesFigureOnBoard.isValidMoveOnChessBoard(this)) {
      this.copy(
        freeBoardPositions    = this.freeBoardPositions.filter(!chesFigureOnBoard.isPositionValidMove(_)),
        placedFiguresOnBoard  = this.placedFiguresOnBoard + chesFigureOnBoard
      ).asList
    }
    else {
      List.empty
    }
  }
}

object ChessBoard
{
  def apply(height: Int, width: Int): ChessBoard =
  {
    Asserts.argumentIsTrue(!(height < 0), "Height must not be negative.")
    Asserts.argumentIsTrue(!(width < 0), "Width must not be negative.")
    Asserts.argumentIsTrue(!(height == 0 && width == 0), "Height and Weight must not be zero at same time.")

    ChessBoard(
      width                 = width,
      height                = height,
      freeBoardPositions    = Helper.initChessBoardPositionsFor(
        height  = height,
        width   = width
      ),
      placedFiguresOnBoard  = Set.empty[ChessFigureOnBoard]
    )
  }

  private object Helper
  {
    def initChessBoardPositionsFor(height: Int, width: Int): List[ChessBoardPosition] =
    {
      val boardSquaresResult = for (
        heightPosition <- 1 to height;
        widthPosition  <- 1 to width
      ) yield ChessBoardPosition(
          x = widthPosition,
          y = heightPosition
        )

      boardSquaresResult.toList
    }
  }
}
