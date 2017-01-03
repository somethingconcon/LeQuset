package lequset.engine.querylang

class QueryLanguage {

}

class Query(resource: LeResource) {
  
  val builder = new DynamicQueryStatement(from = From(resource.table))
  var currentBuilder = builder
  
  // these are fields defined on resouces and need to be translated into generic types
  // Lift Resource Attributes into LeAttribute for developing query operations
  
  trait LeAttribute[T] {
    val attribute: T
  }

  trait LeFilter[T] {
    val filter: T
  }

  // different from the Resource Definition because they're not the same concern
  trait LeTable[LeResource] {

  }

  trait LeQueryStatement 
 
  case class From  (table:       LeTable)          extends LeQueryStatement
  case class Limit (limit:       Int)              extends LeQueryStatement
  case class Selects(attributes: Set[LeAttribute]) extends LeQueryStatement 
  case class Wheres(filters:     Set[LeFilter])    extends LeQueryStatement

  class DynamicQueryStatement(val from: From, val limit: Limit, val selects: Select, val wheres: Where) {
    
    def addFrom(table: LeTable) = {
      new DynamicQueryStatement(From(table), Limit(0), selects, wheres)
    }

    def addLimit(n: Int) = {
      new DynamicQueryStatement(from, Limit(n), selects, wheres)
    }

    def addSelects(fields: Selects) = {
      new DynamicQueryStatement(from, limit, Selects(fields), wheres)
    }

    def addWheres(filters: Wheres) = {
      new DynamicQueryStatement(from, limit, fields, Wheres(filters))
    }

    private def addStatement(statement: QueryStatement) = statement match {
      case statement: From    => statementBuilder.addFrom(statement)
      case statement: Limit   => statementBuilder.addLimit(statement)
      case statement: Selects => statementBuilder.addSelect(statement)
      case statement: Wheres  => statementBuilder.addWheres(statement)
      case _ => throw new Exception("Statement Type not implemented yet.")
    }

  }

  // possibly not in the correct scope
  // def find() = {
  //   FindQueryStatement() // return an immutable FindQueryStatement because find is a singular operation
  // }

  // this can change after statement are added to the structure
  def addStatement(statement: QueryStatement) = {
    currentBuilder = currentBuilder.addStatement(statement)
  }

  // def query() = {
  //   //
  // }
  
  // LeTable is an object which can be a mysql table, redis db, dynamo instance, or cassandra ... let's do this
  // resource tables are more of a location than a schema definition I think...this might be bullshit
  def from(resourceTable: LeTable) = {
    addStatement(From(resourceTable))
    
    this
  }

  def limit(n: Int) = {
    addStatement(Limit(n))

    this
  }
  
  // attributes to select
  def select(fields: LeFields) = {
    addStatement(Selects(buildAttributes(fields)))
    
    this
  }

  // a collection that presevers order...
  // def orderBy() = {

  // }

  def where(filters: Set[LeFilter]) = {
    addStatement(Wheres(filters)
    
    this
  }
}

trait LeRequestType
case class Write extends LeRequestType
case class Read  extends LeReuqestType

//
case class Update extends Write
case class Create extends Write
case class Delete extends Write
case class Update extends Write
case class Upsert extends Write

//
case class Find   extends Read
case class Query  extends Read

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
