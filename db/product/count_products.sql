select t.name, count(*) from product p 
join type t on t.id = p.type_id
group by t.name
having (count(*)) < 3;