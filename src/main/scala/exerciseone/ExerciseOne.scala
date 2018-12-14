package exerciseone

import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Useful tutorial: https://hortonworks.com/tutorial/setting-up-a-spark-development-environment-with-scala/
  * Useful tutorial: https://spark.apache.org/docs/latest/sql-getting-started.html
  * Useful tutorial: https://spark.apache.org/docs/latest/quick-start.html#self-contained-applications
  * When we get null in Chmod https://stackoverflow.com/questions/35652665/java-io-ioexception-could-not-locate-executable-null-bin-winutils-exe-in-the-ha
  *
  *
  * Discover the schema of the input dataset and output it to a file.
  */
object ExerciseOne {

  def main(args: Array[String]): Unit= {

    System.setProperty("hadoop.home.dir", "C:\\Program Files\\Hadoop")

    val spark = SparkSession.builder.appName("Discover Schema").config("spark.master", "local").getOrCreate()

    val planningApplicationsWeekly = spark.read.json("src/main/resources/planning-applications-weekly-list.json")

    planningApplicationsWeekly.schema.treeString

  }

}
