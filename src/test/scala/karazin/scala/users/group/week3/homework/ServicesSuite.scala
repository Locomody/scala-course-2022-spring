package karazin.scala.users.group.week3.homework

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import karazin.scala.users.group.week3.homework.model.*
import karazin.scala.users.group.week3.homework.services.*
import java.util.UUID

import scala.util.{Success, Failure}

/*
  Write test for all service in karazin.scala.users.group.week3.homework.services

  Review:
    • https://scalameta.org/munit/docs/tests.html
    • https://scalameta.org/munit/docs/assertions.html
 */

class ServicesSuite extends munit.FunSuite:

  test("getUserProfile test") {
    val userProfile = getUserProfile()
    userProfile onComplete {
      case Success(UserProfile(_)) => assert(true)
      case Failure(error) => fail("getUserProfile failed")
    }
  }

  test("getPosts test") {
    val userId = UUID.randomUUID()
    val posts = getPosts(userId)
    posts map {
      posts =>
        for (post <- posts)
          assertEquals(post.userId, userId)
    }
  }

  test("getComments test") {
    val postId = UUID.randomUUID()
    val comments = getComments(postId)
    comments map {
      comments =>
        for (comment <- comments)
          assertEquals(comment.postId, postId)
    }
  }

  test("getLikes test") {
    val postId = UUID.randomUUID()
    val likes = getLikes(postId)
    likes map {
      likes =>
        for (like <- likes)
          assertEquals(like.postId, postId)
    }
  }

  test("getShares test") {
    val postId = UUID.randomUUID()
    val shares = getShares(postId)
    shares map {
      shares =>
        for (share <- shares)
          assertEquals(share.postId, postId)
    }
  }
