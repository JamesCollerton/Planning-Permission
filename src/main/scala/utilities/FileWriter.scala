package utilities

import java.io.{BufferedWriter, File, FileWriter}

object FileWriter {

  def write(data: String, filePath: String): Unit = {
    val file = new File(filePath)
    val bw = new BufferedWriter(new FileWriter(file))
    bw.write(data)
    bw.close()
  }

}
