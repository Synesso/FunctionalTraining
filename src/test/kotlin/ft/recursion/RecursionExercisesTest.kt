package ft.recursion

import ft.recursion.RecursionExercises.add
import ft.recursion.RecursionExercises.append
import ft.recursion.RecursionExercises.filter
import ft.recursion.RecursionExercises.length
import ft.recursion.RecursionExercises.sum
import ft.recursion.RecursionExercises.map
import ft.recursion.RecursionExercises.plusOne
import ft.recursion.RecursionExercises.flatten
import ft.recursion.RecursionExercises.flatMap
import ft.recursion.RecursionExercises.maximum
import ft.recursion.RecursionExercises.reverse
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class RecursionExercisesTest : StringSpec({
  "10 + 34 = 44" {
    add(10, 34) shouldBe 44
  }

  "0 + 34 = 34"  {
    add(0, 34) shouldBe 34
  }

  "0 + 0 = 0"  {
    add(0, 0) shouldBe 0
  }

  "Sum of FunList(1,2,3,4,5,6) = 21"  {
    sum(FunList(1, 2, 3, 4, 5, 6)) shouldBe 21
  }


  "Length of FunList(1,2,3,4,5,6) = 6"  {
    length(FunList(1, 2, 3, 4, 5, 6)) shouldBe 6
  }

  "Add one to FunList(1,2,3,4,5,6) = FunList(2,3,4,5,6,7)"  {
    map(FunList(1, 2, 3, 4, 5, 6), ::plusOne) shouldBe FunList(2, 3, 4, 5, 6, 7)
  }

  "Remove elements under 4 for FunList(1,2,3,4,5,6) = FunList(4,5,6): "  {
    filter(FunList(1, 2, 3, 4, 5, 6)) {
      it >= 4
    } shouldBe FunList(4, 5, 6)
  }

  "Append FunList(a,b,c) with FunList(d,e,f) = FunList(a,b,c,d,e,f)"  {
    append(FunList('a', 'b', 'c'), FunList('d', 'e', 'f')) shouldBe FunList('a', 'b', 'c', 'd', 'e', 'f')
  }

  "Flatten a FunList(FunList(a,b,c),FunList(e,f,g), FunList(h,i,j)) = FunList(a,b,c,d,e,f,g,h,i,j)"  {
    flatten(FunList(FunList('a', 'b', 'c'), FunList('d', 'e', 'f', 'g'), FunList('h', 'i', 'j'))) shouldBe FunList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j')
  }

  "Run a flatMap over FunList(hello, world) with function split = FunList(h,e,l,l,o,w,o,r,l,d): "  {
    flatMap(FunList("hello", "world")) { FunList(it.toCharArray().toList())} shouldBe FunList('h', 'e', 'l', 'l', 'o', 'w', 'o', 'r', 'l', 'd')
  }

  "maxium of FunList(4,3,5,7,1,2,6,3,4,5,6) = 7"  {
    maximum(FunList(4, 3, 5, 7, 1, 2, 6, 3, 4, 5, 6)) shouldBe 7
  }

  "Reverse a FunList(1,2,3,4,5,6) = FunList(6,5,4,3,2,1)"  {
    reverse(FunList(1, 2, 3, 4, 5, 6)) shouldBe FunList(6, 5, 4, 3, 2, 1)
  }

})
