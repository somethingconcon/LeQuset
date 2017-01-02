package lequest

// A single interface for all clients
// API Layer for communication with clients.  This layer should only be persistence actions.
//
class LeQuset(clinets: Set[LeClient]) {

  import
    lequset.client.collections.LeClients
  
  val leClients = new LeClients(clients)

  def command(command: LeDbCommand) // return

  def run[A <: LeRequest](request: A): Future[LeResponse] = {
    // Find clients that satisfy the request type
    leClients.filterFor(request) {
      _.run(request)
    }
  }

  // def runMulti[A <: LeRequest](requests: Set[A]): Future[LeResponses] = {
  // }
}

object LeQuset {
  def apply(interfaces: Set[Interface]): LeQuset = {
    dbInterfaces.map(interface => new LeClient(interface))
  }
}