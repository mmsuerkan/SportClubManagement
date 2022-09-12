delete from club_db.public.student_parents where student_parents.student_id > 0 and student_parents.parent_id > 0;;
delete from club_db.public.student where id > 0;
delete from club_db.public.parent where id > 0;

delete from club_db.public.groups where id > 0;
delete from club_db.public.lessons where id > 0;

delete from club_db.public.trainer where id > 0;
delete from club_db.public.coordinator where id > 0;

delete from club_db.public.branch where id > 0;
delete from club_db.public.inventory where id > 0;
delete from club_db.public.club where id > 0;

insert into club_db.public.club(id, name) VALUES (1,'Anadolu Efes');
insert into club_db.public.inventory(id,name)VALUES(1,'Türkkonut-Efes-Inventory');
insert into club_db.public.inventory(id,name)VALUES(2,'Incek-Efes-Inventory');
insert into club_db.public.branch(id,name, club_id, inventory_id) VALUES (1,'Türkkonut-Efes',1,1);
insert into club_db.public.branch(id,name, club_id, inventory_id) VALUES (2,'Incek-Efes',1,2);


insert into club_db.public.coordinator(id, club_id, name, surname) VALUES (1,1,'Gökhan','Dönmez');
insert into club_db.public.trainer(id, branch_id, coordinator_id, name, surname) VALUES (1,1,1,'Özge','Koç');
insert into club_db.public.trainer(id, branch_id, coordinator_id, name, surname) VALUES (2,1,1,'Gökce','Gecikli');



insert into club_db.public.groups(id, group_name, branch_id) VALUES (1,'12-13 Group',1);
insert into club_db.public.groups(id, group_name, branch_id) VALUES (2,'13-14 Group',1);

insert into club_db.public.parent(id, name, surname) VALUES (1,'Ahmet','Caglayan');
insert into club_db.public.parent(id, name, surname) VALUES (2,'Ayse','Caglayan');

insert into club_db.public.student(id, group_id, age, height, is_active, name, surname, weight) VALUES (1,1,12,1.59,true,'Ali','Caglayan',35);
insert into club_db.public.student(id, group_id, age, height, is_active, name, surname, weight) VALUES (2,1,12,1.59,true,'Ahmet','Caglayan',35);
insert into club_db.public.student(id, group_id, age, height, is_active, name, surname, weight) VALUES (3,2,12,1.59,true,'Ayse','Caglayan',35);



insert into club_db.public.student_parents(student_id, parent_id)values (1,1);
insert into club_db.public.student_parents(student_id, parent_id)values (1,2);

insert into club_db.public.users(id, email, first_name, last_name, password) VALUES (1,'mmert.suerkan@gmail.com','Mert','Süerkan','1231456');

