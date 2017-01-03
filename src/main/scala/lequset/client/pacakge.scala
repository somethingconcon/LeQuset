package lequset

package object clients {

}

package object build {
  trait Resource[T] {
    val storageStragegites: Set[StorageStrategy]
    val tableType: Table
    val queryStrategy: QueryStrategy
  }

  trait Table[Resource] {
    val attributes: Set[Attribute]
    val associations: Set[Accociation]
  }
}