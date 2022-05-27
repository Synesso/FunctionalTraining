package ft.typesafety

import arrow.core.None
import arrow.core.Some
import ft.typesafety.OptionalExercises1.getFromConfig
import ft.typesafety.OptionalExercises1.lengthOfHost
import ft.typesafety.OptionalExercises1.portPlus1000
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class OptionalExercises1Test : StringSpec({

  "Getting Host Name from config" {
    getFromConfig("host") shouldBe Some("squareup.com")
  }

  "Getting other from config" {
    getFromConfig("other") shouldBe None
  }

  "The length of the host name" {
    lengthOfHost() shouldBe Some(12)
  }

  "The port plus 1000" {
    portPlus1000() shouldBe Some(9080)
  }

})