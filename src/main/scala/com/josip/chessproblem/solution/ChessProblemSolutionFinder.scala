package com.josip.chessproblem.solution

import com.josip.chessproblem.core.ChessBoard
import com.josip.chessproblem.core.utils.Asserts
import com.josip.chessproblem.figures.ChessFigureType.ChessFigureType

case class ChessProblemSolutionFinder
(
  width       : Int,
  height      : Int,
  figuresSetup: List[ChessFigureType]
)
{
  Asserts.argumentIsNotNull(figuresSetup)

  lazy val initialBoard = ChessBoard(
    height = height,
    width  = width
  )

  lazy val solution = ChessProblemSolutionFinderHelper.findSolution(
    figuresSetup = this.figuresSetup,
    initialBoard = this.initialBoard
  )
}
