# --- First database schema

# --- !Ups
create table users (
  id                        bigint not null auto_increment,
  name                      varchar(255) not null,
  email                     varchar(255) not null,
  constraint pk_users primary key (id))
;

insert into users (id,name,email) values (1,'テスト1','test1@example.com');
insert into users (id,name,email) values (2,'テスト2','test2@example.com');

# --- !Downs
drop table if exists users;
