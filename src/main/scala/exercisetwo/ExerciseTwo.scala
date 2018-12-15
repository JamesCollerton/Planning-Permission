package exercisetwo

import com.typesafe.scalalogging.LazyLogging
import utilities.{ArgumentDeriver, DoubleArgumentExerciseExecutor, SolutionFileWriter, SparkSessionDataframeExecutor}

/**
  * What is the total number of planning application records in the dataset? Feel free to output this to a file or
  * standard output on the console.
  */
object ExerciseTwo extends LazyLogging {

  /**
    * Point of entry for the program. Expects a single parameter containing the filename to
    * write the results to.
    *
    * @param args Single parameter containing the filename as a string
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
