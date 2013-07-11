# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table proposal (
  id                        bigint not null,
  title                     varchar(255),
  problem                   varchar(255),
  solution                  varchar(255),
  benefits                  varchar(255),
  timestamp                 timestamp,
  views                     integer,
  upvotes                   integer,
  downvotes                 integer,
  proposer_email            varchar(255),
  constraint pk_proposal primary key (id))
;

create table tag (
  name                      varchar(255) not null,
  description               varchar(255),
  constraint pk_tag primary key (name))
;

create table user (
  email                     varchar(255) not null,
  name                      varchar(255),
  password                  varchar(255),
  constraint pk_user primary key (email))
;

create sequence proposal_seq;

create sequence tag_seq;

create sequence user_seq;

alter table proposal add constraint fk_proposal_proposer_1 foreign key (proposer_email) references user (email) on delete restrict on update restrict;
create index ix_proposal_proposer_1 on proposal (proposer_email);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists proposal;

drop table if exists tag;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists proposal_seq;

drop sequence if exists tag_seq;

drop sequence if exists user_seq;

