package karazin.scala.users.group.week3.homework

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

import karazin.scala.users.group.week3.homework.model.*
import karazin.scala.users.group.week3.homework.program.*
import java.util.UUID
import scala.util.{Success, Failure}

/*
  Write test for all programs in karazin.scala.users.group.week3.homework.program

  Review:
    • https://scalameta.org/munit/docs/tests.html
    • https://scalameta.org/munit/docs/assertions.html
 */

class ProgramSuite extends munit.FunSuite:

  test("getPostView test") {
    val userId_ = UUID.randomUUID()
    val postId_  = UUID.randomUUID()
    val postView = getPostView(Post(userId_, postId_))
    Future {
      postView onComplete {
        case Success(PostView(Post(userId, postId), _, _, _)) => assertEquals(userId, userId_); assertEquals(postId, postId_);
        case Failure(error) => fail("getPostView failed")
      }
    }
  }
