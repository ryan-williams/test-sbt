package org.hammerlab.test

import java.lang.{ Long => JLong }

import com.google.common.collect.{ TreeRangeSet, Range => JRange }
import htsjdk.samtools.Cigar

object Test {
  def main(args: Array[String]): Unit = {
    val c = new Cigar()
    val r = TreeRangeSet.create[JLong]()
    r.add(JRange.closedOpen[JLong](1L, 3L))
    println(r.asRanges().size())
    println(c)
  }
}
