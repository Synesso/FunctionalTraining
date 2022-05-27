package ft.higherorder

import ft.higherorder.FoldingExercises.append
import ft.higherorder.FoldingExercises.filter
import ft.higherorder.FoldingExercises.flatMap
import ft.higherorder.FoldingExercises.flatten
import ft.higherorder.FoldingExercises.foldLeft
import ft.higherorder.FoldingExercises.foldRight
import ft.higherorder.FoldingExercises.length
import ft.higherorder.FoldingExercises.map
import ft.higherorder.FoldingExercises.maximum
import ft.higherorder.FoldingExercises.reverse
import ft.higherorder.FoldingExercises.sum
import ft.recursion.Nil
import ft.recursion.FunList
import ft.recursion.Cons
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class FoldingExercisesTest : StringSpec({

    "sumAListWithFoldLeft" {
      foldLeft(0, FunList(1, 2, 3), Int::plus) shouldBe 6
    }

    "reconstructAListWithFoldLeft"  {
      foldLeft<Int, FunList<Int>>(Nil, FunList(1, 2, 3)){a, e -> Cons(e ,a)} shouldBe FunList(3, 2, 1)
    }


    "sumAListWithFoldRight"  {
      foldRight(0, FunList(1, 2, 3), Int::plus) shouldBe 6
    }
    "reconstructAListWithFoldRight"  {
      foldRight(Nil, FunList(1, 2, 3), ::Cons) shouldBe FunList(1, 2, 3)
    }

    "Sum of FunList(1,2,3,4,5,6) = 21"  {
      sum(FunList(1, 2, 3, 4, 5, 6)) shouldBe 21
    }

    "Length of FunList(1,2,3,4,5,6) = 6"  {
      length(FunList(1, 2, 3, 4, 5, 6)) shouldBe 6
    }

    "Add one to FunList(1,2,3,4,5,6) = FunList(2,3,4,5,6,7)"  {
      map(FunList(1, 2, 3, 4, 5, 6)){it + 1} shouldBe FunList(2,3,4,5,6,7)
    }

    "Remove elements under 4 for FunList(1,2,3,4,5,6) = FunList(4,5,6): "  {
      filter(FunList(1, 2, 3, 4, 5, 6)) {4 <= it} shouldBe FunList(4, 5, 6)
    }

    "Append FunList(a,b,c) with FunList(d,e,f) = FunList(a,b,c,d,e,f): " {
      append(FunList('a', 'b', 'c'), FunList('d', 'e', 'f')) shouldBe FunList('a', 'b', 'c', 'd', 'e', 'f')
    }

    "Flatten a FunList(FunList(a,b,c),FunList(d,e,f,g), FunList(h,i,j)) = FunList(a,b,c,d,e,f,g,h,i,j): " {
      flatten(FunList(FunList('a', 'b', 'c'), FunList('d', 'e', 'f','g'), FunList('h', 'i', 'j'))) shouldBe FunList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j')
    }
    "Run a flatMap over FunList(hello, world) with function split = FunList(h,e,l,l,o,w,o,r,l,d): " {
      flatMap(FunList("hello", "world")) {FunList(it.toCharArray().toList())} shouldBe FunList('h', 'e', 'l', 'l', 'o', 'w', 'o', 'r', 'l', 'd')
    }

    "maximum of FunList(4,3,5,7,1,2,6,3,4,5,6) = 7" {
      maximum(FunList(4, 3, 5, 7, 1, 2, 6, 3, 4, 5, 6)) shouldBe 7
    }

    "Reverse a FunList(1,2,3,4,5,6) = FunList(6,5,4,3,2,1)" {
      reverse(FunList(1, 2, 3, 4, 5, 6)) shouldBe FunList(6, 5, 4, 3, 2, 1)
    }

})
