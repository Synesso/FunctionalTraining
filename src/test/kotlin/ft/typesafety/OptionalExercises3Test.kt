package ft.typesafety

import arrow.core.curried
import ft.typesafety.OptionalExercises3.Just
import ft.typesafety.OptionalExercises3.Maybe
import ft.typesafety.OptionalExercises3.Nothing
import ft.typesafety.OptionalExercises3.ap
import ft.typesafety.OptionalExercises3.filter
import ft.typesafety.OptionalExercises3.flatMap
import ft.typesafety.OptionalExercises3.orSome
import ft.typesafety.OptionalExercises3.map2
import ft.typesafety.OptionalExercises3.sequence
import ft.typesafety.OptionalExercises3.orElse
import ft.typesafety.OptionalExercises3.fold
import ft.typesafety.OptionalExercises3.map
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class OptionalExercises3Test : StringSpec({

  "flatMap on a Just" {
    flatMap(Just(1)) { a -> Just(a + 1) } shouldBe Just(2)
  }

  "flatMap on Nothing" {
    flatMap<Int, Int>(Nothing) { a -> Just(a + 1) } shouldBe Nothing
  }

  "map on a Just" {
    map(Just(1)) { a -> a + 1 } shouldBe Just(2)
  }

  "map on Nothing" {
    map<Int, Int>(Nothing) { a -> a + 1 } shouldBe Nothing
  }

  "filter in on a Just" {
    filter(Just(2)) { a -> a > 1 } shouldBe Just(2)
  }

  "filter out on a Just" {
    filter(Just(0)) { a -> a > 1 } shouldBe Nothing
  }

  "filter on Nothing" {
    filter(Nothing) { _ -> true } shouldBe Nothing
  }

  "fold on a Just" {
    fold(Just(1), default = { 0 }) { a -> a + 1 } shouldBe 2
  }

  "fold on Nothing" {
    fold<Int, Int>(Nothing, default = { 0 }) { a -> a + 1 } shouldBe 0
  }

  "orElse on a Just" {
    orElse(Just(1)) { Just(2) } shouldBe Just(1)
  }

  "orElse on Nothing" {
    orElse(Nothing) { Just(2) } shouldBe Just(2)
  }

  "orSome on a Just" {
    orSome(Just(1)) { 2 } shouldBe 1
  }

  "orSome on Nothing" {
    orSome(Nothing) { 2 } shouldBe 2
  }

  "map2 on a Just" {
    map2(Just(1), Just(2)) { a: Int, b: Int -> a + b } shouldBe Just(3)
  }

  "map2 on Nothing" {
    map2(Just(1), Nothing) { a: Int, b: Int -> a + b } shouldBe Nothing
  }

  "sequence on an empty List" {
    sequence(listOf<Maybe<Int>>()) shouldBe Just(listOf<Int>())
  }

  "sequence on a List of Justs" {
    sequence(listOf(Just(1), Just(2))) shouldBe Just(listOf(1, 2))
  }

  "sequence on a List of Justs and Nothings" {
    sequence(listOf(Just(1), Nothing)) shouldBe Nothing
  }

  "apply functions when all is some" {

    data class Person(val name: String, val age: Int, val address: String)

    val personConstructor: (String) -> (Int) -> (String) -> Person = ::Person.curried()

    val name = Just("Tom")
    val age = Just(40)
    val address = Just("London")

    val person: Maybe<Person> = ap(address, ap(age, map(name, personConstructor)))

    person shouldBe Just(Person("Tom", 40, "London"))
  }

})