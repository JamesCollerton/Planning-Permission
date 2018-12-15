package utilities

import java.io.{BufferedWriter, File, FileWriter}

/**
  * Handles all writing to solution files
  */
object SolutionFileWriter {

  /**
    * Given a string of data writes to the supplied file path
    *
    * @param data string we would like to write to a file
    * @param filePath file path we would like to write to
    */
  def write(data: String, filePath: String): Unit = {
    val file = new File(filePath)
    val bw = new BufferedWriter(new FileWriter(file))
    bw.write(data)
    bw.close()
  }

}
