package exercisesix

import com.typesafe.scalalogging.LazyLogging
import org.apache.spark.sql.functions._
import utilities.DoubleArgumentExerciseExecutor

/**
  * Measure the average public consultation duration in days (i.e. the difference between PUBLICCONSULTATIONENDDATE and
  * PUBLICCONSULTATIONSTARTDATE fields). Feel free to output this to a file or standard output on the console.
  *
  * Improvements:
  *   - Check to make sure date
  *   - Assumes that column exists
  *   - Check to make sure end date not before start date
  */
object ExerciseSix extends LazyLogging {

  /**
    * Point of entry for the program. Expects two parameters, the first containing a
    * resource name to execute against, the second containing a filename to write against.
    *
    * @param args Array of parameters, first should be resource to execute against, second should be file to write to
    */
  def main(args: Array[String]): Unit= {

    logger.info(s"Entered ExerciseSix.main: $args")

    DoubleArgumentExerciseExecutor.execute(
      args,
      d => d.withColumn("PUBLICCONSULTATIONDIFFERENCE",
              datediff(to_date(col("PUBLICCONSULTATIONENDDATE"), "dd/MM/yyyy"), to_date(col("PUBLICCONSULTATIONSTARTDATE"), "dd/MM/yyyy"))
            )
            .agg(
              avg(col("PUBLICCONSULTATIONDIFFERENCE")).as("AVGPUBLICCONSULTATIONDIFFERENCE")
            )
            .collect()
            .mkString(", ")
    )

    logger.info(s"Exiting ExerciseSix.main: $args")

  }

}
