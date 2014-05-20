package com.rea.typesafety

import scalaz._, Scalaz._

object ValidationExercises {
  type VE[+A] = ValidationNel[ErrorCode, A]

  def validateKey(input: Map[String, String], key: String): ValidationNel[ErrorCode, String] =
    input.get(key).toSuccess(keyNotFound(key)).toValidationNel

  def nameValidation(name: String, label: String): ValidationNel[ErrorCode, String] =
    if (!name.isEmpty) name.successNel else nameIsEmpty(label).failNel

  def passwordStrengthValidation(password: String): ValidationNel[ErrorCode, String] =
    if (password.matches( """.*\d+.*""")) password.successNel else passwordTooWeak.failNel

  def passwordLengthValidation(password: String): ValidationNel[ErrorCode, String] =
    if (password.length >= 8) password.successNel else passwordTooShort.failNel

  def validateInput(input: Map[String, String]): ValidationNel[ErrorCode, Person] = {
    val vKey = validateKey(input, _: String)
    def vName(name: String) = vKey(name).flatMap(nameValidation(_, name))

    val firstName = vName("firstName")
    val lastName = vName("lastName")


    val password = vKey("password").flatMap(
      password => passwordLengthValidation(password) <* passwordStrengthValidation(password)
    )

    Apply[VE].apply3(firstName, lastName, password)(Person)

  }


}

case class Person(firstName: String, lastName: String, password: String)

sealed trait ErrorCode

case object passwordTooShort extends ErrorCode

case object passwordTooWeak extends ErrorCode

case class keyNotFound(key: String) extends ErrorCode

case class nameIsEmpty(key: String) extends ErrorCode


/*

Interesting Validator combinators

scala> val a:ValidationNel[String,String]  = "hi".successNel
a: scalaz.ValidationNel[String,String] = Success(hi)

scala> val b:ValidationNel[String,String]  = "world".successNel
b: scalaz.ValidationNel[String,String] = Success(world)

scala> val c:ValidationNel[String,String]  = "error1".failNel
c: scalaz.ValidationNel[String,String] = Failure(NonEmptyList(error1))

scala> val d:ValidationNel[String,String]  = "error2".failNel
d: scalaz.ValidationNel[String,String] = Failure(NonEmptyList(error2))

scala> a <* b
res0: scalaz.Validation[scalaz.NonEmptyList[String],String] = Success(hi)

scala> a *> b
res1: scalaz.Validation[scalaz.NonEmptyList[String],String] = Success(world)

scala> c <* d
res2: scalaz.Validation[scalaz.NonEmptyList[String],String] = Failure(NonEmptyList(error1, error2))

scala> a <* d
res3: scalaz.Validation[scalaz.NonEmptyList[String],String] = Failure(NonEmptyList(error2))

scala> a flatMap (hi => b)
res4: scalaz.Validation[scalaz.NonEmptyList[String],String] = Success(world)

scala> a flatMap (s => if (s == "hi") "hey back".successNel else "fine, be that way!".failNel)
res6: scalaz.Validation[scalaz.NonEmptyList[String],String] = Success(hey back)

scala> d flatMap (s => if (s == "hi") "hey back".successNel else "fine, be that way!".failNel)
res7: scalaz.Validation[scalaz.NonEmptyList[String],String] = Failure(NonEmptyList(error2))

scala> b flatMap (s => if (s == "hi") "hey back".successNel else "fine, be that way!".failNel)
res8: scalaz.Validation[scalaz.NonEmptyList[String],String] = Failure(NonEmptyList(fine, be that way!))

scala> a map (hi => hi + " worldz")
res5: scalaz.Validation[scalaz.NonEmptyList[String],String] = Success(hi worldz)


 */
