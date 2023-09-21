create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

create or replace procedure p_del()
language 'plpgsql'
as $$
	BEGIN
		delete from products where products.count = 0;
	END
$$;

create or replace function f_del()
returns void
language 'plpgsql'
as
$$
	BEGIN
		delete from products where products.count = 0;
	END;
$$;