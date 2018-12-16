package utilities

import java.io.File

import org.apache.commons.io.FileUtils
import org.scalatest.BeforeAndAfter

import scala.io.Source

class SolutionFileWriterTest extends org.scalatest.FunSuite with BeforeAndAfter {

  private val testFileName = "TestFile.txt"

  after {
    FileUtils.deleteQuietly(new File(testFileName))
  }

  test("Given file path does exist and data valid, when write file, then writes file correctly") {
    SolutionFileWriter.write("Test Data", testFileName)
    val testFileLines = Source.fromFile(testFileName).getLines.toList
    assert(testFileLines.length == 1)
    assert(testFileLines.head == "Test Data")
  }

}
