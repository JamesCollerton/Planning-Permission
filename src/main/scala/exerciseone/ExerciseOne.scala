package exerciseone

import com.typesafe.scalalogging.LazyLogging
import utilities.{ArgumentDeriver, DoubleArgumentExerciseExecutor, SolutionFileWriter, SparkSessionDataframeExecutor}

/**
  * Discover the schema of the input dataset and output it to a file.
  */
object ExerciseOne extends LazyLogging {

  /**
    * Point of entry for the program. Expects a single parameter containing the filename to
    * write the results to.
    *
    * @param args Single parameter containing the filename as a string
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
