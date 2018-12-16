package exerciseone

import com.typesafe.scalalogging.LazyLogging
import utilities.DoubleArgumentExerciseExecutor

/**
  * Discover the schema of the input dataset and output it to a file.
  */
object ExerciseOne extends LazyLogging {

  /**
    * Point of entry for the program. Expects two parameters, the first containing a
    * resource name to execute against, the second containing a filename to write against.
    *
    * @param args Array of parameters, first should be resource to execute against, second should be file to write to
    */
  def main(args: Array[String]): Unit= {

    logger.info(s"Entered ExerciseOne.main: $args")

    DoubleArgumentExerciseExecutor.execute(
      args,
      d => d.schema.treeString
    )

    logger.info(s"Exiting ExerciseOne.main: $args")

  }

}
