package io.petersen

import com.pff.{ PSTObject, PSTFolder, PSTMessage }
import java.util.Vector
import scala.util.control.NonFatal
import scala.collection.immutable.StringOps

// https://github.com/rjohnsondev/java-libpst
class Processor(val folder : PSTFolder, val searchEmailAddress : String) {
  def this(folder : PSTFolder) {
    this(folder, "");
    println("\nNo search email given, printing all from addresses.")
  }

  processFolder(folder, 0);

  implicit def iteratorToWrapper[T](iter:java.util.Iterator[T]):IteratorWrapper[T] = new IteratorWrapper[T](iter)

  def processFolder(folder : PSTFolder, depth : Int) {
    // the root folder doesn't have a display name
    if(depth > 0) {
      // printDepth(depth)
      // println(folder.getDisplayName())
    }

    // go through the folders...
    if (folder.hasSubfolders()) {
      val childFolders : Vector[PSTFolder] = folder.getSubFolders()
      var childIterator = childFolders.iterator()
      for(childFolder <- childIterator) {
        processFolder(childFolder, depth+1)
      }
    }
    printEmails(folder, depth+1)
  }

  def printEmails(folder : PSTFolder, depth : Int) {
    val Pattern = "(\\s*-+Original Message-+\\s*)".r
    var count : Int = 1
    if(folder.getContentCount() > 0) {
      var email : PSTMessage = folder.getNextChild().asInstanceOf[PSTMessage]
      while (email != null) {
        if(searchEmailAddress == "") {
          // FIND EMAILS
          println(email.getSenderEmailAddress())
        } else if(email.getSenderEmailAddress() == searchEmailAddress) {
          // PRINT BODIES
          var print = true
          val stringOps = new StringOps(email.getBody())
          for(line <- stringOps.lines) {
            line match {
              case Pattern(c) => print = false
              case _ => {
                if(print) {
                  println(line)
                }
              }
            }
          }
        }
        try {
          email = folder.getNextChild().asInstanceOf[PSTMessage]
        } catch {
          case NonFatal(exc) => {
            System.err.println("Error ["+count+"] ["+folder.getContentCount()+"]")
            folder.moveChildCursorTo(count)            
          }
        }
        count = count+1
      }
    }
  }

  def printDepth(depth : Int) {
    for(a <- 1 until depth){
      print(" | ");
    }
    print(" |- ");
  }
}
