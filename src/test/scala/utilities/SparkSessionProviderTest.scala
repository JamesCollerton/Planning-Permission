package utilities

import org.scalatest.FunSuite

class SparkSessionProviderTest extends FunSuite {

  test("Given valid session name provided, when build session, then returns valid session") {
    val sparkSession = SparkSessionProvider.buildSession()
    assert(sparkSession.conf.get("spark.app.name") == "Spark Session")
  }

}
