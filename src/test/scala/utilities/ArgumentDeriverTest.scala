package utilities

import org.scalatest.FunSuite

class ArgumentDeriverTest extends FunSuite {

  test("Given valid arguments, when derive arguments, returns correct filename") {
    val (resourcePath, fileName) = ArgumentDeriver.deriveResourceFilenameArgument(Array("resourcePath", "filename"))
    assert(resourcePath == "resourcePath")
    assert(fileName == "filename")
  }

  test("Given invalid arguments, when derive arguments, throws exception") {
    val exception = intercept[IllegalArgumentException] {
      ArgumentDeriver.deriveResourceFilenameArgument(Array())
    }
    assert(exception.getMessage == "Arguments for resource path/ file incorrect.")
  }

}
