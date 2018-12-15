package utilities

import org.apache.spark.sql.SparkSession

object SparkSessionProvider {

  def buildSession(): SparkSession = {
    SparkSession.builder.appName("Spark Session").config("spark.master", "local").getOrCreate()
  }

}
