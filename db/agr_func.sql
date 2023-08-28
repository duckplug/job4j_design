create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into people(name) values ('Иван');
insert into people(name) values ('Сергей');
insert into people(name) values ('Павел');
insert into devices(name, price) values ('Bike', 25000);
insert into devices(name, price) values ('Computer', 12000);
insert into devices(name, price) values ('Phone', 80000);
insert into devices_people(device_id, people_id) values (1, 1);
insert into devices_people(device_id, people_id) values (2, 2);
insert into devices_people(device_id, people_id) values (3, 3);
insert into devices_people(device_id, people_id) values (1, 2);
insert into devices_people(device_id, people_id) values (1, 3);
insert into devices_people(device_id, people_id) values (3, 3);
insert into devices_people(device_id, people_id) values (2, 1);

select pp.name, avg(price) from devices_people as dp
join devices ds on ds.id = dp.device_id
join people pp on pp.id = dp.people_id
group by pp.name
having avg(ds.price) > 19000;


