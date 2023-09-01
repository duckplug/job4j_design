select p.name наименование, t.name тип from product p
join type t on t.id = p.type_id
group by p.name, t.name
having t.name = 'Сыр';