select  t.name, count(*) from product p
join type t on p.type_id = t.id
group by t.name;