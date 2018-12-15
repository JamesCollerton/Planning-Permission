package exercisetwo

import org.scalatest.FunSuite

class ExerciseTwoTest extends FunSuite {

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
