package com.josip.chessproblem.core.utils

object Asserts
{
  def argumentIsNotNull[T](arg: T)
  {
    if (arg == null)
      throw new IllegalArgumentException(ExceptionMessages.NULL_ARGUMENT_PASSED)
  }

  def argumentIsTrue(arg: Boolean, message: String)
  {
    argumentIsNotNullNorEmpty(message, ExceptionMessages.MESSAGE_MUST_NOT_BE_NULL_NOR_EMPTY)

    if (!arg)
      throw new IllegalArgumentException(message)
  }

  def argumentIsNotNullNorEmpty(arg: String, message: String)
  {
    if(message == null){
      throw new IllegalArgumentException(ExceptionMessages.DESCRIPTION_MUST_NOT_BE_NULL)
    }
    if(message.isEmpty){
      throw new IllegalArgumentException(ExceptionMessages.DESCRIPTION_MUST_NOT_BE_EMPTY)
    }

    if (arg == null )
      throw new IllegalArgumentException(ExceptionMessages.NULL_ARGUMENT_PASSED + addDescription(message))
    if (arg.isEmpty)
      throw new IllegalArgumentException(ExceptionMessages.EMPTY_ARGUMENT_PASSED + addDescription(message))
  }

  private object ExceptionMessages
  {
    val NULL_ARGUMENT_PASSED                  = "Null argument passed!"
    val EMPTY_ARGUMENT_PASSED                 = "Empty argument passed!"
    val DESCRIPTION_MUST_NOT_BE_NULL          = "Description must not be null"
    val DESCRIPTION_MUST_NOT_BE_EMPTY         = "Description must not be empty"
    val MESSAGE_MUST_NOT_BE_NULL_NOR_EMPTY    = "Error message must not be null nor empty"
  }

  private def addDescription(description: => String): String =
  {
    String.format(" (%s)", description)
  }

}
