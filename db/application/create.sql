create table roles( 
id serial primary key,
role varchar(255)
);

create table categories(
id serial primary key,
categorie varchar(255)
);

create table states(
id serial primary key,
state varchar(255)
);

create table rules( 
id serial primary key,
rule varchar(255)
);

create table roles_rules( 
id serial primary key,
role_rules int references roles(id),
rule_role int references rules(id)
);

create table users(
id serial primary key,
name varchar(255),
user_role int references roles(id)
);

create table items(
id serial primary key,
number varchar(255),
item_categorie int references categories(id),
item_state int references states(id),
item_users int references users(id),
item text
);

create table comments(
id serial primary key,
item_comment int references items(id),
comment text
);

create table attachs(
id serial primary key,
item_attach int references items(id),
file text
);