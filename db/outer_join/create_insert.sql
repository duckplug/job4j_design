create table departments(
id serial primary key,
name varchar(255)
);

create table employees(
id serial primary key,
name varchar(255),
id_employee int references departments(id)
);

insert into departments (name) values ('sales');
insert into departments (name) values ('stock');
insert into departments (name) values ('logistics');
insert into departments (name) values('lawyers');
insert into employees (name, id_employee) values ('junior manager', 1);
insert into employees (name, id_employee) values ('manager', 1);
insert into employees (name, id_employee) values ('senior managerr', 1);
insert into employees (name, id_employee) values ('storekeeper', 2);
insert into employees (name, id_employee) values ('senior storekeeper', 2);
insert into employees (name, id_employee) values ('logistician', 3);
