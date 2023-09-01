select p.name from product p 
join type t on p.type_id = t.id
group by p.name, t.name
having t.name like 'Молоко' or t.name like 'Сыр';