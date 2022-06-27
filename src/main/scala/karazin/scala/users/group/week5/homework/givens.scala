package karazin.scala.users.group.week5.homework

object givens:
  
  /* 
    The trait is used for converting instances to a json string representation
    Provide a type parameter(s) for the trait and the method 
    and argument(s) and a result type
  */
  
  trait JsonStringEncoder[T]:
    def encode(src: T): String

  given IntEncoder: JsonStringEncoder[Int] with
    def encode(src: Int): String = src.toString

  given BoolEncoder: JsonStringEncoder[Boolean] with
    def encode(src: Boolean): String = src.toString

  given StringEncoder: JsonStringEncoder[String] with
    def encode(src: String): String = "\"" + src + "\""

  given ListEncoder[T](using encoder: => JsonStringEncoder[T]): JsonStringEncoder[List[T]] with
    def encode(src: List[T]): String = "[" + src.foldLeft(List[String]()) {(acc, src) => acc :+ encoder.encode(src)}.mkString(", ") + "]"

  object JsonStringEncoder:
    def apply[V](using encoder: => JsonStringEncoder[V]): JsonStringEncoder[V] =
      encoder

  /* 
    Make sure that integers, booleans, strings and lists 
    are convertable to a json string representation 
  */ 