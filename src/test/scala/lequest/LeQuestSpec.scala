package lequest

import
  org.scalatest.Matchers,
  org.scalamock.scalatest.MockFactory,
  org.scalatest.WordSpec

class LeQuestSpec extends WordSpec
                 with MockFactory
                 with Matchers {

  "LeQuest" should {

    val config = s"""lequest { dbs = [] } """
    val lequest = LeQuest.load(config)

    "build a Set[DB] from configuration" in {
      lequest.dbs shouldBe Set[DB()]
    }
  
  }
}