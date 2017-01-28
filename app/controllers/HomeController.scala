package controllers

import java.io.File
import javax.inject._

import com.earnmoney.models.Person
import play.api._
import play.api.libs.json.Json
import play.api.mvc._
/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject() extends Controller {

  /**
   * Create an Action to render an HTML page with a welcome message.
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index = Action {
    Logger.debug("Attempting risky calculation.")

    Ok(views.html.index("Your new application is ready."))
  }

  def json = Action {
    Ok(Json.toJson(Person("abhay", "singh")))
  }
  def upload = Action(parse.multipartFormData) { request =>
    request.body.file("picture").map { picture =>
      val filename = picture.filename
      val contentType = picture.contentType
      picture.ref.moveTo(new File(s"/tmp/picture/$filename"))
      Ok("File uploaded")
    }.getOrElse {
      Redirect(routes.HomeController.index).flashing(
        "error" -> "Missing file")
    }
  }

}
