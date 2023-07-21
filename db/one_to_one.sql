create table state_number (
	id serial primary key,
	sequence_number int
);
create table car(
	id serial primary key,
	model varchar(255),
	car_number int references state_number(id) unique
);