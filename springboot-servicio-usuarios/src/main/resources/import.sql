insert into usuarios (username, password, enabled, nombre, apellido, email) values ('anthony', '$2a$10$QQEHH0JDx1PK4s26HLIXlehFm7k4BDwkEbrocWq5XBdCYyE3Rz/0W', true, 'Anthony', 'Montes', 'anthony@email.com');
insert into usuarios (username, password, enabled, nombre, apellido, email) values ('admin', '$2a$10$iRq9ck4FGgMjFrFD8Kn6/uKW/vxeLFix4jn9x4OO/H/c9/qXls6sy', true, 'admin', 'admin', 'admin@email.com');

insert into roles (nombre) values ('ROLE_USER');
insert into roles (nombre) values ('ROLE_ADMIN');

insert into usuarios_roles (usuario_id, role_id) values (1, 1);
insert into usuarios_roles (usuario_id, role_id) values (2, 2);
insert into usuarios_roles (usuario_id, role_id) values (2, 1);