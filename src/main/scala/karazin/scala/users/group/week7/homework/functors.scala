package karazin.scala.users.group.week7.homework

object functors:

  type MapStr[T] = Map[String, T]

  /*
   Implement Functor
  */
  trait Functor[F[_]]:
    def map[A, B](x: F[A])(f: A => B): F[B]

  given MapStrFunctor[T]: Functor[MapStr] with
    def map[A, B](x: MapStr[A])(f: A => B): MapStr[B] = x.view.mapValues(f).map(identity).toMap

  /*
   Implement Functor[Map[String, T]] for any type T
  */

end functors