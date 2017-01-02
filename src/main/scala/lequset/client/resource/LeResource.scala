package lequset.client.resource

import
  shapeless._

trait LeResource[PK, Resource] {

  val id: Option[PK]
  val primaryKey: PrimaryKey[PK]

  def from(row: Row): Resource = {
    row.signature.from(row)
  }

  def to(resource: Resource): Row = {
    row.signature.to(data)
  }

}

private trait PrimaryKey[T] {
  val key: T
}

private trait Row[T] {
  val signature: T
}

private object Row {
  def apply(resource: T) = {
    // new Row[Generic[T]]{
    //   val signature = Generic[T]
    // }
    new Row {
      val signature = Generic[T]
    }
  }
}