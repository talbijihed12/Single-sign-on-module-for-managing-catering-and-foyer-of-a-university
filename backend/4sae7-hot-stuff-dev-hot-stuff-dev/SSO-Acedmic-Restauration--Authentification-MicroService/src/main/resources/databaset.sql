insert into roles (id,name) values (1,'ROLE_ADMIN') on conflict(id) do nothing;
insert into roles (id,name) values (2,'ROLE_ADMIN_RESTEAU') on conflict(id) do nothing;
insert into roles (id,name) values (3,'ROLE_ADMIN_FOYER') on conflict(id) do nothing;
insert into roles (id,name) values (4,'ROLE_ADMIN_FORUM') on conflict(id) do nothing;
insert into roles (id,name) values (6,'ETUDIANT') on conflict(id) do nothing;
insert into roles (id,name) values (7,'PERSONNEL') on conflict(id) do nothing;
insert into roles (id,name) values (8,'PROFESSEUR') on conflict(id) do nothing;