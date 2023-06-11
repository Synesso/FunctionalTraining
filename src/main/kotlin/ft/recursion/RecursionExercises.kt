package ft.recursion

import kotlin.math.min

// Taken from http://tmorris.net/posts/scala-exercises-for-beginners/index.html

/**
 * Ok here are the rules.
 *
 * You can't use any of the standard list functions, like `map`, `filter`, `flatMap`, `append`, `:::`, `:+`, etc.
 *
 * But you can always use `Cons(head, tail)` to construct a new list by prepending an element to another list.
 *
 * You CAN and are encouraged to use the solutions from the exercises below to solve the harder
 * ones towards the end.
 *
 * Keep an eye out for repetition and similarities between your answers.
 *
 * REMEMBER: Follow the types, they almost always guide you to the solution.  If it compiles and looks a little
 * too simple, it's probably correct.  As Sherlock Holmes once said, "Each one is suggestive, together they are
 * most certainly conclusive."
 *
 * See if you can make your solution tail recursive, where possible.
 *
 */

sealed class FunList<out A> {
  companion object {
    operator fun <A> invoke(vararg l: A): FunList<A> = invoke(listOf(*l))
    operator fun <A> invoke(l: List<A>): FunList<A> =
      l.foldRight(Nil, { e: A, acc: FunList<A> -> Cons<A>(e, acc) })
  }
}

object Nil : FunList<Nothing>()
data class Cons<out A>(val head: A, val tail: FunList<A>): FunList<A>()

object RecursionExercises {

  fun plusOne(n: Int) = n + 1

  fun minusOne(n: Int) = n - 1

  // Add two non-negative Integers together.  You are only allowed to use plusOne and minusOne above
  //a = 6, b = 2
  //a = 7, b = 1
  //a = 8, b = 0
  tailrec fun add(a: Int, b: Int): Int =
    if (b == 0) a
    else add(plusOne(a), minusOne(b))

  // You are not permitted to use any list functions such as map, flatMap, ++, flatten etc
  // Empty FunList is a FunList = FunList()

  //FunList = [1,2,3]
    // add([3, 3])
  //FunList = []


  fun sum(x: FunList<Int>): Int =
    when (x) {
      Nil -> 0
      is Cons -> add(x.head, sum(x.tail))
    }

  //Again no list functions are permitted for the following
  fun <A> length(x: FunList<A>): Int {
    fun length2(x: FunList<A>, depth: Int) =
      when (x) {
        Nil -> depth
        is Cons -> length2(x.tail, depth + 1)
      }
    return length2(x, 0)
  }

  // Do you notice anything similar between sum and length? Hmm...

  // Mapping over a list.  You are given a FunList of type A and a function converting an A to a B
  // and you give back a list of type B.  No list functions allowed!
  fun <A, B> map(x: FunList<A>, f: (A) -> B): FunList<B> = TODO()

  // Given a function from A -> Boolean, return a list with only those item where the function returned true.
  fun <A> filter(x: FunList<A>, f: (A) -> Boolean): FunList<A> = TODO()

  // This pattern should be familiar by now... psst... look at add.
  fun <A> append(x: FunList<A>, y: FunList<A>): FunList<A> = TODO()

  // Flatten a list of lists to a single list.  Remember you can't use list.flatten.  Can you use a previous
  // solution to solve this one?
  fun <A> flatten(x: FunList<FunList<A>>): FunList<A> = TODO()

  // Follow the types.  You've done a great job getting here. Follow the types.
  fun <A, B> flatMap(x: FunList<A>, f: (A) -> FunList<B>): FunList<B> = TODO()

  // Maximum of the empty list is 0
  fun maximum(x: FunList<Int>): Int = TODO()

  // Reverse a list
  fun <A> reverse(x: FunList<A>): FunList<A> = TODO()
}
