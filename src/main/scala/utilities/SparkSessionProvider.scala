package utilities

import org.apache.spark.sql.SparkSession

object SparkSessionProvider {

  def buildSession(sessionName: String): SparkSession = {
    SparkSession.builder.appName(sessionName).config("spark.master", "local").getOrCreate()
  }

}
