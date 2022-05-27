package ft.typesafety

import ft.typesafety.OptionalExercises2.connectToSquareupHostsOnly
import ft.typesafety.OptionalExercises2.getEnvForHost
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class OptionalExercises2Test : StringSpec({

  "Get environment for host1" {
    getEnvForHost("host1") shouldBe "prod"
  }

  "Get environment for host2" {
    getEnvForHost("host2") shouldBe "test"
  }

  "Get environment for host3" {
    getEnvForHost("host3") shouldBe "couldn't resolve"
  }

  "Get environment for host4" {
    getEnvForHost("host4") shouldBe "couldn't resolve"
  }

  "Connect to Squareup host for host1" {
    connectToSquareupHostsOnly("host1") shouldBe "connected to squareup.com"
  }

  "Connect to Squareup host for host2" {
    connectToSquareupHostsOnly("host2") shouldBe "connected to test.squareup.com"
  }

  "Not connect to any host for host3" {
    connectToSquareupHostsOnly("host3") shouldBe "not connected"
  }

  "Not connect to any host for host4" {
    connectToSquareupHostsOnly("host4") shouldBe "not connected"
  }

})