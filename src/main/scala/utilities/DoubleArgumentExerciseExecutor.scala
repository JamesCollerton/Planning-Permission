package utilities

import com.typesafe.scalalogging.LazyLogging
import org.apache.spark.sql.DataFrame

object DoubleArgumentExerciseExecutor extends LazyLogging {

  /**
    * Expects two parameters containing the resource path and filename to write the results to, executes a
    * function against a dataframe resource and writes it to the specified file.
    *
    * @param args first parameter represents resource path, second represents the filename
    * @param f function to execute against the dataframe
    * @tparam A result type we expect from the function
    */
  def execute[A](args: Array[String], f: DataFrame => A): Unit= {

    logger.info(s"Entered DoubleArgumentExerciseExecutor.execute: $args")

    val (resourcePath, fileToWriteTo) = ArgumentDeriver.deriveResourceFilenameArgument(args)
    val result = SparkSessionDataframeExecutor.buildSessionExecuteFunction(resourcePath, f)
    SolutionFileWriter.write(result.toString, fileToWriteTo)

    logger.info(s"Exiting DoubleArgumentExerciseExecutor.execute: $args")

  }

}
