package lequset.request

trait LeRequest[LeResource] {
  // * = ALL
  // - = None
  // Set[ColumnData]
  val columns:     Set[LeResourceColumn] // every action column to update
  val requestType: LeRequestType
}

object LeRequest {
  def build(action: ActionCommand, resource: LeResource): LeRequest = {
    action match {
      case a: Writer => WriteBuilder(action, resource)
      case a: Reader => ReadBuilder(action, response)
      case _ => throw new Exception("no good.")
    }
  }
}

trait LeRequestType
case class Write extends LeRequestType
case class Read extends LeReuqestType

//
case class Update extends Write
case class Create extends Write
case class Delete extends Write
case class Update extends Write
case class Upsert extends Write

//
case class Find extends Read
case class Query extends Read

//
trait LeRequestAction
case class SomeAggregateFunction extends LeRequestAction

//
trait LeActionCommand
case object Find   extends LeActionCommand
case object Get    extends LeActionCommand
case object Create extends LeActionCommand
case object Delete extends LeActionCommand
case object Query  extends LeActionCommand
case object Update extends LeActionCommand
case object Select extends LeActionCommand