package utilities

import com.typesafe.scalalogging.LazyLogging
import org.apache.spark.sql.{DataFrame, SparkSession}

/**
  * When we want to execute a single function against a resource we pass the function
  * to this executor which will execute it against the session and the resource
  */
object SparkSessionDataframeExecutor extends LazyLogging {

  /**
    * Singleton SparkSession
    */
  private val spark = SparkSession.builder.appName("Spark Session").config("spark.master", "local").getOrCreate()

  /**
    * Allows us to execute an arbitrary function f against a DataFrame of a resource using the
    * current SparkSession
    *
    * @param resourcePath Path of the resource to execute against
    * @param f Function to execute
    * @tparam A Type parameter we expect to be returned from the function
    * @return Result from executing the function against the dataframe
    */
  def buildSessionExecuteFunction[A](resourcePath: String, f: DataFrame => A): A = {

    logger.info(s"Entered SparkSessionDataframeExecutor.buildSessionExecuteFunction: $resourcePath")

    val dataFrame = spark.read.option("multiline", value = true).json(resourcePath)

    val functionResult = f(dataFrame)

    logger.info(s"Exiting SparkSessionDataframeExecutor.buildSessionExecuteFunction: $functionResult")

    functionResult

  }

}
