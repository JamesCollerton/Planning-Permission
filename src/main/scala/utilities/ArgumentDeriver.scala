package utilities

import com.typesafe.scalalogging.LazyLogging

/**
  * Handles all arguments supplied to the main functions of the various exercises
  */
object ArgumentDeriver extends LazyLogging {

  /**
    * Checks there are two arguments for the resource and the filename, then returns them. Will throw an
    * IllegalArgumentException stopping the program if the arguments are incorrect.
    *
    * @param args The arguments to check
    * @return A tuple of arguments from the list representing the resource path and file to write to
    */
  def deriveResourceFilenameArgument(args: Array[String]): (String, String) = {

    logger.info(s"Entered ArgumentDeriver.deriveResourceFilenameArgument: $args")

    if(args.length != 2) {
      throw new IllegalArgumentException("Arguments for resource path/ file incorrect.")
    }

    val resourcePath = args.head
    val fileToWriteTo = args.tail.head

    logger.info(s"Exiting ArgumentDeriver.deriveResourceFilenameArgument: $resourcePath, $fileToWriteTo")

    (resourcePath, fileToWriteTo)

  }

  /**
    * Extracts the extra argument representing the number of agents to retrieve from the program arguments. Will throw
    * an IllegalArgumentException stopping the program if the arguments are incorrect. Will also throw a NumberFormatException
    * if the argument for the number of agents is not an integer.
    *
    * @param args Array of parameters, first should be resource to execute against, second should be file to write to
    *             third number of agents to extract
    * @return A tuple of the number of agents to extract and the remaining arguments
    */
  def deriveResourceFilenameNArgument(args: Array[String]): (Int, Array[String]) = {

    logger.info(s"Entered ArgumentDeriver.deriveResourceFilenameNArgument: $args")

    if(args.length != 3) {
      throw new IllegalArgumentException("Arguments for resource path/ file/ number of agents incorrect.")
    }

    val numberAgents = args(2).toInt

    if(numberAgents < 0) {
      throw new IllegalArgumentException("Argument for number of agents must be positive.")
    }

    val newArgs = args.take(2)

    logger.info(s"Exiting ArgumentDeriver.deriveResourceFilenameNArgument: $numberAgents, $newArgs")

    (numberAgents, newArgs)

  }

}
