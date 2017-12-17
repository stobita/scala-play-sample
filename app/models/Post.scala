package models

import scalikejdbc._

case class Posts(
  id: Long,
  title: String,
  content: String,
  user_id: Long
)

object Posts extends SQLSyntaxSupport[Posts]{
  override val tableName = "posts"
  override val columns = Seq("id", "title", "content", "user_id")

  def create(title: String, content: String, user_id: Long)(implicit session: DBSession = AutoSession): Posts = {
    val postId = withSQL{
      insert.into(Posts).namedValues(
        column.title -> title,
        column.content -> content,
        column.user_id -> user_id
      )
    }.updateAndReturnGeneratedKey.apply()
    Posts(
      id = postId,
      title = title,
      content = content,
      user_id = user_id
    )
  }
}
