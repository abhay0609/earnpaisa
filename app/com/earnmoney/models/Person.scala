package com.earnmoney.models

import play.api.libs.json.Json

case class Person(name :String, last: String)

object Person{
  implicit val writer = Json.writes[Person]
  implicit val reader = Json.reads[Person]
}
