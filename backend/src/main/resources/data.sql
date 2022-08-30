delete from club_db.public.student_parents where student_parents.student_id > 0 and student_parents.parents_id > 0;;
delete from club_db.public.student where id > 0;
delete from club_db.public.parent where id > 0;

delete from club_db.public.groups where id > 0;
delete from club_db.public.trainer where id > 0;
delete from club_db.public.coordinator where id > 0;

delete from club_db.public.branch where id > 0;
delete from club_db.public.inventory where id > 0;
delete from club_db.public.club where id > 0;

insert into club(id, name) VALUES (1,'Anadolu Efes');
insert into inventory(id,name)VALUES(1,'Türkkonut-Efes-Inventory');
insert into inventory(id,name)VALUES(2,'Incek-Efes-Inventory');
insert into branch(id,name, club_id, inventory_id) VALUES (1,'Türkkonut-Efes',1,1);
insert into branch(id,name, club_id, inventory_id) VALUES (2,'Incek-Efes',1,2);


insert into coordinator(id, club_id, name, surname) VALUES (1,1,'Gökhan','Dönmez');
insert into trainer(id, branch_id, coordinator_id, name, surname) VALUES (1,1,1,'Özge','Koç');


insert into groups(id, group_name, branch_id) VALUES (1,'12-13 Group',1);

insert into parent(id, name, surname) VALUES (1,'Ahmet','Caglayan');
insert into parent(id, name, surname) VALUES (2,'Ayse','Caglayan');
insert into student(id, group_id, age, height, is_active, name, surname, weight) VALUES (1,1,12,1.59,true,'Ali','Caglayan',35);

insert into student_parents(student_id, parents_id)values (1,1);
insert into student_parents(student_id, parents_id)values (1,2);
