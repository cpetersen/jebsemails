package io.petersen

import com.pff.PSTFile

object MainApplication extends App {
  for( arg <- args) {
    val pstFile : PSTFile = new PSTFile(arg)
    new Processor(pstFile.getRootFolder())
  }  
}
