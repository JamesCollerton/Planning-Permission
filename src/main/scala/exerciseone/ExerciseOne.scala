package exerciseone

import com.typesafe.scalalogging.LazyLogging
import utilities.{FileWriter, SparkSessionProvider}

/**
  * Useful tutorial: https://hortonworks.com/tutorial/setting-up-a-spark-development-environment-with-scala/
  * Useful tutorial: https://spark.apache.org/docs/latest/sql-getting-started.html
  * Useful tutorial: https://spark.apache.org/docs/latest/quick-start.html#self-contained-applications
  * When we get null in Chmod https://stackoverflow.com/questions/35652665/java-io-ioexception-could-not-locate-executable-null-bin-winutils-exe-in-the-ha
  *
  * Logging: https://stackoverflow.com/questions/978252/logging-in-scala
  * Logging: https://index.scala-lang.org/lightbend/scala-logging/scala-logging/3.9.0?target=_2.12
  *
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

    val fileToWriteTo = deriveArgs(args)
    val schema = findSchema("src/main/resources/data/planning-applications-weekly-list.json")
    FileWriter.write(schema, fileToWriteTo)

    logger.info(s"Exiting ExerciseOne.main: $args")

  }

  /**
    * Checks there is only one argument and returns it
    *
    * @param args the arguments to check
    * @return a single argument taken from the list
    */
  def deriveArgs(args: Array[String]): String = {

    logger.info(s"Entered ExerciseOne.deriveArgs: $args")

    if(args.length != 1) {
      throw new IllegalArgumentException("No argument for file to write to presented.")
    }

    val fileToWriteTo = args.head

    logger.info(s"Exiting ExerciseOne.deriveArgs: $fileToWriteTo")

    fileToWriteTo

  }

  /**
    * Derives the schema from the supplied resource name.
    *
    * @param resourcePath path of the resource we would like to derive the schema for
    * @return string representing the resource schema
    */
  def findSchema(resourcePath: String): String = {

    logger.info(s"Entered ExerciseOne.findSchema: $resourcePath")

    val spark = SparkSessionProvider.buildSession("Discover Schema")

    val dataFrame = spark.read.json(resourcePath)

    val schema = dataFrame.schema.treeString

    logger.info(s"Exiting ExerciseOne.findSchema: $schema")

    schema

  }

}
