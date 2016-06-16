# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table project (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  folder                    varchar(255),
  constraint pk_project primary key (id))
;

create table t_competence_l (
  cl_id                     integer auto_increment not null,
  cl_id_student             integer,
  cl_id_competence          integer,
  cl_bool_competence        integer,
  cl_id_tutor               integer,
  cl_level                  integer,
  cl_comment                varchar(255),
  constraint pk_t_competence_l primary key (cl_id))
;

create table t_competence_p (
  cp_id                     integer auto_increment not null,
  cp_name                   varchar(255),
  cp_description            varchar(255),
  cp_type                   integer,
  constraint pk_t_competence_p primary key (cp_id))
;

create table t_competence_t (
  ct_id                     integer auto_increment not null,
  ct_name                   varchar(255),
  ct_coeff                  integer,
  ct_description            varchar(255),
  constraint pk_t_competence_t primary key (ct_id))
;

create table t_course_l (
  cl_id                     integer auto_increment not null,
  cl_id_course              integer,
  cl_id_group               integer,
  constraint pk_t_course_l primary key (cl_id))
;

create table t_course_p (
  cp_id                     integer auto_increment not null,
  cp_name                   varchar(255),
  cp_description            varchar(255),
  cp_ending_date            date,
  cp_type                   integer,
  cp_id_group               integer,
  constraint pk_t_course_p primary key (cp_id))
;

create table t_course_t (
  ct_id                     integer auto_increment not null,
  ct_name                   varchar(255),
  constraint pk_t_course_t primary key (ct_id))
;

create table t_courses_p (
  cp_id                     integer auto_increment not null,
  cp_name                   varchar(255),
  cp_description            varchar(255),
  cp_starting_date          date,
  cp_ending_date            date,
  constraint pk_t_courses_p primary key (cp_id))
;

create table t_group_p (
  gp_id                     integer auto_increment not null,
  gp_id_student             integer,
  gp_type                   integer,
  constraint pk_t_group_p primary key (gp_id))
;

create table t_group_t (
  gt_id                     integer auto_increment not null,
  gt_name                   varchar(255),
  constraint pk_t_group_t primary key (gt_id))
;

create table t_mem_status_t (
  mt_id                     integer auto_increment not null,
  mt_id_status              integer,
  mt_id_member              integer,
  constraint pk_t_mem_status_t primary key (mt_id))
;

create table t_member_p (
  mp_id                     integer auto_increment not null,
  mp_login                  varchar(255),
  mp_password               varchar(255),
  mp_name                   varchar(255),
  mp_first_name             varchar(255),
  mp_mail                   varchar(255),
  constraint pk_t_member_p primary key (mp_id))
;

create table t_note_p (
  np_id                     integer auto_increment not null,
  np_value                  integer,
  np_name                   varchar(255),
  constraint pk_t_note_p primary key (np_id))
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

drop table t_competence_l;

drop table t_competence_p;

drop table t_competence_t;

drop table t_course_l;

drop table t_course_p;

drop table t_course_t;

drop table t_courses_p;

drop table t_group_p;

drop table t_group_t;

drop table t_mem_status_t;

drop table t_member_p;

drop table t_note_p;

drop table task;

drop table user;

SET FOREIGN_KEY_CHECKS=1;

