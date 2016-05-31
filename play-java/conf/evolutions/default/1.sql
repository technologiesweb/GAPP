# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table project (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  folder                    varchar(255),
  constraint pk_project primary key (id))
;

create table t_competence_p (
  cp_name                   varchar(255) not null,
  cp_parent_id              integer,
  cp_description            varchar(255),
  cp_coeff                  integer,
  constraint pk_t_competence_p primary key (cp_name))
;

create table t_courses_p (
  cp_id                     integer auto_increment not null,
  t_courses_t_ct_id         integer,
  cp_ending_date            datetime(6),
  cp_starting_date          datetime(6),
  constraint pk_t_courses_p primary key (cp_id))
;

create table t_member_p (
  mp_login                  varchar(255) not null,
  mp_password               varchar(255),
  constraint pk_t_member_p primary key (mp_login))
;

create table task (
  id                        bigint auto_increment not null,
  title                     varchar(255),
  done                      tinyint(1) default 0,
  due_date                  datetime(6),
  assigned_to_email         varchar(255),
  folder                    varchar(255),
  project_id                bigint,
  constraint pk_task primary key (id))
;

create table user (
  email                     varchar(255) not null,
  name                      varchar(255),
  password                  varchar(255),
  constraint pk_user primary key (email))
;


create table project_user (
  project_id                     bigint not null,
  user_email                     varchar(255) not null,
  constraint pk_project_user primary key (project_id, user_email))
;
alter table task add constraint fk_task_assignedTo_1 foreign key (assigned_to_email) references user (email) on delete restrict on update restrict;
create index ix_task_assignedTo_1 on task (assigned_to_email);
alter table task add constraint fk_task_project_2 foreign key (project_id) references project (id) on delete restrict on update restrict;
create index ix_task_project_2 on task (project_id);



alter table project_user add constraint fk_project_user_project_01 foreign key (project_id) references project (id) on delete restrict on update restrict;

alter table project_user add constraint fk_project_user_user_02 foreign key (user_email) references user (email) on delete restrict on update restrict;

# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table project;

drop table project_user;

drop table t_competence_p;

drop table t_courses_p;

drop table t_member_p;

drop table task;

drop table user;

SET FOREIGN_KEY_CHECKS=1;

