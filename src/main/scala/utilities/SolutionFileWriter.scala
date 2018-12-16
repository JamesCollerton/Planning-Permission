package utilities

import java.io.{BufferedWriter, File, FileWriter}

/**
  * Handles all writing to solution files. Technically capable of handling all file
  * writing, but as all we are interested in is solutions files has been named thus.
  * I recognise that this most likely isn't the best way to write files using Spark.
  */
object SolutionFileWriter {

  /**
    * Given a string of data writes to the supplied file path.
    *
    * @param data String we would like to write to a file
    * @param filePath File path we would like to write to
    */
  def write(data: String, filePath: String): Unit = {
    val file = new File(filePath)
    val bw = new BufferedWriter(new FileWriter(file))
    bw.write(data)
    bw.close()
  }

}
