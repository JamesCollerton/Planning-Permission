package exercisetwo

import com.typesafe.scalalogging.LazyLogging
import utilities.{ArgumentDeriver, SolutionFileWriter, SparkSessionDataframeExecutor}

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

    val fileToWriteTo = ArgumentDeriver.deriveFilenameArgument(args)
    val recordCount = countRecords("src/main/resources/data/planning-applications-weekly-list.json")
    SolutionFileWriter.write(recordCount.toString, fileToWriteTo)

    logger.info(s"Exiting ExerciseTwo.main: $args")

  }

  /**
    * Counts the number of records from the supplied resource name.
    *
    * @param resourcePath path of the resource we would like to count records for
    * @return string representing the resource schema
    */
  def countRecords(resourcePath: String): Long = {

    logger.info(s"Entered ExerciseTwo.countRecords: $resourcePath")

    val count = SparkSessionDataframeExecutor.buildSessionExecuteFunction(resourcePath, d => d.count)

    logger.info(s"Exiting ExerciseTwo.countRecords: $count")

    count

  }

}
