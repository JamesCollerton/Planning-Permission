package utilities

import com.typesafe.scalalogging.LazyLogging

/**
  * Handles all arguments supplied to the main functions of the various exercises
  */
object ArgumentDeriver extends LazyLogging {

  /**
    * Checks there are two arguments for the resource and the filename, then returns them
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

}
