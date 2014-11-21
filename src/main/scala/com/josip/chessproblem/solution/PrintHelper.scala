package com.josip.chessproblem.solution

import java.io.PrintStream

import com.josip.chessproblem.core.{ChessBoardPosition, ChessBoard}
import com.josip.chessproblem.figures.ChessFigureType

object PrintHelper
{
  def printResults(totalTimeElapsedInMillis: Long, results: Set[ChessBoard])
  {
    val out = new PrintStream(System.out, true, "UTF-8")
    out.println("First 10 results: ")

    results.take(10).toList.zipWithIndex.map(r => printSingleBoard(r._1, r._2, out))

    out.println(s"Time elapsed: \033[32m$totalTimeElapsedInMillis\033[39m ms")
    out.println(s"Number of unique configurations: \033[32m${results.size}\033[39m")

    out.close()
  }

  private def printSingleBoard(chessBoard: ChessBoard, index: Int, out: PrintStream)
  {
    out.println(s"Board '${index + 1}'")

    val heightCoordinates = 1 to chessBoard.height
    val widthCoordinates  = 1 to chessBoard.width

    val filledBoardForPrinting = heightCoordinates.foldLeft(List.empty[List[ChessFigureType.ChessFigureType]])((resultBuilder, y) => {
      val row = widthCoordinates.foldLeft(List.empty[ChessFigureType.ChessFigureType])((rowBuilder, x) => {
        val currentPosition = ChessBoardPosition(x = x, y = y)
        val optionElement = chessBoard.placedFiguresOnBoard.find(_.position == currentPosition)
        if(optionElement.isDefined) {
          rowBuilder :+ optionElement.map(_.figureType).get
        } else {
          if((x+y) % 2 == 0) rowBuilder :+ ChessFigureType.EmptyEven
          else               rowBuilder :+ ChessFigureType.EmptyOdd
        }
      })

      resultBuilder :+ row
    })

    for (row <- filledBoardForPrinting) {
      out.println(row.mkString("\u2551", "\u2502", "\u2551"))
    }
    out.println("\n")
  }

  def printStart(taskFigureSetup: List[ChessFigureType.ChessFigureType])
  {
    println("--------------------------------------------------------------------")
    println("********** STARTED: ChessProblemSolutionFinder execution.***********")
    println("--------------------------------------------------------------------")
    println()
    println(s"FIGURES SETUP: ${taskFigureSetup.mkString(", ")}")
    println()
  }

  def printEnd()
  {
    println("--------------------------------------------------------------------")
    println("********** FINISHED: ChessProblemSolutionFinder execution.**********")
    println("--------------------------------------------------------------------")
  }
}
