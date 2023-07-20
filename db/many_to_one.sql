create table bus(
	id serial primary key,
	number_bus int
);

create table passenger(
	id serial primary key,
	name varchar(255),
	bus_passenger int references bus(id)
);