package exercisefour

import com.typesafe.scalalogging.LazyLogging
import utilities.{ArgumentDeriver, DoubleArgumentExerciseExecutor}
import org.apache.spark.sql.functions._

/**
  * Who are the top N agents (AGENT field) submitting the most number of applications? Allow N to be configurable and
  * output the list to a file.
  *
  * Improvements
  *   - Blank agent fields not filtered
  *   - Two agents have same number of applications will be arbitrarily chosen
  */
object ExerciseFour extends LazyLogging {

  /**
    * Point of entry for the program. Expects three parameters, the first containing a
    * resource name to execute against, the second containing a filename to write against,
    * the third the number of agents to query
    *
    * @param args Array of parameters, first should be resource to execute against, second should be file to write to, third
    *             should be the number of agents to query
    */
  def main(args: Array[String]): Unit= {

    logger.info(s"Entered ExerciseFour.main: $args")

    val (numberAgents, newArgs) = ArgumentDeriver.deriveResourceFilenameNArgument(args)

    DoubleArgumentExerciseExecutor.execute(
      newArgs,
      d => d.groupBy("AGENT")
                .count()
                .withColumnRenamed("count", "n")
                .orderBy(desc("n"))
                .select("AGENT")
                .head(numberAgents)
                .mkString(", ")
    )

    logger.info(s"Exiting ExerciseFour.main: $args")

  }

}
