# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table address (
  address                   varchar(150),
  city                      varchar(50),
  province                  varchar(100),
  country                   varchar(100),
  postal_code               varchar(15))
;

create table comment (
  id                        bigint not null,
  user_id                   bigint,
  food_id                   bigint,
  title                     varchar(255),
  content                   varchar(1000),
  constraint pk_comment primary key (id))
;

create table food (
  id                        bigint not null,
  foodtype_id               bigint,
  restaurant_id             bigint,
  name                      varchar(100) not null,
  price                     double(22) not null,
  is_available              boolean,
  ranking                   float(12) not null,
  ranking_vote              bigint,
  constraint pk_food primary key (id))
;

create table foodorder (
  id                        bigint not null,
  user_id                   bigint,
  menu_id                   bigint,
  ordertype_id              bigint,
  food_id                   bigint,
  quantity                  bigint not null,
  discount                  double(22),
  order_at                  timestamp(19),
  deal                      boolean,
  comments                  varchar(255),
  constraint pk_foodorder primary key (id))
;

create table foodtype (
  id                        bigint not null,
  name                      varchar(100) not null,
  constraint pk_foodtype primary key (id))
;

create table menu (
  id                        bigint not null,
  usergroup_id              bigint,
  created_at                timestamp(19),
  deal                      boolean,
  closed_at                 timestamp(19),
  is_available              boolean,
  constraint pk_menu primary key (id))
;

create table menu_user (
  id                        bigint not null,
  user_id                   bigint,
  menu_id                   bigint,
  constraint pk_menu_user primary key (id))
;

create table ordertype (
  id                        bigint not null,
  name                      varchar(255),
  constraint pk_ordertype primary key (id))
;

create table payment (
  id                        bigint not null,
  user_id                   bigint,
  usergroup_id              bigint,
  amount                    double(22) not null,
  created_at                timestamp(19) not null,
  description               varchar(255),
  constraint pk_payment primary key (id))
;

create table restaurant (
  id                        bigint not null,
  name                      varchar(100) not null,
  phone_number              varchar(50),
  address                   varchar(255),
  manager                   varchar(50),
  ranking                   float(12),
  ranking_vote              bigint,
  open_at                   timestamp(19),
  delivery_at               timestamp(19) not null,
  is_available              boolean,
  constraint pk_restaurant primary key (id))
;

create table role (
  id                        bigint not null,
  name                      varchar(20),
  description               varchar(64),
  constraint pk_role primary key (id))
;

create table schedule (
  id                        bigint not null,
  user_id                   bigint,
  ordertype_id              bigint,
  food_id                   bigint,
  start_at                  timestamp(19),
  end_at                    timestamp(19),
  calendar_type             bigint,
  cycle_day                 bigint,
  cycle_times               bigint,
  is_available              boolean,
  constraint pk_schedule primary key (id))
;

create table user (
  id                        bigint not null,
  version                   integer not null,
  usergroup_id              bigint,
  role_id                   bigint,
  user_name                 varchar(50) not null,
  full_name                 varchar(50),
  password                  varchar(50) not null,
  email                     varchar(100) not null,
  phone_number              varchar(50),
  money                     double(22),
  bonus                     bigint,
  account_expired           boolean,
  account_locked            boolean,
  account_enabled           boolean,
  credentials_expired       boolean,
  reg_at                    timestamp(19),
  last_login                timestamp(19),
  ip                        varchar(20),
  mail_reminder_on          boolean,
  constraint pk_user primary key (id))
;

create table usergroup (
  id                        bigint not null,
  name                      varchar(100),
  im_on                     boolean,
  open_reg                  boolean,
  lucky_rule                bigint,
  mail_reminder_on          boolean,
  menu_auto_close           boolean,
  bonus_on                  boolean,
  bonus_pay_ratio           double(22),
  bonus_order_ratio         double(22),
  bonus_manage_ratio        double(22),
  bonus_carry_ratio         double(22),
  description               varchar(1000),
  constraint pk_usergroup primary key (id))
;

create sequence comment_seq;

create sequence food_seq;

create sequence foodorder_seq;

create sequence foodtype_seq;

create sequence menu_seq;

create sequence menu_user_seq;

create sequence ordertype_seq;

create sequence payment_seq;

create sequence restaurant_seq;

create sequence role_seq;

create sequence schedule_seq;

create sequence user_seq;

create sequence usergroup_seq;

alter table comment add constraint fk_comment_user_1 foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_comment_user_1 on comment (user_id);
alter table comment add constraint fk_comment_food_2 foreign key (food_id) references food (id) on delete restrict on update restrict;
create index ix_comment_food_2 on comment (food_id);
alter table food add constraint fk_food_foodtype_3 foreign key (foodtype_id) references foodtype (id) on delete restrict on update restrict;
create index ix_food_foodtype_3 on food (foodtype_id);
alter table food add constraint fk_food_restaurant_4 foreign key (restaurant_id) references restaurant (id) on delete restrict on update restrict;
create index ix_food_restaurant_4 on food (restaurant_id);
alter table foodorder add constraint fk_foodorder_user_5 foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_foodorder_user_5 on foodorder (user_id);
alter table foodorder add constraint fk_foodorder_menu_6 foreign key (menu_id) references menu (id) on delete restrict on update restrict;
create index ix_foodorder_menu_6 on foodorder (menu_id);
alter table foodorder add constraint fk_foodorder_ordertype_7 foreign key (ordertype_id) references ordertype (id) on delete restrict on update restrict;
create index ix_foodorder_ordertype_7 on foodorder (ordertype_id);
alter table foodorder add constraint fk_foodorder_food_8 foreign key (food_id) references food (id) on delete restrict on update restrict;
create index ix_foodorder_food_8 on foodorder (food_id);
alter table menu add constraint fk_menu_usergroup_9 foreign key (usergroup_id) references usergroup (id) on delete restrict on update restrict;
create index ix_menu_usergroup_9 on menu (usergroup_id);
alter table menu_user add constraint fk_menu_user_user_10 foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_menu_user_user_10 on menu_user (user_id);
alter table menu_user add constraint fk_menu_user_menu_11 foreign key (menu_id) references menu (id) on delete restrict on update restrict;
create index ix_menu_user_menu_11 on menu_user (menu_id);
alter table payment add constraint fk_payment_user_12 foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_payment_user_12 on payment (user_id);
alter table payment add constraint fk_payment_usergroup_13 foreign key (usergroup_id) references usergroup (id) on delete restrict on update restrict;
create index ix_payment_usergroup_13 on payment (usergroup_id);
alter table schedule add constraint fk_schedule_user_14 foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_schedule_user_14 on schedule (user_id);
alter table schedule add constraint fk_schedule_ordertype_15 foreign key (ordertype_id) references ordertype (id) on delete restrict on update restrict;
create index ix_schedule_ordertype_15 on schedule (ordertype_id);
alter table schedule add constraint fk_schedule_food_16 foreign key (food_id) references food (id) on delete restrict on update restrict;
create index ix_schedule_food_16 on schedule (food_id);
alter table user add constraint fk_user_usergroup_17 foreign key (usergroup_id) references usergroup (id) on delete restrict on update restrict;
create index ix_user_usergroup_17 on user (usergroup_id);
alter table user add constraint fk_user_role_18 foreign key (role_id) references role (id) on delete restrict on update restrict;
create index ix_user_role_18 on user (role_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists address;

drop table if exists comment;

drop table if exists food;

drop table if exists foodorder;

drop table if exists foodtype;

drop table if exists menu;

drop table if exists menu_user;

drop table if exists ordertype;

drop table if exists payment;

drop table if exists restaurant;

drop table if exists role;

drop table if exists schedule;

drop table if exists user;

drop table if exists usergroup;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists comment_seq;

drop sequence if exists food_seq;

drop sequence if exists foodorder_seq;

drop sequence if exists foodtype_seq;

drop sequence if exists menu_seq;

drop sequence if exists menu_user_seq;

drop sequence if exists ordertype_seq;

drop sequence if exists payment_seq;

drop sequence if exists restaurant_seq;

drop sequence if exists role_seq;

drop sequence if exists schedule_seq;

drop sequence if exists user_seq;

drop sequence if exists usergroup_seq;

