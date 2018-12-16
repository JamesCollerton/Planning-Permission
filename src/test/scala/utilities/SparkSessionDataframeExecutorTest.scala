package utilities

import org.apache.spark.sql.functions.desc
import org.scalatest.FunSuite

class SparkSessionDataframeExecutorTest extends FunSuite {

  test("Given valid basic schema, when find schema, then correct schema returned") {
    val expectedSchema = "root\n |-- age: long (nullable = true)\n |-- city: string (nullable = true)\n |-- name: string (nullable = true)\n"
    val actualSchema = SparkSessionDataframeExecutor.buildSessionExecuteFunction(
                          "src/test/resources/data/utilities/valid-basic.json",
                          d => d.schema.treeString
                        )
    assert(actualSchema == expectedSchema)
  }

  test("Given valid dataset with ten records, when count records, then returns ten") {
    val count = SparkSessionDataframeExecutor.buildSessionExecuteFunction(
      "src/test/resources/data/utilities/valid-ten-records.json",
      d => d.count
    )
    assert(count == 10)
  }

  test("Given valid file with ten rows and three distinct case officers, when find case officers, returns correct three") {
    val caseOfficerList = SparkSessionDataframeExecutor.buildSessionExecuteFunction(
      "src/test/resources/data/utilities/valid-ten-rows-three-case-officers.json",
      d => d.select("CASEOFFICER").distinct().collect().mkString(", ")
    )
    assert(caseOfficerList == "[strawberry], [apple], [banana]")
  }

  test("Given valid file with ten rows, when find top 2 agents, then returns top 2 agents") {

    val caseOfficerList = SparkSessionDataframeExecutor.buildSessionExecuteFunction(
      "src/test/resources/data/utilities/valid-file-five-agents.json",
      d => d.groupBy("AGENT")
            .count()
            .withColumnRenamed("count", "n")
            .orderBy(desc("n"))
            .select("AGENT")
            .head(2)
            .mkString(", ")
    )
    assert(caseOfficerList == "[pineapple], [banana]")
  }

}
