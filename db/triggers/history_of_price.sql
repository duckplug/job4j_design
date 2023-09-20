-- запись внесения продукта 
create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

create or replace function history()
returns trigger as
$$
	BEGIN
		insert into history_of_price(name, price, date) values (new.name, new.price, now());
		return new;
	END;
$$
LANGUAGE 'plpgsql';

create trigger history_trigger
after insert on products
for each row
execute function history();