package exercisefour

import java.io.File

import org.apache.commons.io.FileUtils
import org.scalatest.{BeforeAndAfter, FunSuite}

import scala.io.Source

class ExerciseFourTest extends FunSuite with BeforeAndAfter {

  private val testFileName = "ExerciseFourTestFile.txt"

  after {
    FileUtils.deleteQuietly(new File(testFileName))
  }

  test("Given valid dataset, when count 10 AGENTs, returns expected list") {

    val resourcePath = "src/main/resources/data/planning-applications-weekly-list.json"
    val expectedList = "[], [Michael Rathbone], [Mr Steven Kirk], [Mr Keith Butler], [Earle Hall], [Mr Jon Tweddell], [Mr Richard Sullivan], [Mr Kevin Doonan], [Kris Burnett], [Mr Michael Rathbone]"
    val numberAgents = 10

    actAndAssertExerciseFour(resourcePath, numberAgents, expectedList)

  }

  test("Given valid dataset, when count 0 AGENTs, returns expected list") {

    val resourcePath = "src/main/resources/data/planning-applications-weekly-list.json"
    val numberAgents = 0

    ExerciseFour.main(Array(resourcePath, testFileName, numberAgents.toString))
    val testFileLines = Source.fromFile(testFileName).getLines.toList

    assert(testFileLines.isEmpty)

  }

  test("Given valid file with less than 10 agents, when find top 10 agents, then only gets available top") {

    val resourcePath = "src/test/resources/data/exercisefour/valid-file-five-agents.json"
    val expectedList = "[pineapple], [strawberry], [banana], [kiwi], [apple]"
    val numberAgents = 10

    actAndAssertExerciseFour(resourcePath, numberAgents, expectedList)

  }

  test("Given valid file with ten rows, when find top 2 agents, then returns top 2 agents") {

    val resourcePath = "src/test/resources/data/exercisefour/valid-file-five-agents.json"
    val expectedList = "[pineapple], [banana]"
    val numberAgents = 2

    actAndAssertExerciseFour(resourcePath, numberAgents, expectedList)

  }

//  test("Given invalid file, when find top 2 agents, then throws exception") {
//
//  }

  /*
    Utilities
   */

  private def actAndAssertExerciseFour(resourcePath: String, numberAgents: Int, expectedList: String): Unit = {

    ExerciseFour.main(Array(resourcePath, testFileName, numberAgents.toString))
    val testFileLines = Source.fromFile(testFileName).getLines.toList

    assert(testFileLines.length == 1)
    assert(testFileLines.head == expectedList)

  }

}
