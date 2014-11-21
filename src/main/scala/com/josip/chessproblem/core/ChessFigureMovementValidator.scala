package com.josip.chessproblem.core

trait ChessFigureMovementValidator
{
  def isPositionValidMove(currentPosition: ChessBoardPosition, positionCandidate: ChessBoardPosition): Boolean
}