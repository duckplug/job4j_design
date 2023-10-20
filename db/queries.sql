CREATE TABLE customers(
    id serial primary key,
    first_name text,
    last_name text,
    age int,
    country text
);

CREATE TABLE orders(
    id serial primary key,
    amount int,
    customer_id int references customers(id)
);

insert into customers (first_name, last_name, age, country) values
('Иван', 'Иванов', 25, 'Россия'),
('Артем', 'Петров', 33, 'Россия'),
('Даниил', 'Данилов', 40, 'Дания'),
('NotIn', 'NotInov', 11, 'NOT'); 

insert into orders (amount, customer_id) values
(1000, 1), (2000, 2), (3000, 3);

select first_name, last_name from customers c where c.id not in (select customer_id from orders); 