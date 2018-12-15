package utilities

import com.typesafe.scalalogging.LazyLogging

object ArgumentDeriver extends LazyLogging {

  /**
    * Checks there is only one argument and returns it
    *
    * @param args the arguments to check
    * @return a single argument taken from the list
    */
  def deriveFilenameArgument(args: Array[String]): String = {

    logger.info(s"Entered ArgumentDeriver.deriveArgs: $args")

    if(args.length != 1) {
      throw new IllegalArgumentException("No argument for file to write to presented.")
    }

    val fileToWriteTo = args.head

    logger.info(s"Exiting ArgumentDeriver.deriveArgs: $fileToWriteTo")

    fileToWriteTo

  }

}
