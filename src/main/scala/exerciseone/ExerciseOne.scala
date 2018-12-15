package exerciseone

import com.typesafe.scalalogging.LazyLogging
import utilities.{ArgumentDeriver, FileWriter, SparkSessionDataframeExecutor, SparkSessionProvider}

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

    val fileToWriteTo = ArgumentDeriver.deriveFilenameArgument(args)
    val schema = findSchema("src/main/resources/data/planning-applications-weekly-list.json")
    FileWriter.write(schema, fileToWriteTo)

    logger.info(s"Exiting ExerciseOne.main: $args")

  }

  /**
    * Derives the schema from the supplied resource name.
    *
    * @param resourcePath path of the resource we would like to derive the schema for
    * @return string representing the resource schema
    */
  def findSchema(resourcePath: String): String = {

    logger.info(s"Entered ExerciseOne.findSchema: $resourcePath")

    val schema = SparkSessionDataframeExecutor.buildSessionExecuteFunction(resourcePath, d => d.schema.treeString)

    logger.info(s"Exiting ExerciseOne.findSchema: $schema")

    schema

  }

}
