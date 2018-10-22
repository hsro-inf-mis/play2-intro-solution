package controllers

import javax.inject.Inject
import play.api.mvc.{AbstractController, ControllerComponents}
import play.api.libs.json._

class GreeterController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  val messageCount = MessageCount(0)

  def greet(name: String) = {
    implicit val writes = Json.writes[MessageDto]
    messageCount.count += 1
    var messageTrager = MessageDto(s"Hello $name", name)
    Action { Ok(Json.toJson(messageTrager)) }
  }

  def greets() = {
    Action {Ok(messageCount.count.toString)}
  }
}

case class MessageDto(val message: String, val greeter: String)
case class MessageCount(var count: Int)
