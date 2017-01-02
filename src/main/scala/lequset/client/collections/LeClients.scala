package lequset.client.collections

class LeClients(clients: Set[LeClient]) {
  def filterFor(request: LeRequest) = {
    // clients.filter{ clinet =>
    //   // client. == request.action define what needs to be accessible for a request to match a client
    // }
    clients
  }
}

object LeClientFilter {
  def apply() = {
    
  }
}