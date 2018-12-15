package exerciseone

import java.io.File

import org.apache.commons.io.FileUtils
import org.apache.spark.sql.AnalysisException
import org.scalatest.FunSuite

import scala.io.Source

class ExerciseOneTest extends FunSuite {

  test("Given valid file, when derive schema, returns correct result") {
    val testFileName = "ExerciseOneTestFile.txt"
    val expectedSchema = "root\n |-- AGENT: string (nullable = true)\n |-- AGENTADDRESS: string (nullable = true)\n |-- APPLICANTADDRESS: string (nullable = true)\n |-- APPLICANTNAME: string (nullable = true)\n |-- CASEDATE: string (nullable = true)\n |-- CASEOFFICER: string (nullable = true)\n |-- CASEREFERENCE: string (nullable = true)\n |-- CASETEXT: string (nullable = true)\n |-- CASEURL: string (nullable = true)\n |-- COMMITTEEAREA: string (nullable = true)\n |-- DECISION: string (nullable = true)\n |-- DECISIONTARGETDATE: string (nullable = true)\n |-- DECISIONTYPE: string (nullable = true)\n |-- DECISION_ISSUED: string (nullable = true)\n |-- EXPECTEDDECISION: string (nullable = true)\n |-- EXTRACTDATE: string (nullable = true)\n |-- LOCATIONTEXT: string (nullable = true)\n |-- ORGANISATIONLABEL: string (nullable = true)\n |-- ORGANISATIONURI: string (nullable = true)\n |-- PARISH: string (nullable = true)\n |-- PUBLICCONSULTATIONENDDATE: string (nullable = true)\n |-- PUBLICCONSULTATIONSTARTDATE: string (nullable = true)\n |-- PUBLISHERLABEL: string (nullable = true)\n |-- PUBLISHERURI: string (nullable = true)\n |-- RESPONSESAGAINST: string (nullable = true)\n |-- RESPONSESFOR: string (nullable = true)\n |-- WARD: string (nullable = true)"

    ExerciseOne.main(Array(testFileName))
    val testFileLines = Source.fromFile(testFileName).getLines.toList
    val testFileContents = testFileLines.mkString("\n")

    assert(testFileLines.length == 28)
    assert(testFileContents == expectedSchema)

    FileUtils.deleteQuietly(new File(testFileName))
  }

  test("Given valid basic schema, when find schema, then correct schema returned") {
    val expectedSchema = "root\n |-- age: long (nullable = true)\n |-- city: string (nullable = true)\n |-- name: string (nullable = true)\n"
    val actualSchema = ExerciseOne.findSchema("src/test/resources/data/exerciseone/valid-basic.json")
    assert(actualSchema == expectedSchema)
  }

  test("Given invalid basic schema, when find schema, then returns corrupt record") {
    val expectedSchema = "root\n |-- _corrupt_record: string (nullable = true)\n"
    val actualSchema = ExerciseOne.findSchema("src/test/resources/data/exerciseone/invalid-basic.json")
    assert(actualSchema == expectedSchema)
  }

  test("Given invalid file, when find schema, then throws exception") {
    val exception = intercept[AnalysisException] {
      val actualSchema = ExerciseOne.findSchema("no such file")
    }
    assert(exception.getMessage.contains("Path does not exist"))
  }

}
