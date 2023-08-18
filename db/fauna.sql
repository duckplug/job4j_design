create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date) values('red_fish', 1000, '1930-02-01');
insert into fauna(name, avg_age, discovery_date) values('green_fish', 2000, '1970-09-01');
insert into fauna(name, avg_age, discovery_date) values('yellow_fish', 1000, '1900-05-01');
insert into fauna(name, avg_age, discovery_date) values('bear', 15000, '1600-03-02');
insert into fauna(name, avg_age, discovery_date) values('snake', 10000, '1700-01-01');
insert into fauna(name, avg_age, discovery_date) values('big_snail', 100, null);

select*from fauna where name like '%fish%';
select*from fauna where avg_age > 10000 and avg_age < 21000;
select*from fauna where discovery_date isnull;
select*from fauna where discovery_date < '01-01-1950';
