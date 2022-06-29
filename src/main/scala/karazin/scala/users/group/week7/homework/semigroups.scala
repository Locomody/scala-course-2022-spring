package karazin.scala.users.group.week7.homework

object semigroups:

  /*
   Implement Semigroup
  */
  trait Semigroup[T]:
    extension (left: T) def combine(right: T): T

  given IntGroup: Semigroup[Int] with
    extension (left: Int) def combine(right: Int): Int = left + right

  given BooleanGroup: Semigroup[Boolean] with
    extension (left: Boolean) def combine(right: Boolean): Boolean = left | right

  given StringGroup: Semigroup[String] with
    extension (left: String) def combine(right: String): String = left concat right

  given ListGroup[T]: Semigroup[List[T]] with
    extension (left: List[T]) def combine(right: List[T]): List[T] = left ::: right

  given MapGroup[T](using group: => Semigroup[T]): Semigroup[Map[String, T]] with
    extension (left: Map[String, T]) def combine(right: Map[String, T]): Map[String, T] =
      (left ++ right) map { (key, value) =>
        left.get(key) match
          case Some(leftValue)        => right.get(key) match
            case Some(rightValue)     => (key, leftValue combine rightValue)
            case _                     => (key, value)
          case _                       => (key, value)
      }
  
  /*
   Implement Semigroup[Map[String, T]] for any type T
   */
  
end semigroups
