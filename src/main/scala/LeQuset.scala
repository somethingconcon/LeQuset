package lequest

// A single interface for all clients
// API Layer for communication with clients.  This layer should only be persistence actions.
//
class LeQuset(clinets: Set[Client]) {

  // import
  //   LeRequestBuilder._,
  //   LeClientFinder._

  def command(command: LeDbCommand) // return

  def run[A <: LeRequest](request: A): Future[LeResponse] = {
    findClients(request).map {
      clinet.send(request)
    }
  }

  // def runMulti[A <: LeRequest](requests: Set[A]): Future[LeResponses] = {
  // }
}

object LeQuset {

  def apply(definitions: Set[DbDefinition]) = {
    definitions.map { definition =>
      new Client(definition)
    }
  }

  def apply(dbInterfaces: Set[Interface]) = {
    dbInterfaces.map(interface => new Client(interface))
  }
}