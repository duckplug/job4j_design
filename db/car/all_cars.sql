select c.id as id, c.name as car_name, b.name as body_name, 
	e.name as engine_name, t.name as transmission_name 
from cars c 
	left join car_bodies b on c.body_id = b.id
	left join car_engines e on c.engine_id = e.id
	left join car_transmissions t on c.transmission_id = t.id;