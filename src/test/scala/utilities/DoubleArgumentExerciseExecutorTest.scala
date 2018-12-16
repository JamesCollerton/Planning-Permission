package utilities

import java.io.File

import exerciseone.ExerciseOne
import exercisetwo.ExerciseTwo
import org.apache.commons.io.FileUtils
import org.apache.spark.sql.DataFrame
import org.scalatest.{BeforeAndAfter, FunSuite}

import scala.io.Source

class DoubleArgumentExerciseExecutorTest extends FunSuite with BeforeAndAfter {

  private val testFileName = "DoubleArgumentExerciseExecutorTestFile.txt"

  after {
    FileUtils.deleteQuietly(new File(testFileName))
  }

  test("Given valid file, when derive schema, returns correct result") {

    val resourcePath = "src/main/resources/data/planning-applications-weekly-list.json"
    val expectedNumberLines = 28
    val expectedSchema = "root\n |-- AGENT: string (nullable = true)\n |-- AGENTADDRESS: string (nullable = true)\n |-- APPLICANTADDRESS: string (nullable = true)\n |-- APPLICANTNAME: string (nullable = true)\n |-- CASEDATE: string (nullable = true)\n |-- CASEOFFICER: string (nullable = true)\n |-- CASEREFERENCE: string (nullable = true)\n |-- CASETEXT: string (nullable = true)\n |-- CASEURL: string (nullable = true)\n |-- COMMITTEEAREA: string (nullable = true)\n |-- DECISION: string (nullable = true)\n |-- DECISIONTARGETDATE: string (nullable = true)\n |-- DECISIONTYPE: string (nullable = true)\n |-- DECISION_ISSUED: string (nullable = true)\n |-- EXPECTEDDECISION: string (nullable = true)\n |-- EXTRACTDATE: string (nullable = true)\n |-- LOCATIONTEXT: string (nullable = true)\n |-- ORGANISATIONLABEL: string (nullable = true)\n |-- ORGANISATIONURI: string (nullable = true)\n |-- PARISH: string (nullable = true)\n |-- PUBLICCONSULTATIONENDDATE: string (nullable = true)\n |-- PUBLICCONSULTATIONSTARTDATE: string (nullable = true)\n |-- PUBLISHERLABEL: string (nullable = true)\n |-- PUBLISHERURI: string (nullable = true)\n |-- RESPONSESAGAINST: string (nullable = true)\n |-- RESPONSESFOR: string (nullable = true)\n |-- WARD: string (nullable = true)"

    DoubleArgumentExerciseExecutor.execute(Array(resourcePath, testFileName), d => d.schema.treeString)

    val testFileLines = Source.fromFile(testFileName).getLines.toList
    val testFileContents = testFileLines.mkString("\n")

    assert(testFileLines.length == expectedNumberLines)
    assert(testFileContents == expectedSchema)

  }

  test("Given valid dataset, when count records, returns 20087") {

    val resourcePath = "src/main/resources/data/planning-applications-weekly-list.json"
    val expectedCount = 20087

    DoubleArgumentExerciseExecutor.execute(Array(resourcePath, testFileName), d => d.count)
    val testFileLines = Source.fromFile(testFileName).getLines.toList

    assert(testFileLines.length == 1)
    assert(testFileLines.head == expectedCount.toString)

  }

}
