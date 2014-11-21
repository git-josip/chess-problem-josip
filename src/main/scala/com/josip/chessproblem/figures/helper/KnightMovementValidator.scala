package com.josip.chessproblem.figures.helper

import com.josip.chessproblem.core.{ChessBoardPosition, ChessFigureMovementValidator}
import com.josip.chessproblem.core.utils.Asserts

case object KnightMovementValidator extends ChessFigureMovementValidator
{
  def isPositionValidMove(currentPosition: ChessBoardPosition, positionCandidate: ChessBoardPosition): Boolean =
  {
    Asserts.argumentIsNotNull(currentPosition)
    Asserts.argumentIsNotNull(positionCandidate)

    currentPosition == positionCandidate ||
    (
      Math.abs(currentPosition.x - positionCandidate.x) == 2 &&
      Math.abs(currentPosition.y - positionCandidate.y) == 1
    ) ||
    (
      Math.abs(currentPosition.x - positionCandidate.x) == 1 &&
      Math.abs(currentPosition.y - positionCandidate.y) == 2
    )
  }
}
