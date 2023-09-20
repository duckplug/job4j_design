create or replace function taxed()
	returns trigger as
$tax_trigger$
--налог на стоймость продукта 13%
	BEGIN
		update products
		set price = price * 1
		where id = (select id from tax_table);
		return NEW;
	END;
$tax_trigger$
LANGUAGE 'plpgsql';

--триггер апдейта, увеличение цены + 13%
create trigger tax_trigger
after insert on products
referencing new table as tax_table
for each statement
execute procedure taxed();


--Налог на стоймость до вставки
create trigger tax_after
before insert on products
for each row
execute function tax_after_row();

create or replace function tax_after_row ()
	returns trigger as
$$
BEGIN
	update products
	set price = price * 1.3
	where id = new.id;
	return new;
END;
$$
LANGUAGE 'plpgsql';