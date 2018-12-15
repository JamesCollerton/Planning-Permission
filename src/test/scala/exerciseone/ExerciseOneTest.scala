package exerciseone

import org.apache.spark.sql.AnalysisException
import org.scalatest.FunSuite

class ExerciseOneTest extends FunSuite {

  test("Given valid arguments, when derive arguments, returns correct filename") {
    assert(ExerciseOne.deriveArgs(Array("filename")) == "filename")
  }

  test("Given invalid arguments, when derive arguments, throws exception") {
    val exception = intercept[IllegalArgumentException] {
      ExerciseOne.deriveArgs(Array())
    }
    assert(exception.getMessage == "No argument for file to write to presented.")
  }

  test("Given valid basic schema, when find schema, then correct schema returned") {
    val expectedSchema = "root\n |-- age: long (nullable = true)\n |-- city: string (nullable = true)\n |-- name: string (nullable = true)\n"
    val actualSchema = ExerciseOne.findSchema("src/test/resources/data/exerciseone/valid-basic.json")
    assert(actualSchema == expectedSchema)
  }

  test("Given invalid basic schema, when find schema, then returns corrupt record") {
    val expectedSchema = "root\n |-- _corrupt_record: string (nullable = true)\n"
    val actualSchema = ExerciseOne.findSchema("src/test/resources/data/exerciseone/invalid-basic.json")
    assert(actualSchema == expectedSchema)
  }

  test("Given invalid file, when find schema, then throws exception") {
    val exception = intercept[AnalysisException] {
      val actualSchema = ExerciseOne.findSchema("no such file")
    }
    assert(exception.getMessage.contains("Path does not exist"))
  }

}
