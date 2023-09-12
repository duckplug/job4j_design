create table students (
    id serial primary key,
    name varchar(50)
);
insert into students (name) values ('Иван Иванов');
insert into students (name) values ('Петр Петров');

create table authors (
    id serial primary key,
    name varchar(50)
);
insert into authors (name) values ('Александр Пушкин');
insert into authors (name) values ('Николай Гоголь');

create table company (
	id serial primary key,
	name varchar(100),
	country varchar(100)
	);
insert into company (name, country) values ('BooksWorld', 'Russia');
insert into company (name, country) values ('BooksLight', 'USA');

create table publisher (
    id serial primary key,
    name varchar(50),
	year_pub date,
	id_company int references company(id)
);
insert into publisher (name, year_pub, id_company) values ('MSKpub', '1999-12-01', 1);
insert into publisher (name, year_pub, id_company) values ('USApub', '2020-04-01', 2);

create table books (
    id serial primary key,
    name varchar(200),
    author_id integer references authors(id),
	id_publisher int references publisher(id)
);
insert into books (name, author_id, id_publisher) values ('Евгений Онегин', 1, 1);
insert into books (name, author_id, id_publisher) values ('Капитанская дочка', 1, 2);
insert into books (name, author_id, id_publisher) values ('Дубровский', 1, 1);
insert into books (name, author_id, id_publisher) values ('Мертвые души', 2, 2);
insert into books (name, author_id, id_publisher) values ('Вий', 2, 1);

create table orders (
    id serial primary key,
    book_id integer references books(id),
    student_id integer references students(id)
);
insert into orders (book_id, student_id) values (1, 1);
insert into orders (book_id, student_id) values (3, 1);
insert into orders (book_id, student_id) values (5, 2);
insert into orders (book_id, student_id) values (4, 1);
insert into orders (book_id, student_id) values (2, 2);

select s.name, count(a.name), a.name, c.country from students as s
		 join orders o on s.id = o.student_id
         join books b on o.book_id = b.id
         join authors a on b.author_id = a.id
		 join publisher p on b.id_publisher = p.id
		 join company c on p.id_company = c.id
         group by (s.name, a.name, c.country) having c.country = 'Russia';

create view student_publisher_country_russia 
	as select s.name as students, count(a.name), a.name as authors, c.country as country from students as s
		 join orders o on s.id = o.student_id
         join books b on o.book_id = b.id
         join authors a on b.author_id = a.id
		 join publisher p on b.id_publisher = p.id
		 join company c on p.id_company = c.id
         group by (s.name, a.name, c.country) having c.country = 'Russia';

