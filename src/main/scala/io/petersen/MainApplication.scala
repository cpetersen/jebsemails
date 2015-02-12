package io.petersen

import com.pff.PSTFile

object MainApplication extends App {
  for( arg <- args) {
    println(arg)
  }  
  // val pstFile : PSTFile = new PSTFile("files/1999.pst")
  // println(pstFile.getMessageStore().getDisplayName())
  // new Processor(pstFile.getRootFolder())
}
