package exercisefive

import java.io.File

import org.apache.commons.io.FileUtils
import org.scalatest.{BeforeAndAfter, FunSuite}

import scala.io.Source

class ExerciseFiveTest extends FunSuite with BeforeAndAfter {

  private val testFileName = "ExerciseFiveTestFile.txt"

  after {
    FileUtils.deleteQuietly(new File(testFileName))
  }

  test("Given valid file with five unique words, when count unique words, then returns correct result") {

    val resourcePath = "src/test/resources/data/exercisefive/valid-file-five-unique-words.json"
    val expectedList = "[strawberry,1], [kiwi,1], [apple,1], [banana,1], [pineapple,1]"

    actAndAssertExerciseFive(resourcePath, expectedList)

  }

  test("Given valid file with four unique words one duplicate, when count unique words, then returns correct result") {

    val resourcePath = "src/test/resources/data/exercisefive/valid-file-four-unique-words-one-duplicate.json"
    val expectedList = "[strawberry,2], [kiwi,1], [apple,1], [banana,1], [pineapple,1]"

    actAndAssertExerciseFive(resourcePath, expectedList)

  }

  test("Given valid file with no unique words, when count unique words, then returns blank word") {

    val resourcePath = "src/test/resources/data/exercisefive/valid-file-no-unique-words.json"
    val expectedList = "[,1]"

    actAndAssertExerciseFive(resourcePath, expectedList)

  }

  /*
    Utilities
  */

  private def actAndAssertExerciseFive(resourcePath: String, expectedContent: String): Unit = {

    ExerciseFive.main(Array(resourcePath, testFileName))
    val testFileLines = Source.fromFile(testFileName).getLines.toList
    val testFileContent = testFileLines.mkString(", ")

    assert(testFileLines.length == 1)
    assert(testFileContent == expectedContent)

  }

}
