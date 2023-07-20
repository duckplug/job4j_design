create table animal(
	id serial primary key,
	name varchar(255)
);

create table area(
	id serial primary key,
	country varchar(255)
);

create table areal_animals(
	id serial primary key,
	animal_area int references area(id),
	area_animal int references animal(id)
);
	