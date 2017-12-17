# --- First database schema

# --- !Ups
create table posts (
  id             bigint not null auto_increment,
  title          varchar(255) not null,
  content        varchar(255) not null,
  user_id        bigint not null,
  constraint     pk_posts primary key (id)
);

# --- !Downs
drop table if exists posts;
