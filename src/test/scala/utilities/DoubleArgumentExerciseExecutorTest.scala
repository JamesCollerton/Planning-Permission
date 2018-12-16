package utilities

import java.io.File

import org.apache.commons.io.FileUtils
import org.apache.spark.sql.functions.desc
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

  test("Given valid dataset, when filter CASEOFFICER, returns expected list") {

    val resourcePath = "src/main/resources/data/planning-applications-weekly-list.json"
    val expectedList = "[Ms Rebecca Adams], [Ms Melanie Francis], [Mrs Heather Overhead], [Mr Jeff Tweddle], [Miss Caitlin Newby], [Capita], [Mr Philip McCarthy], [Mr Daniel Puttick], [Mr Mark Ketley], [Central Registry Team], [Mr John Dowsett], [Mr Malcolm Thompson], [Mrs Margaret Telfer], [Mr Ryan Soulsby], [Mr David Rowlinson], [Mr Will Laing], [Mr Joseph Turner], [Mr John Aynsley], [Miss Rachael Watts], [Mrs Laura Tipple], [Mrs Julie Seaton], [Miss Joanne Wood], [Mrs Catherine Winsett], [Mrs Haley Marron], [Mr Neil Armstrong], [Mr Ed Harwood-Scorer], [Mrs Kate Blyth], [Mr Callum Harvey], [Mrs Frances Wilkinson], [Mr Kevin Tipple], [Ms Amber Windle], [Mrs Tamsin Wood], [Mrs Judith Murphy], [Mr Richard Whittaker], [Mrs Anna Hilditch], [Ms Elizabeth Sinnamon], [Mrs Katherine Robbie], [Mrs Jennie Adamson], [West Area Team], [Mrs Caroline Jones], [Mrs Vivienne Cartmell], [South East Area Team], [Mr Sardar Dara], [North Area Team], [Mr Tony Carter], [Householder & Others Team], [Miss Ros Duncan], [Mr Connor Willis], [Ms Erin Hynes], [Mr Graeme Robbie - Historic], [Ms Jenny Green], [Mr Bart Milburn], [Mrs Esther Ross], [Mr James Sewell], [Mrs Julie Gilmour], [Ms Caroline Grist], [Mr Ian Birkett], [Mrs Sarah Seabury], [Ms Marie Haworth], [Mr Joe Nugent], [Ms Ann Rawlinson], [Mrs Susannah Buylla], [Mr James Bellis], [Mr Craig Ross], [Mr Hubert Lam], [Mrs Hannah Nilsson], [Miss Emma Thomas], [Ms Jo-Anne McGhee], [Mr Jon Sharp], [Mr Aidan Dobinson-Booth], [Ms Sarah Brannigan], [Mr Liam Hall], [Mr Skyz Ma], [Ms Rachel Campbell], [Mr Tom Procter], [Mr Richard Laughton], [Miss Sarah Witherley], [Mr Geoff Horsman], [Miss Stephanie Forster], [Mr John Lenderyou], [Mr Tony Lowe], [Mr Chris McDonagh], [Mr Ragu Sittambalam]"

    DoubleArgumentExerciseExecutor.execute(Array(resourcePath, testFileName), d => d.select("CASEOFFICER").distinct().collect().mkString(", "))
    val testFileLines = Source.fromFile(testFileName).getLines.toList

    assert(testFileLines.length == 1)
    assert(testFileLines.head == expectedList)

  }

  test("Given valid dataset, when count 10 AGENTs, returns expected list") {

    val resourcePath = "src/main/resources/data/planning-applications-weekly-list.json"
    val expectedList = "[], [Michael Rathbone], [Mr Steven Kirk], [Mr Keith Butler], [Earle Hall], [Mr Jon Tweddell], [Mr Richard Sullivan], [Mr Kevin Doonan], [Kris Burnett], [Mr Michael Rathbone]"
    val numberAgents = 10

    DoubleArgumentExerciseExecutor.execute(
      Array(resourcePath, testFileName),
      d => d.groupBy("AGENT")
              .count()
              .withColumnRenamed("count", "n")
              .orderBy(desc("n"))
              .select("AGENT")
              .head(numberAgents)
              .mkString(", ")
    )
    val testFileLines = Source.fromFile(testFileName).getLines.toList

    assert(testFileLines.length == 1)
    assert(testFileLines.head == expectedList)

  }

}
