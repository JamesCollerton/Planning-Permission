package exercisetwo

import java.io.File

import exerciseone.ExerciseOne
import org.apache.commons.io.FileUtils
import org.scalatest.FunSuite

import scala.io.Source

class ExerciseTwoTest extends FunSuite {

  test("Given valid file, when derive schema, returns correct result") {
    val testFileName = "ExerciseTwoTestFile.txt"

    ExerciseTwo.main(Array(testFileName))
    val testFileLines = Source.fromFile(testFileName).getLines.toList

    assert(testFileLines.length == 1)
    assert(testFileLines.head == 20087.toString)

    FileUtils.deleteQuietly(new File(testFileName))
  }

  test("Given valid dataset with ten records, when count records, then returns ten") {
    val count = ExerciseTwo.countRecords("src/test/resources/data/exercisetwo/valid-ten-records.json")
    assert(count == 10)
  }

  test("Given valid dataset with no records, when count records, then returns zero") {
    val count = ExerciseTwo.countRecords("src/test/resources/data/exercisetwo/valid-no-records.json")
    assert(count == 0)
  }

  test("Given invalid dataset, when count records, then counts one invalid row") {
    val count = ExerciseTwo.countRecords("src/test/resources/data/exercisetwo/invalid-basic.json")
    assert(count == 1)
  }

}
