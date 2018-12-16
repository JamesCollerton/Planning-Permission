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

  test("Given N argument which is not an integer, when extract N argument, then throws exception") {
    val exception = intercept[NumberFormatException] {
      val (numberAgents, newArgs) = ArgumentDeriver.deriveResourceFilenameNArgument(Array("resourcePath", "filename", "notInteger"))
    }
    assert(exception.getMessage == "For input string: \"notInteger\"")
  }

  test("Given N argument which is a positive integer, when extract N argument, then returns correct integer") {
    val (numberAgents, newArgs) = ArgumentDeriver.deriveResourceFilenameNArgument(Array("resourcePath", "filename", "10"))
    assert(numberAgents == 10)
    assert(newArgs.sameElements(Array("resourcePath", "filename")))
  }

  test("Given N argument which is a negative integer, when extract N argument, then throws exception") {
    val exception = intercept[IllegalArgumentException] {
      ArgumentDeriver.deriveResourceFilenameNArgument(Array("resourcePath", "filename", "-10"))
    }
    assert(exception.getMessage == "Argument for number of agents must be positive.")
  }

  test("Given incorrect number of arguments, when extract N argument, then throws exception") {
    val exception = intercept[IllegalArgumentException] {
      ArgumentDeriver.deriveResourceFilenameNArgument(Array("resourcePath", "filename"))
    }
    assert(exception.getMessage == "Arguments for resource path/ file/ number of agents incorrect.")
  }

}
