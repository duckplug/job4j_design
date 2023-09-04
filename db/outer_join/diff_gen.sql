create table teens(
id serial primary key,
name varchar(255),
gender varchar(255),
);

insert into teens(name, gender) values('Bob', 'male');
insert into teens(name, gender) values('Dan', 'male');
insert into teens(name, gender) values('Arnold', 'male');
insert into teens(name, gender) values('Kate', 'female');
insert into teens(name, gender) values('Rose', 'female');
insert into teens(name, gender) values('Ann', 'female');

select t1.name, t2.name, t1.gender, t2.gender from teens t1 cross join teens t2 where t1.gender not like t2.gender;