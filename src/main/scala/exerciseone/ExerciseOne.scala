package exerciseone

import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Useful tutorial: https://hortonworks.com/tutorial/setting-up-a-spark-development-environment-with-scala/
  * Useful tutorial: https://spark.apache.org/docs/latest/sql-getting-started.html
  * When we get null in Chmod https://stackoverflow.com/questions/40764807/null-entry-in-command-string-exception-in-saveastextfile-on-pyspark
  *
  *
  * Discover the schema of the input dataset and output it to a file.
  */
object ExerciseOne {

  def main(args: Array[String]): Unit= {

    val spark = SparkSession.builder.appName("Discover Schema").getOrCreate()

    spark.read.json("src/main/resources/planning-applications-weekly-list.json")


  }

}
