package com.josip.chessproblem.figures.helper

import com.josip.chessproblem.core.{ChessBoardPosition, ChessFigureMovementValidator}
import com.josip.chessproblem.core.utils.Asserts

case object RookMovementValidator extends ChessFigureMovementValidator
{
  def isPositionValidMove(currentPosition: ChessBoardPosition, positionCandidate: ChessBoardPosition): Boolean =
  {
    Asserts.argumentIsNotNull(currentPosition)
    Asserts.argumentIsNotNull(positionCandidate)

    currentPosition.x == positionCandidate.x ||
    currentPosition.y == positionCandidate.y
  }
}