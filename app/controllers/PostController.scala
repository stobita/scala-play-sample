package controllers

import javax.inject._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

import models._

object PostController{
  case class PostForm(title: String, content: String)

  var postForm = Form(
    mapping(
      "title" -> text,
      "content" -> text
    )(PostForm.apply)(PostForm.unapply)
  )
}

class PostController @Inject()(cc: ControllerComponents) extends AbstractController(cc) with play.api.i18n.I18nSupport{
  import PostController._
  def register() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.post.register(postForm))
  }
  def create() = Action { implicit request =>
    postForm.bindFromRequest.fold(
      formWithErrors => {
        BadRequest(views.html.post.register(formWithErrors))
      },
      postForm => {
        val post = Posts.create(postForm.title, postForm.content, 1)
        Redirect(routes.PostController.register)
      }
    )
  }
}
