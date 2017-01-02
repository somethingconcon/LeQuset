package lequest.client

import
  lequest.Interface,
  lequest.Interface._

trait LeClient[A <: Interface, B] {

  import LeClient._

  val db:            B
  val translator:    LeTranslator[B]
  val clientContext: ExecutionContext // define a EC for clients to distribute cpu load

  def run[C, D](request: LeRequest)(implicit ec: ExecutionContext) = {
    //
    // pass EC to Engine plz
    //
    Engine.doIt[B, C](db, request, translator) onComplete {
      case Success(data) => SuccessfulResponse[C](data)
      case Failure(ex)   => FailureResponse(ex)
    }
  }
}

object LeClient {
  case class SuccessfulResponse[A](data: Traversable[A])
  case class FailureResponse(bad: Throwable)
}