package lequset.engine

import
  scala.concurrent._,
  scala.concurrent.duration._,
  scala.concurrent.ExecutionContext.Implicits.global

object Engine {

  import
    lequset.DB,
    lequset.request.LeRequest

  def doIt[T](db: T, request: LeRequest) = {
    db match {
      case client: DynamoClient => DynamoEngine(db, request)
      case client: SlickClient  => SlickEngine(db, request)
      case _ => throw new UnrecognizedDBError("Database Definition is not valid.")
    }
  }

  class UnrecognizedDBError(reason: String) extends Exception(reason)
}