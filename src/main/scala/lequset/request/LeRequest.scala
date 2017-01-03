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