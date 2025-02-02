package cpstest

import org.junit.{Test,Ignore}
import org.junit.Assert._

import scala.util.*

import cps.*
import cps.monads.{*,given}
import cps.testconfig.given


class TestCollectionMonads {

    @Test
    def testAllPairsForIterable() = {
      val l = List(1,2,3)
      val allPairs = reify[Iterable] {
          (reflect(l),reflect(l))
      }
      //println(s"allPairs=${allPairs}")
      assert(allPairs == List((1,1), (1,2), (1,3), (2,1), (2,2), (2,3), (3,1), (3,2), (3,3)))
    }


    @Test
    def testAllPairsForVector() = {
      val l = Vector(1,2,3)
      val allPairs = reify[Vector] {
          (reflect(l),reflect(l))
      }
      assert(allPairs == Vector((1,1), (1,2), (1,3), (2,1), (2,2), (2,3), (3,1), (3,2), (3,3)))
    }

    @Test
    def testThrowInsideCollection() = {
      val l = Seq(1,2,3)
      var exceptionCatched = false
      try 
        reify[Seq] {
          val v = reflect(l)
          if (v==2) then
            throw RuntimeException("v==2") 
        }
      catch
        case ex: RuntimeException =>
          exceptionCatched = true
      assert(exceptionCatched)
    }


}