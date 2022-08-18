delete from club_db.public.users where id > 0;
delete from club_db.public.club where id > 0;

insert into club(id, branch_name, name) VALUES (1,'Türkkonut','Anadolu Efes');

insert into users(id,first_name, last_name,email, password, club_id) VALUES (1,'Mert','Süerkan','mmert.suerkan@gmail.com','123213',1);