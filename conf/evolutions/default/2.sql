# --- Add Default Roles
# --- !Ups

INSERT INTO `role` (`id`,`name`,`description`) VALUES (1,'FFF_ADMIN','default admin role');
INSERT INTO `role` (`id`,`name`,`description`) VALUES (2,'FFF_MANAGER','default manager role');
INSERT INTO `role` (`id`,`name`,`description`) VALUES (3,'FFF_USER','default user role');


# --- !Downs

DELETE FROM fff.role where id in (1,2,3);
