import scala.collection.mutable.ArrayBuffer

/** Simple string tokenizers
  *
  * - split1 splits the string at each of the separator characters
  * - splitM plits the string at continuous runs of the separator characters
  */
package object stringsplit {

  /** Splits a string onto substrings.
    *
    * Cuts the string into substrings and gives an iterator on them.
    * Whether the string is copied or not depends on String.substring
    */
  def split1Iter(str: String, sep: Char): Iterator[String] =
    new Iterator[String] {
      private var i = 0

      def hasNext = i <= str.length

      def next() = {
        val j = i
        while (i < str.length && str.charAt(i) != sep) {
          i += 1
        }
        val ret = str.substring(j, i)
        i = i + 1

        ret
      }

    }

  /** Splits a string onto substrings.
    *
    * Cuts the string into substrings and gives an iterator on them.
    * Whether the string is copied or not depends on String.substring
    */
  def split1Iter(str: String, sep: Set[Char]): Iterator[String] =
    new Iterator[String] {
      private var i = 0

      def hasNext = i <= str.length

      def next() = {
        val j = i
        while (i < str.length && !sep.contains(str.charAt(i))) {
          i += 1
        }
        val ret = str.substring(j, i)
        i = i + 1

        ret
      }

    }

  /** Stores all elements in the given mutable buffer. */
  def storeIterInArrayAll[T](
      iter: Iterator[T],
      destination: ArrayBuffer[T]
  ): Unit = {
    var i = 0
    while (iter.hasNext) {
      val s = iter.next

      while (destination.size < i + 1) destination += (null).asInstanceOf[T]

      destination(i) = s

      i += 1
    }
  }

  /** Stores the elements between [i,j) indices (exclusive end)  in the given mutable buffer. */
  def storeIterInArrayInterval[T](
      iter: Iterator[T],
      destination: ArrayBuffer[T],
      i: Int,
      j: Int
  ): Unit = {
    var k = 0
    while (iter.hasNext && k < j) {
      val s = iter.next

      if (k >= i && k < j) {

        while (destination.size < k + 1 - i)
          destination += (null)
            .asInstanceOf[T]

        destination(k - i) = s
      }

      k += 1
    }
  }

  def split1Array(str: String, sep: Char): Array[String] =
    split1Iter(str, sep).toArray

  def split1Array(str: String, sep: Set[Char]): Array[String] =
    split1Iter(str, sep).toArray

  def split1(str: String, sep: Char): IndexedSeq[String] =
    split1Iter(str, sep).toIndexedSeq

  def split1(str: String, sep: Set[Char]): IndexedSeq[String] =
    split1Iter(str, sep).toIndexedSeq

  /** Splits string onto substrings, while continuous spans of separators are merged.
    */
  def splitMIter(str: String, sep: Char): Iterator[String] =
    new Iterator[String] {
      var i = 0

      while (i < str.length && str.charAt(i) == sep) {
        i = i + 1
      }

      def hasNext = i < str.length

      def next() = {
        val j = i
        while (i < str.length && str.charAt(i) != sep) {
          i = i + 1
        }
        val ret = str.substring(j, i)
        while (i < str.length && str.charAt(i) == sep) {
          i = i + 1
        }

        ret
      }

    }

  def splitM(str: String, sep: Char): IndexedSeq[String] = {
    splitMIter(str, sep).toIndexedSeq
  }

  def splitM(str: String, sep: Set[Char]): IndexedSeq[String] = {
    splitMIter(str, sep).toIndexedSeq
  }

  def splitMArray(str: String, sep: Char): Array[String] = {
    splitMIter(str, sep).toArray
  }

  def splitMArray(str: String, sep: Set[Char]): Array[String] = {
    splitMIter(str, sep).toArray
  }

  def splitMIter(str: String, sep: Set[Char]): Iterator[String] =
    new Iterator[String] {
      var i = 0

      while (i < str.length && sep.contains(str.charAt(i))) {
        i = i + 1
      }

      def hasNext = i < str.length

      def next() = {
        val j = i
        while (i < str.length && !sep.contains(str.charAt(i))) {
          i = i + 1
        }
        val ret = str.substring(j, i)
        while (i < str.length && sep.contains(str.charAt(i))) {
          i = i + 1
        }

        ret
      }

    }

  implicit class StringWithFastSplit(string: String) {
    def splitMArray(s: Char): Array[String] =
      stringsplit.splitMArray(string, s)

    def splitMArray(ss: Set[Char]): Array[String] =
      stringsplit.splitMArray(string, ss)

    def splitM(s: Char): IndexedSeq[String] =
      stringsplit.splitM(string, s)

    def splitM(ss: Set[Char]): IndexedSeq[String] =
      stringsplit.splitM(string, ss)

    def splitMIter(s: Char): Iterator[String] =
      stringsplit.splitMIter(string, s)

    def splitMIter(ss: Set[Char]): Iterator[String] =
      stringsplit.splitMIter(string, ss)

    def split1(s: Char): IndexedSeq[String] =
      stringsplit.split1(string, s)

    def split1(ss: Set[Char]): IndexedSeq[String] =
      stringsplit.split1(string, ss)

    def split1Array(s: Char): Array[String] =
      stringsplit.split1Array(string, s)

    def split1Array(ss: Set[Char]): Array[String] =
      stringsplit.split1Array(string, ss)

    def split1Iter(s: Char): Iterator[String] =
      stringsplit.split1Iter(string, s)

    def split1Iter(ss: Set[Char]): Iterator[String] =
      stringsplit.split1Iter(string, ss)
  }

}
