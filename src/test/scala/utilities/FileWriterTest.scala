package utilities

import java.io.File

import org.apache.commons.io.FileUtils
import org.scalatest.BeforeAndAfter

import scala.io.Source

class FileWriterTest extends org.scalatest.FunSuite with BeforeAndAfter {

  after {
    FileUtils.deleteQuietly(new File("TestFile.txt"))
  }

  test("Given file path does exist and data valid, when write file, then writes file correclty") {
    FileWriter.write("Test Data", "TestFile.txt")
    val testFileLines = Source.fromFile("TestFile.txt").getLines.toList
    assert(testFileLines.length == 1)
    assert(testFileLines.head == "Test Data")
  }

}
