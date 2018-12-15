package utilities

import org.scalatest.FunSuite

class ArgumentDeriverTest extends FunSuite {

  test("Given valid arguments, when derive arguments, returns correct filename") {
    assert(ArgumentDeriver.deriveFilenameArgument(Array("filename")) == "filename")
  }

  test("Given invalid arguments, when derive arguments, throws exception") {
    val exception = intercept[IllegalArgumentException] {
      ArgumentDeriver.deriveFilenameArgument(Array())
    }
    assert(exception.getMessage == "No argument for file to write to presented.")
  }

}
