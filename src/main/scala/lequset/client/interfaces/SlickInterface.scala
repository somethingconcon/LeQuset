package lequset.client.interfaces

class SlickInterface() extends Interface {

}

object SlickInterface extends Interface {
  def apply(tables: Set[Table]) = {
    new SlickInteface(tables)
  }
}