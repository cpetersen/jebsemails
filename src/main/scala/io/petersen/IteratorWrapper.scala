package io.petersen

// http://stackoverflow.com/questions/495741/iterating-over-java-collections-in-scala
class IteratorWrapper[A](iter:java.util.Iterator[A]) {
  def foreach(f: A => Unit): Unit = {
    while(iter.hasNext){
      f(iter.next)
    }
  }
}
