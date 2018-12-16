package utilities

import com.typesafe.scalalogging.LazyLogging
import org.apache.spark.sql.DataFrame

/**
  * A number of exercises follow the same format, read in a resource, carry out a function on
  * a DataFrame and then write the result to a file.
  */
object DoubleArgumentExerciseExecutor extends LazyLogging {

  /**
    * Expects two parameters containing the resource path and filename to write the results to, executes a
    * function against a DataFrame resource and writes it to the specified file.
    *
    * @param args First parameter represents resource path, second represents the filename
    * @param f Function to execute against the DataFrame
    * @tparam A Result type we expect from the function
    */
  def execute[A](args: Array[String], f: DataFrame => A): Unit= {

    logger.info(s"Entered DoubleArgumentExerciseExecutor.execute: $args")

    val (resourcePath, fileToWriteTo) = ArgumentDeriver.deriveResourceFilenameArgument(args)
    val result = SparkSessionDataframeExecutor.buildSessionExecuteFunction(resourcePath, f)
    SolutionFileWriter.write(result.toString, fileToWriteTo)

    logger.info(s"Exiting DoubleArgumentExerciseExecutor.execute: $args")

  }

}
