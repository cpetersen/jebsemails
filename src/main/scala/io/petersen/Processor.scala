package io.petersen

import com.pff.{ PSTObject, PSTFolder, PSTMessage }
import java.util.Vector
import scala.util.control.NonFatal

class IteratorWrapper[A](iter:java.util.Iterator[A]) {
  def foreach(f: A => Unit): Unit = {
    while(iter.hasNext){
      f(iter.next)
    }
  }
}

class Processor(val folder : PSTFolder) {
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
    var count : Int = 1
    if(folder.getContentCount() > 0) {
      var email : PSTMessage = folder.getNextChild().asInstanceOf[PSTMessage]
      while (email != null) {
        // ORIGINAL
        // printDepth(depth)
        // println("From: "+email.getSubject())
        // println(email.getBody())

        // FIND EMAILS
        // println(email.getSenderEmailAddress())

        // PRINT BODIES
        if(email.getSenderEmailAddress() == "jeb@jeb.org") {
          println(email.getBody())
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
