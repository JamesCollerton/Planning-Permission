package exercisethree

import java.io.File

import exercisetwo.ExerciseTwo
import org.apache.commons.io.FileUtils
import org.scalatest.{BeforeAndAfter, FunSuite}

import scala.io.Source

class ExerciseThreeTest extends FunSuite with BeforeAndAfter {

  private val testFileName = "ExerciseOneTestFile.txt"

  after {
    FileUtils.deleteQuietly(new File(testFileName))
  }

  test("Given valid dataset, when filter CASEOFFICER, returns expected list") {

    val resourcePath = "src/main/resources/data/planning-applications-weekly-list.json"
    val expectedList = "[Ms Rebecca Adams], [Ms Melanie Francis], [Mrs Heather Overhead], [Mr Jeff Tweddle], [Miss Caitlin Newby], [Capita], [Mr Philip McCarthy], [Mr Daniel Puttick], [Mr Mark Ketley], [Central Registry Team], [Mr John Dowsett], [Mr Malcolm Thompson], [Mrs Margaret Telfer], [Mr Ryan Soulsby], [Mr David Rowlinson], [Mr Will Laing], [Mr Joseph Turner], [Mr John Aynsley], [Miss Rachael Watts], [Mrs Laura Tipple], [Mrs Julie Seaton], [Miss Joanne Wood], [Mrs Catherine Winsett], [Mrs Haley Marron], [Mr Neil Armstrong], [Mr Ed Harwood-Scorer], [Mrs Kate Blyth], [Mr Callum Harvey], [Mrs Frances Wilkinson], [Mr Kevin Tipple], [Ms Amber Windle], [Mrs Tamsin Wood], [Mrs Judith Murphy], [Mr Richard Whittaker], [Mrs Anna Hilditch], [Ms Elizabeth Sinnamon], [Mrs Katherine Robbie], [Mrs Jennie Adamson], [West Area Team], [Mrs Caroline Jones], [Mrs Vivienne Cartmell], [South East Area Team], [Mr Sardar Dara], [North Area Team], [Mr Tony Carter], [Householder & Others Team], [Miss Ros Duncan], [Mr Connor Willis], [Ms Erin Hynes], [Mr Graeme Robbie - Historic], [Ms Jenny Green], [Mr Bart Milburn], [Mrs Esther Ross], [Mr James Sewell], [Mrs Julie Gilmour], [Ms Caroline Grist], [Mr Ian Birkett], [Mrs Sarah Seabury], [Ms Marie Haworth], [Mr Joe Nugent], [Ms Ann Rawlinson], [Mrs Susannah Buylla], [Mr James Bellis], [Mr Craig Ross], [Mr Hubert Lam], [Mrs Hannah Nilsson], [Miss Emma Thomas], [Ms Jo-Anne McGhee], [Mr Jon Sharp], [Mr Aidan Dobinson-Booth], [Ms Sarah Brannigan], [Mr Liam Hall], [Mr Skyz Ma], [Ms Rachel Campbell], [Mr Tom Procter], [Mr Richard Laughton], [Miss Sarah Witherley], [Mr Geoff Horsman], [Miss Stephanie Forster], [Mr John Lenderyou], [Mr Tony Lowe], [Mr Chris McDonagh], [Mr Ragu Sittambalam]"

    actAndAssertExerciseThree(resourcePath, expectedList)

  }

  test("Given valid file with ten rows and three distinct case officers, when find case officers, returns correct three") {

    val resourcePath = "src/test/resources/data/exercisethree/valid-ten-rows-three-case-officers.json"
    val expectedList = "[strawberry], [apple], [banana]"

    actAndAssertExerciseThree(resourcePath, expectedList)

  }

  test("Given valid file with three rows and three distinct case officers, when find case officers, returns correct three") {

    val resourcePath = "src/test/resources/data/exercisethree/valid-three-rows-three-case-officers.json"
    val expectedList = "[strawberry], [apple], [banana]"

    actAndAssertExerciseThree(resourcePath, expectedList)

  }

  /*
    Utilities
   */

  private def actAndAssertExerciseThree(resourcePath: String, expectedList: String): Unit = {

    ExerciseThree.main(Array(resourcePath, testFileName))
    val testFileLines = Source.fromFile(testFileName).getLines.toList

    assert(testFileLines.length == 1)
    assert(testFileLines.head == expectedList)

  }

//  test("Given invalid file, when find case officers, throws exception") {
//
//  }

}
