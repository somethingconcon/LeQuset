class Statement(resource: LeResource) {

  private def build( action: Action,
                     data  : Set[LeResourceRow],
                     fields: Fields = Fielder(None),
                      joins: Joins  = Joiner(None),
                     offset: Offset = Offsetter(None),
                    limiter: Limit  = Limiter(None)) = {
    new BuiltStatement()
  }

  def find(key: Key) = {
    build(
      action = Find,
      fields = Set(key)
      limit  = LimitBuilder(1))
  }

  def query(selects: Set[Field], limit: Int = 0) = {
    build(
      action = Query,
      fields = Filder(selects))
  }

  def update(id: PrimaryKey, fields: Set[Field], values: Set[FValues]) = {
    build(
      action = Update)
  }

  def update(id: PrimaryKey, row: LeResourceRow) = {
    build(
      action = Update,
      data   = Set(row))
  }

  def Fielder(fields: Option[Set[Field]]) = fields match {
    case Some(data) => data
    case None       => Set[Field]()
  }

  def Joiner(joins: Option[Set[Join]]) = joins match {
    case Some(data) => data
    case None       => Set[Join]()
  }

  def Offsetter(offset: Option[Int]) = offset match {
    case Some(data) = OffsetBuilder(data)
    case None       = NoOffset()
  }


  def Limiter(limit: Option[Int]) = limit match {
    case Some(data) => LimitBuilder(data)
    case None       => NoLimit()
  }
}

// LeDB Request Building Blocks
trait ConditionLens
case class Limit(n: Int)                               extends ConditionLens
case class Where(s: Filter)                            extends ConditionLens
case class Join(t: LeTable, on: => t.foreignKey#type ) extends ConditionLens
case class Limit(n: Int)                               extends ConditionLens
case class Having()                                    extends ConditionLens

trait ResultLens
case class OrderBy(columns: Set[Columns], desc: Boolean) extends ResultLens
case class GroupBy(columns: Set[Columns])                extends ResultLens
case class Select (columns: Set[Columns])                extends ResultLens

object Statement {

  def find(r: LeResource, conditions: Set[Condition]) = {
    new Statement(r).find(conditions)
  }

  def query(r: LeResource, conditions: Set[Condition]) = {
    new Statement(r).query(conditions)
  }
}

// trait PersistenceStatement
// CreateStatement extends PersistenceStatement
// DeleteStatement extends PersistenceStatement
// UpdateStatement extends PersistenceStatement


// trait QueryStatement {

// }

// class SelectStatement() extends QueryStatement {

// }