package exercisefive

import com.typesafe.scalalogging.LazyLogging
import utilities.DoubleArgumentExerciseExecutor
import org.apache.spark.sql.functions._

/**
  * Count the occurrence of each word within the case text (CASETEXT field) across all planning application records.
  * Output each word and the corresponding count to a file.
  *
  * Note, this could do things like remove punctuation but doesn't
  */
object ExerciseFive extends LazyLogging {

  /**
    * Point of entry for the program. Expects two parameters, the first containing a
    * resource name to execute against, the second containing a filename to write against.
    *
    * @param args Array of parameters, first should be resource to execute against, second should be file to write to
    */
  def main(args: Array[String]): Unit= {

    logger.info(s"Entered ExerciseFive.main: $args")

    DoubleArgumentExerciseExecutor.execute(
      args,
      d => d.withColumn("CASETEXT", explode(split(col("CASETEXT"), " ")))
              .groupBy("CASETEXT")
              .count()
              .collect()
              .mkString(", ")
    )

    logger.info(s"Exiting ExerciseFive.main: $args")

  }

}
