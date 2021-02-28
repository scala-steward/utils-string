import org.scalatest.funsuite.{AnyFunSuite => FunSuite}
import scala.io.Source

import stringsplit._

class FastSplitSeparatorTestSuite extends FunSuite {

  test("fastSplitSeparator") {
    assertResult(IndexedSeq("1", "2", "3")) { splitM("1,2,3", ',') }
    assertResult(IndexedSeq("1", "2", "3")) { splitM("1,2,,,,3", ',') }
    assertResult(IndexedSeq("1", "2", "3")) { splitM(",,,1,,2,,,3,,,", ',') }
    assertResult(IndexedSeq("1", "2", "3")) {
      splitM("\n\n\n1\n\n2\n\n\n3\n\n\n", '\n')
    }
    assertResult(IndexedSeq("1", "2", "3")) {
      splitM("\n\n\n1\n\n2\n\n\n3\n\n\n", Set('\n', '\t'))
    }
    assertResult(IndexedSeq("1", "2", "3")) { splitM(",1,2,3", ',') }
    assertResult(IndexedSeq("1", "2", "3")) {
      splitM(",,,,,,1,,,,,2,,,,,3,,,,,,", ',')
    }
    assertResult(IndexedSeq("1", "2", "3")) {
      splitM("1,,,,,,,,,,,2,,,,,,,,,,,3", ',')
    }
    assertResult(IndexedSeq()) { splitM("", ',') }
    assertResult(IndexedSeq()) { splitM(",,,,,", ',') }
    assertResult(IndexedSeq()) { splitM(",,,,,", Set(',')) }
    assertResult(IndexedSeq()) { splitM(",", ',') }
    assertResult(IndexedSeq("1")) { splitM(",1", ',') }
    assertResult(IndexedSeq("1")) { splitM("1,", ',') }
    assertResult(IndexedSeq("  1")) { splitM("  1,,,,,,", ',') }
    assertResult(IndexedSeq("1  ")) { splitM("1  ,,,,,", ',') }
    assertResult(IndexedSeq("1")) { splitM(",,1,,,,", ',') }
  }

  test("fastSplit1WideSeparator") {
    assertResult(IndexedSeq("1", "2", "3")) { split1("1,2,3", ',') }
    assertResult(IndexedSeq("1", "2", "", "", "", "3")) {
      split1("1,2,,,,3", ',')
    }
    assertResult(
      IndexedSeq("", "", "", "1", "", "2", "", "", "3", "", "", "")
    ) {
      split1(",,,1,,2,,,3,,,", ',')
    }
    assertResult(
      IndexedSeq("", "", "", "1", "", "2", "", "", "3", "", "", "")
    ) {
      split1("\n\n\n1\n\n2\n\n\n3\n\n\n", '\n')
    }
    assertResult(IndexedSeq("", "1", "2", "3")) { split1(",1,2,3", ',') }
    assertResult(IndexedSeq("")) { split1("", ',') }
    assertResult(IndexedSeq("asdf")) { split1("asdf", ',') }
    assertResult(IndexedSeq("", "", "", "", "", "")) { split1(",,,,,", ',') }
    assertResult(IndexedSeq("", "")) { split1(",", ',') }
    assertResult(IndexedSeq("", "1")) { split1(",1", ',') }
    assertResult(IndexedSeq("1", "")) { split1("1,", ',') }
    assertResult(IndexedSeq("  1", "", "", "", "", "", "")) {
      split1("  1,,,,,,", ',')
    }
    assertResult(IndexedSeq("1  ", "", "", "", "", "")) {
      split1("1  ,,,,,", ',')
    }
    assertResult(IndexedSeq("", "", "", "", "", "")) {
      split1(",,,,,", Set(','))
    }
    assertResult(
      IndexedSeq("", "", "", "1", "", "2", "", "", "3", "", "", "")
    ) {
      split1("\n\n\n1\n\n2\n\n\n3\n\n\n", Set('\n', '\t'))
    }

  }

  test("store in array shared all") {
    val mut = scala.collection.mutable.ArrayBuffer[String]();
    assertResult(IndexedSeq("1", "2", "3")) {
      storeIterInArrayAll(split1Iter("1,2,3", ','), mut); mut.toVector
    }
    assertResult(IndexedSeq("4", "5", "6")) {
      storeIterInArrayAll(split1Iter("4,5,6", ','), mut); mut.toVector
    }

  }

  test("store in array shared interval") {
    {
      val mut = scala.collection.mutable.ArrayBuffer[String]();
      assertResult(IndexedSeq("1")) {
        storeIterInArrayInterval(split1Iter("1,2,3", ','), mut, 0, 1);
        mut.toVector
      }
      assertResult(IndexedSeq("4")) {
        storeIterInArrayInterval(split1Iter("4,5,6", ','), mut, 0, 1);
        mut.toVector
      }
    }

    {
      val mut = scala.collection.mutable.ArrayBuffer[String]();
      assertResult(IndexedSeq("1", "2")) {
        storeIterInArrayInterval(split1Iter("1,2,3", ','), mut, 0, 2);
        mut.toVector
      }
      assertResult(IndexedSeq("4", "5")) {
        storeIterInArrayInterval(split1Iter("4,5,6", ','), mut, 0, 2);
        mut.toVector
      }
    }

    {
      val mut = scala.collection.mutable.ArrayBuffer[String]();
      assertResult(IndexedSeq("1", "2", "3")) {
        storeIterInArrayInterval(split1Iter("1,2,3", ','), mut, 0, 5);
        mut.toVector
      }
      assertResult(IndexedSeq("4", "5", "6")) {
        storeIterInArrayInterval(split1Iter("4,5,6", ','), mut, 0, 5);
        mut.toVector
      }
    }
    {
      val mut = scala.collection.mutable.ArrayBuffer[String]();
      assertResult(IndexedSeq("2", "3")) {
        storeIterInArrayInterval(split1Iter("1,2,3", ','), mut, 1, 5);
        mut.toVector
      }
      assertResult(IndexedSeq("5", "6")) {
        storeIterInArrayInterval(split1Iter("4,5,6", ','), mut, 1, 5);
        mut.toVector
      }
    }
  }

}
