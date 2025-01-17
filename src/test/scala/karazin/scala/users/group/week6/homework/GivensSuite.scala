package karazin.scala.users.group.week6.homework

import scala.concurrent.Future
import karazin.scala.users.group.week6.homework.givens.{given, _}

import org.scalacheck._
import Prop.{forAll, propBoolean}

/*
  Write test for all programs in karazin.scala.users.group.week4.homework.givens

  Make sure that the following cases are tested:
    • json string representation for integers works
    • json string representation for booleans works
    • json string representation for strings works
    • json string representation for lists for integers, booleans and strings works
    • json string representation for maps for integers, booleans and strings works
    • all instances for integers, booleans, strings and lists should be randomly generated by scalacheck 
      (that's a good time to uncover homework implementations from the previous semester)

  Review:
    • https://www.json.org/json-en.html
    • https://scalameta.org/munit/docs/tests.html
    • https://scalameta.org/munit/docs/assertions.html
    • https://scalameta.org/munit/docs/assertions.html#compileerrors
    • https://scalameta.org/munit/docs/integrations/scalacheck.html
    
  NB: Do not use sync, this homework does not belong async stuff
    
 */
object nativeTypes extends Properties("native types"):

  property("integer") = forAll { (number: Int) =>
    number.toJsonString() == number.toString()
  }

  property("string") = forAll { (string: String) =>
    string.toJsonString() == "\"" + string + "\""
  }

  property("boolean") = forAll { (boolean: Boolean) =>
    boolean.toJsonString() == boolean.toString()
  }

end nativeTypes

object list extends Properties("List"):

  def getSampleString[T](list: List[T])(using encoder: => JsonStringEncoder[T]): String =
    list.map { (elem) =>
      elem.toJsonString()
    }.mkString("[", ",", "]")

  property("list Integer") = forAll { (list: List[Int]) =>
    list.toJsonString() == getSampleString(list)
  }

  property("list String") = forAll { (list: List[String]) =>
    list.toJsonString() == getSampleString(list)
  }

  property("list Boolean") = forAll { (list: List[Boolean]) =>
    list.toJsonString() == getSampleString(list)
  }

end list

object map extends Properties("Map"):

  def getSampleString[V](map: Map[String, V])(using encoder: => JsonStringEncoder[V]): String =
    map.foldLeft(List[String]()) {
      case (acc, (key, value)) => acc :+ (key.toJsonString() + ": " + value.toJsonString())}.mkString("{", ",", "}")

  property("map Integer") = forAll { (map: Map[String, Int]) =>
    map.toJsonString() == getSampleString(map)
  }

  property("map String") = forAll { (map: Map[String, String]) =>
    map.toJsonString() == getSampleString(map)
  }

  property("map Boolean") = forAll { (map: Map[String, Boolean]) =>
    map.toJsonString() == getSampleString(map)
  }

end map