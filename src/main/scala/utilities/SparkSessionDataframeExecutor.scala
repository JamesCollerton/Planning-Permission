package utilities

import com.typesafe.scalalogging.LazyLogging
import org.apache.spark.sql.DataFrame

object SparkSessionDataframeExecutor extends LazyLogging {

  private val spark = SparkSessionProvider.buildSession()

  def buildSessionExecuteFunction[A](resourcePath: String, f: DataFrame => A): A = {

    logger.info(s"Entered SparkSessionDataframeExecutor.buildSessionExecuteFunction: $resourcePath")

    val dataFrame = spark.read.option("multiline", value = true).json(resourcePath)

    val functionResult = f(dataFrame)

    logger.info(s"Exiting SparkSessionDataframeExecutor.buildSessionExecuteFunction: $functionResult")

    functionResult

  }

}
