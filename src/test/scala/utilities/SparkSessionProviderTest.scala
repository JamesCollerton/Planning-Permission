package utilities

import org.scalatest.FunSuite

class SparkSessionProviderTest extends FunSuite {

  test("Given valid session name provided, when build session, then returns valid session") {
    val sparkSession = SparkSessionProvider.buildSession("Test Name")
    assert(sparkSession.conf.get("spark.app.name") == "Test Name")
  }

}
