package ft.typesafety

import arrow.core.Option

/**
 * Use pattern matching and recursion.  No vars, no loops, no overriding.
 *
 * `Option` is an implementation of optional functionality.
 *
 * We've made these exercises to give you greater insight into how an optional pattern
 * might work in a functional language.
 *
 * When you see `Option` think: "It may exist, or it may not"
 *
 * There are two ways to construct an `Option`:
 *
 *   `Some()` represents something that exists
 *
 *   `None` represents something that doesn't exist
 *
 * We use `Option` in situations where there isn't certainty that a meaningful
 * value will be returned to us.
 *
 * For example, a Map in Kotlin is similar to many other languages,
 * we just need to tell it about the types we're working with.
 *
 *     This is the type of the key
 *                      |
 *                      |     This is the type of the value
 *                      |      |
 *                      |      |
 *     val myMap = mapOf<Int, String>( 1 to "one", 2 to "two", ...)
 *
 *   When we index a Map we will get back the nullable value type. Let's convert it to an `Option`:
 *
 *     Option.ofNullable(myMap[1]) = Some("one")  //The value exists and it's the string "one"
 *
 *     Option.ofNullable(myMap[0]) = None      //The value doesn't exist so we get None
 *
 *   `Some("one")` and `None` are both of the type Option
 *
 *   Since `Some` and `None` are the same type we can `when` match on them.
 *
 *   We can have one set of logic when we get Some back and a different set
 *   of logic when we get `None` back!
 *
 *   val mightBeSomething: Option<String> = Option.ofNullable(myMap[3])
 *
 *   when(mightBeSomething) {
 *     is Some -> "I got a String back!"
 *     else -> otherwise()
 *   }
 *
 * Good luck!
 *
 */

object OptionalExercises1 {

  val config = mapOf("host" to "squareup.com", "port" to "8080")

  fun getFromConfig(key: String): Option<String> = TODO()

  fun lengthOfHost(): Option<Int> = TODO()

  fun portPlus1000(): Option<Int> = TODO()
}

object OptionalExercises2 {

  private fun <A, B> Map<A, B>.getOption(key: A): Option<B> = Option.fromNullable(this[key])

  val hosts = mapOf("host1" to "squareup.com", "host2" to "test.squareup.com", "host3" to "netflix.com")
  val envs = mapOf("squareup.com" to "prod", "test.squareup.com" to "test", "amazon.com" to "stage")

  // Should return the host string if successful or "couldn't resolve" if unsuccessful
  fun getEnvForHost(host: String): String = TODO()

  // See how many ways you can implement this.
  // Will either return "connected to <squareup host>" or "not connected"
  fun connectToSquareupHostsOnly(host: String): String = TODO()

  fun createConnection(domain: String): String = "connected to $domain"
}

/**
 * Here we make the trait `Maybe`, which is our version of `Option`
 *
 * `Just` has the same behavior as `Some`
 * `Nothing` has the same behavior as `None`
 *
 * We use this exercise to illustrate that we can create our own optional behavior
 * with just a few functions.
 *
 */

object OptionalExercises3 {

  sealed interface Maybe<out A>

  data class Just<A>(val get: A) : Maybe<A>

  object Nothing : Maybe<kotlin.Nothing>

  fun <A, B> flatMap(m: Maybe<A>, f: (A) -> Maybe<B>): Maybe<B> = TODO()

  fun <A, B> map(m: Maybe<A>, f: (A) -> B): Maybe<B> = TODO()

  fun <A> filter(m: Maybe<A>, p: (A) -> Boolean): Maybe<A> = TODO()

  fun <A, B> fold(m: Maybe<A>, default: () -> B, f: (A) -> B): B = TODO()

  fun <A> orElse(m: Maybe<A>, otherwise: () -> Maybe<A>): Maybe<A> = TODO()

  fun <A> orSome(m: Maybe<A>, default: () -> A): A = TODO()

  fun <A, B, C> map2(m1: Maybe<A>, m2: Maybe<B>, f: (A, B) -> C): Maybe<C> = TODO()

  fun <A> sequence(l: List<Maybe<A>>): Maybe<List<A>> = TODO()

  fun <A, B> ap(m1: Maybe<A>, m2: Maybe<(A) -> B>): Maybe<B> = TODO()
}