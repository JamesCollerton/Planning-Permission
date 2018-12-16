package exercisetwo

import java.io.File

import org.apache.commons.io.FileUtils
import org.scalatest.{BeforeAndAfter, FunSuite}

import scala.io.Source

class ExerciseTwoTest extends FunSuite with BeforeAndAfter {

  private val testFileName = "ExerciseOneTestFile.txt"

  after {
    FileUtils.deleteQuietly(new File(testFileName))
  }

  test("Given valid dataset, when count records, returns 20087") {

    val resourcePath = "src/main/resources/data/planning-applications-weekly-list.json"
    val expectedCount = 20087

    actAndAssertExerciseTwo(resourcePath, expectedCount)

  }

  test("Given valid dataset with ten records, when count records, then returns ten") {

    val resourcePath = "src/test/resources/data/exercisetwo/valid-ten-records.json"
    val expectedCount = 10

    actAndAssertExerciseTwo(resourcePath, expectedCount)

  }

  test("Given valid dataset with no records, when count records, then returns zero") {

    val resourcePath = "src/test/resources/data/exercisetwo/valid-no-records.json"
    val expectedCount = 0

    actAndAssertExerciseTwo(resourcePath, expectedCount)

  }

  test("Given invalid dataset, when count records, then counts one invalid row") {

    val resourcePath = "src/test/resources/data/exercisetwo/invalid-basic.json"
    val expectedCount = 1

    actAndAssertExerciseTwo(resourcePath, expectedCount)

  }

  /*
    Utilities
  */

  private def actAndAssertExerciseTwo(resourcePath: String, expectedCount: Long): Unit = {

    ExerciseTwo.main(Array(resourcePath, testFileName))
    val testFileLines = Source.fromFile(testFileName).getLines.toList

    assert(testFileLines.length == 1)
    assert(testFileLines.head == expectedCount.toString)

  }

}
