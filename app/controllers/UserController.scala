package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import models._
import play.api.data._
import play.api.data.Forms._

object UserController{
  // Form
  case class UserForm(name: String, email: String)

  // apply/unapplyでcase classをmappingに接続する？
  // フィールド数の上限は22らしい。超える場合はListに詰め込む
  val userForm = Form(
    mapping(
      "name" -> text,
      "email" -> text
    )(UserForm.apply)(UserForm.unapply)
  )
}

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class UserController @Inject()(cc: ControllerComponents) extends AbstractController(cc) with play.api.i18n.I18nSupport {
  import UserController._
  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action { implicit request: Request[AnyContent] =>
    val users = Users.findAll()
    Ok(views.html.user.index(users))
  }

  def register() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.user.register(userForm))
  }

  def create() = Action { implicit request: Request[AnyContent] =>
    userForm.bindFromRequest.fold(
      formWithErrors =>{
        // bindingエラー発生時の処理
        BadRequest(views.html.user.register(formWithErrors))
      },
      userForm => {
        // フォームから値を取得し、Userモデルに投げる
        val user = Users.create(userForm.name, userForm.email)
        Redirect(routes.UserController.index)
      }
    )
  }
}
