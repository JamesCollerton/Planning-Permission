package exercisesix

import java.io.File

import org.apache.commons.io.FileUtils
import org.scalatest.{BeforeAndAfter, FunSuite}

import scala.io.Source

class ExerciseSixTest extends FunSuite with BeforeAndAfter {

  private val testFileName = "ExerciseSixTestFile.txt"

  after {
    FileUtils.deleteQuietly(new File(testFileName))
  }

  test("Given valid dataset, when find consultation length, returns expected list") {

    val resourcePath = "src/main/resources/data/planning-applications-weekly-list.json"
    val expectedList = "[60.921188887782534]"

    actAndAssertExerciseSix(resourcePath, expectedList)

  }

  test("Given valid input file with two days difference, when find consultation length, then return two days") {

    val resourcePath = "src/test/resources/data/exercisesix/valid-file-two-days-difference.json"
    val expectedList = "[2.0]"

    actAndAssertExerciseSix(resourcePath, expectedList)

  }

  test("Given valid input file with four days difference, when find consultation length, then return two days") {

    val resourcePath = "src/test/resources/data/exercisesix/valid-file-four-days-difference.json"
    val expectedList = "[4.0]"

    actAndAssertExerciseSix(resourcePath, expectedList)

  }

  /*
    Utilities
  */

  private def actAndAssertExerciseSix(resourcePath: String, expectedContent: String): Unit = {

    ExerciseSix.main(Array(resourcePath, testFileName))
    val testFileLines = Source.fromFile(testFileName).getLines.toList

    assert(testFileLines.length == 1)
    assert(testFileLines.head == expectedContent)

  }

}
