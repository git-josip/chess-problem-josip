package com.josip.chessproblem

import com.josip.chessproblem.figures.ChessFigureType
import com.josip.chessproblem.solution.{PrintHelper, ChessProblemSolutionFinder}

object AppExecutor extends App
{
  val taskFigureSetup = List(
    ChessFigureType.King,
    ChessFigureType.King,
    ChessFigureType.Queen,
    ChessFigureType.Queen,
    ChessFigureType.Bishop,
    ChessFigureType.Bishop,
    ChessFigureType.Knight
  )

  val chessProblemSolutionFinder = ChessProblemSolutionFinder(
    width        = 7,
    height       = 7,
    figuresSetup = taskFigureSetup
  )

  PrintHelper.printStart(taskFigureSetup)

  val startTime = System.nanoTime()
  val results = chessProblemSolutionFinder.solution
  val endTime = System.nanoTime()
  val totalTimeElapsedInMillis = (endTime - startTime) / 1000000

  PrintHelper.printResults(
    totalTimeElapsedInMillis  = totalTimeElapsedInMillis,
    results                   = results
  )

  PrintHelper.printEnd()
}
