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
  id                        bigint auto_increment not null,
  user_id                   bigint,
  food_id                   bigint,
  title                     varchar(255),
  content                   varchar(1000),
  constraint pk_comment primary key (id))
;

create table food (
  id                        bigint auto_increment not null,
  foodtype_id               bigint,
  restaurant_id             bigint,
  name                      varchar(100) not null,
  price                     double not null,
  is_available              tinyint(1) default 0,
  ranking                   float(12) not null,
  ranking_vote              bigint,
  constraint pk_food primary key (id))
;

create table foodorder (
  id                        bigint auto_increment not null,
  user_id                   bigint,
  menu_id                   bigint,
  ordertype_id              bigint,
  food_id                   bigint,
  quantity                  bigint not null,
  discount                  double,
  order_at                  datetime,
  deal                      tinyint(1) default 0,
  comments                  varchar(255),
  constraint pk_foodorder primary key (id))
;

create table foodtype (
  id                        bigint auto_increment not null,
  name                      varchar(100) not null,
  constraint pk_foodtype primary key (id))
;

create table menu (
  id                        bigint auto_increment not null,
  usergroup_id              bigint,
  created_at                datetime,
  deal                      tinyint(1) default 0,
  closed_at                 datetime,
  is_available              tinyint(1) default 0,
  constraint pk_menu primary key (id))
;

create table menu_user (
  id                        bigint auto_increment not null,
  user_id                   bigint,
  menu_id                   bigint,
  constraint pk_menu_user primary key (id))
;

create table ordertype (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  constraint pk_ordertype primary key (id))
;

create table payment (
  id                        bigint auto_increment not null,
  user_id                   bigint,
  usergroup_id              bigint,
  amount                    double not null,
  created_at                datetime not null,
  description               varchar(255),
  constraint pk_payment primary key (id))
;

create table restaurant (
  id                        bigint auto_increment not null,
  name                      varchar(100) not null,
  phone_number              varchar(50),
  address                   varchar(255),
  manager                   varchar(50),
  ranking                   float(12),
  ranking_vote              bigint,
  open_at                   datetime,
  delivery_at               datetime not null,
  is_available              tinyint(1) default 0,
  constraint pk_restaurant primary key (id))
;

create table role (
  id                        bigint auto_increment not null,
  name                      varchar(50),
  description               varchar(255),
  constraint pk_role primary key (id))
;

create table schedule (
  id                        bigint auto_increment not null,
  user_id                   bigint,
  ordertype_id              bigint,
  food_id                   bigint,
  start_at                  datetime,
  end_at                    datetime,
  calendar_type             bigint,
  cycle_day                 bigint,
  cycle_times               bigint,
  is_available              tinyint(1) default 0,
  constraint pk_schedule primary key (id))
;

create table user (
  id                        bigint auto_increment not null,
  user_name                 varchar(255),
  full_name                 varchar(255),
  password                  varchar(255),
  email                     varchar(255),
  phone_number              varchar(255),
  money                     double,
  bonus                     bigint,
  account_expired           tinyint(1) default 0,
  account_locked            tinyint(1) default 0,
  account_enabled           tinyint(1) default 0,
  credentials_expired       tinyint(1) default 0,
  reg_at                    datetime,
  last_login                datetime,
  ip                        varchar(255),
  mail_reminder_on          tinyint(1) default 0,
  usergroup_id              bigint,
  role_id                   bigint,
  constraint pk_user primary key (id))
;

create table usergroup (
  id                        bigint auto_increment not null,
  name                      varchar(100),
  im_on                     tinyint(1) default 0,
  open_reg                  tinyint(1) default 0,
  lucky_rule                bigint,
  mail_reminder_on          tinyint(1) default 0,
  menu_auto_close           tinyint(1) default 0,
  bonus_on                  tinyint(1) default 0,
  bonus_pay_ratio           double,
  bonus_order_ratio         double,
  bonus_manage_ratio        double,
  bonus_carry_ratio         double,
  description               varchar(1000),
  constraint pk_usergroup primary key (id))
;

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

SET FOREIGN_KEY_CHECKS=0;

drop table address;

drop table comment;

drop table food;

drop table foodorder;

drop table foodtype;

drop table menu;

drop table menu_user;

drop table ordertype;

drop table payment;

drop table restaurant;

drop table role;

drop table schedule;

drop table user;

drop table usergroup;

SET FOREIGN_KEY_CHECKS=1;

