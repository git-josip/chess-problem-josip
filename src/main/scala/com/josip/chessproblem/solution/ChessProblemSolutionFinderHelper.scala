package com.josip.chessproblem.solution

import com.josip.chessproblem.core.utils.Asserts
import com.josip.chessproblem.core.{ChessFigureOnBoard, ChessBoardPosition, ChessBoard}
import com.josip.chessproblem.figures.ChessFigureType._

private object ChessProblemSolutionFinderHelper
{
  def findSolution(figuresSetup: List[ChessFigureType], initialBoard: ChessBoard) : Set[ChessBoard] =
  {
    Asserts.argumentIsNotNull(figuresSetup)
    Asserts.argumentIsNotNull(initialBoard)

    placeFiguresOnBoards(
      figuresSetup  = figuresSetup,
      resultBuilder = List(initialBoard)
    )
  }

  private def placeFiguresOnBoards
  (
    figuresSetup  : List[ChessFigureType],
    resultBuilder : List[ChessBoard]
  ): Set[ChessBoard] =
  {
    figuresSetup match {
      case headFigure :: remainingFigures =>
        placeFiguresOnBoards(
          figuresSetup = remainingFigures,
          resultBuilder = placeSingleFigureOnBoards(
            placingFigure               = headFigure,
            boardsForFigurePlacingSetup = resultBuilder,
            resultBuilder               = Set.empty[ChessBoard]
          )
        )
      case Nil =>
        resultBuilder.toSet
    }
  }

  private def placeSingleFigureOnBoards
  (
    placingFigure               : ChessFigureType,
    boardsForFigurePlacingSetup : List[ChessBoard],
    resultBuilder               : Set[ChessBoard]
  ): List[ChessBoard] =
  {
    boardsForFigurePlacingSetup match {
      case board :: rest =>
        placeSingleFigureOnBoards(
          placingFigure = placingFigure,
          boardsForFigurePlacingSetup = rest,
          resultBuilder = resultBuilder ++ placeFigureOnBoard(
            placingFigure       = placingFigure,
            currentBoard        = board
          )
        )
      case Nil =>
        resultBuilder.toList
    }
  }

  private def placeFigureOnBoard
  (
    placingFigure : ChessFigureType,
    currentBoard  : ChessBoard
  ): List[ChessBoard] =
  {
    def placeFigureOnBoardFreePositions
    (
      freeBoardPositions  : List[ChessBoardPosition],
      resultBuilder       : List[ChessBoard]
    ): List[ChessBoard] =
    {
      freeBoardPositions match {
        case headFreeBoardPosition :: remainingFreeBoardPositions =>
          val figureMovedBoard = currentBoard.tryMakeFigureMove(
            ChessFigureOnBoard(
              figureType  = placingFigure,
              position    = headFreeBoardPosition
            )
          )

          placeFigureOnBoardFreePositions(
            freeBoardPositions  = remainingFreeBoardPositions,
            resultBuilder       = resultBuilder ::: figureMovedBoard
          )

        case Nil =>
          resultBuilder
      }
    }

    placeFigureOnBoardFreePositions(
      freeBoardPositions  = currentBoard.freeBoardPositions.toList,
      resultBuilder       = List.empty[ChessBoard]
    )
  }
}
