package io.petersen

import com.pff.PSTFile

object MainApplication extends App {
  if(args.length == 1) {
    val pstFile : PSTFile = new PSTFile(args(0))
    System.err.println("["+args(0)+"]")
    new Processor(pstFile.getRootFolder())
  } else if(args.length == 2) {
    val pstFile : PSTFile = new PSTFile(args(0))
    val searchEmailAddress : String = args(1)
    System.err.println("["+args(0)+"] ["+searchEmailAddress+"]")
    new Processor(pstFile.getRootFolder(), Some(searchEmailAddress))
  }
}
