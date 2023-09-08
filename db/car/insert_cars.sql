insert into car_bodies(name) values ('bod1');
insert into car_bodies(name) values ('bod2');
insert into car_bodies(name) values ('bod3');
insert into car_bodies(name) values ('bod_no_link');

insert into car_engines(name) values ('e-1');
insert into car_engines(name) values ('e-2');
insert into car_engines(name) values ('e-3');
insert into car_engines(name) values ('engine_no_link');

insert into car_transmissions(name) values ('tr-1');
insert into car_transmissions(name) values ('tr-2');
insert into car_transmissions(name) values ('tr-3');
insert into car_transmissions(name) values ('tr_no_link');


insert into cars (name, body_id, engine_id, transmission_id) values('Car-1', 1, 1, 1);
insert into cars (name, body_id, engine_id, transmission_id) values('Car-2', 2, 2, 2);
insert into cars (name, body_id, engine_id, transmission_id) values('Car-3', 3, 3, 3);
insert into cars (name, body_id, transmission_id) values('Car_ne', 1, 1);
insert into cars (name, body_id, transmission_id) values('Car_tr', 3, 3);
insert into cars (name, engine_id, transmission_id) values('Car-nb', 3, 3);





