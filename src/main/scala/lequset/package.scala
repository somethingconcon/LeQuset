package object lequest {

  trait Client {
    val dbClinetInterface: DbClinetInterface
  }

  // A represents the clinet type
  trait DbClinetInterface[T, B] {
    val client: A
  }

  // This is what the Storage layer + DB table represent
  trait Resource[T] extends PersistentEntity[T] {
    val id: Id
  }

  // define backends
  trait Storage {
    import storage._

    val strategy: Strategy
  }

  // define Resource Schema
  // define fields that apply to a Resource
  trait Schema {
    val fields: Set[Field]
  }

}

package object schema {

  // trait Column {

  // }
  trait Schema {
    val tables: Set[Table]
  }

  // generic representation of schema Int :: String :: Nil
  // HList entity
  // Type T here is EQUAL to the HList of a Resource ie User / Account / Profile
  // * things that are domain level objects
  // case classes work but it would be good to represent classes as well.
  trait Entity[T] {

  }

  /*
    Field[T] is the representation of a single table attribute
    Table(Set(Field[T], Field[T], Field[T]))
  */
  trait Field[T] {

  }

  // represents
  trait PersistentEntity[Entity[T]] {
    val id: Id[]
  }

  trait PrimaryKey {
    val id: Field[T]
  }

  trait Table {
    val columns: Set[Column]
    val entity: Entity
  }
}

package object storage {

  trait      Strategy  { val joinable: Boolean }
  case class Relational(joinable: Boolean = true ) extends Strategy
  case class KeyValue  (joinable: Boolean = false) extends Strategy
  case class NoSql     (joinable: Boolean = false) extends Strategy

  trait Table
  case class JoinableTable extends Table
  case class SingleTable   extends Table

}

