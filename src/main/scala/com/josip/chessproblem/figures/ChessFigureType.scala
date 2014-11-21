package com.josip.chessproblem.figures

import com.josip.chessproblem.core.ChessFigureMovementValidator
import com.josip.chessproblem.core.utils.Asserts
import com.josip.chessproblem.figures.helper._

object ChessFigureType extends Enumeration
{
  case class Val(displayName: String, figureMovementValidatorInstance: Option[ChessFigureMovementValidator]) extends super.Val {
    override def toString() = this.displayName

    def figureMovementValidator = {
      Asserts.argumentIsTrue(this.figureMovementValidatorInstance.nonEmpty, "figureMovementValidatorInstance must not be empty")
      this.figureMovementValidatorInstance.get
    }
  }

  type ChessFigureType = Val

  val EmptyOdd  = Val(displayName = "\u2588",            figureMovementValidatorInstance = None                         )
  val EmptyEven = Val(displayName = " ",                 figureMovementValidatorInstance = None                         )
  val King      = Val(displayName = "\033[31mK\033[39m", figureMovementValidatorInstance = Some(KingMovementValidator)  )
  val Queen     = Val(displayName = "\033[32mQ\033[39m", figureMovementValidatorInstance = Some(QueenMovementValidator) )
  val Bishop    = Val(displayName = "\033[36mB\033[39m", figureMovementValidatorInstance = Some(BishopMovementValidator))
  val Knight    = Val(displayName = "\033[33mN\033[39m", figureMovementValidatorInstance = Some(KnightMovementValidator))
  val Rook      = Val(displayName = "\033[35mR\033[39m", figureMovementValidatorInstance = Some(RookMovementValidator)  )
}

