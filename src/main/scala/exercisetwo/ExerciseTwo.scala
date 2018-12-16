package exercisetwo

import com.typesafe.scalalogging.LazyLogging
import utilities.DoubleArgumentExerciseExecutor

/**
  * What is the total number of planning application records in the dataset? Feel free to output this to a file or
  * standard output on the console.
  */
object ExerciseTwo extends LazyLogging {

  /**
    * Point of entry for the program. Expects two parameters, the first containing a
    * resource name to execute against, the second containing a filename to write against.
    *
    * @param args Array of parameters, first should be resource to execute against, second should be file to write to
    */
  def main(args: Array[String]): Unit= {

    logger.info(s"Entered ExerciseTwo.main: $args")

    DoubleArgumentExerciseExecutor.execute(
      args,
      d => d.count
    )

    logger.info(s"Exiting ExerciseTwo.main: $args")

  }

}
