create table courses(
id serial primaty key,
object varchar(255)
);

create students(
is serial primary key,
name varchar(255),
id_object int references courses(id)
);

select st.name, st.id_object from students as st join courses on st.id_object = courses.id;
select st.name, st.id_object, cs.object from students as st join courses as cs on st.id_object = cs.id;
select cs.object, cs.duration, st.id_object from students as st join courses as cs on st.id_object = cs.id;
select st.name as Имя, cs.object as Предмет from students st inner join courses cs on st.id_object = cs.id;