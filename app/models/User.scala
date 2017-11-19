package models

import scalikejdbc._

case class Users(
  id: Int,
  name: String,
  email: String
)

object Users extends SQLSyntaxSupport[Users]{
  // オブジェクトの名称と異なる時に設定が必要。キャメルケースをクラス名にした場合などか？
  override val tableName = "users"
  override val columns = Seq("id","name","email")

  // def apply(u: SyntaxProvider[Users])(rs: WrappedResultSet): Users = apply(u.resultName)(rs)
  // findAllで必要となる↓
  // applyはクラス名で呼ばれた際に使われる糖衣構文
  def apply(u: ResultName[Users])(rs: WrappedResultSet): Users = new Users(
    id = rs.get(u.id),
    name = rs.get(u.name),
    email = rs.get(u.email)
  )

  val u = Users.syntax("u")
  override val autoSession = AutoSession

  def findAll()(implicit session: DBSession = AutoSession):List[Users] = {
    withSQL(select.from(Users as u)).map(Users(u.resultName)).list.apply()
  }

}
