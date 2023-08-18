insert into roles(role) values('Admin');

insert into categories(categorie) values('main');
insert into states(state) values('in work');
insert into rules(rule) values('Create items');
insert into roles_rules(role_rules, rule_role) values(1,1);
insert into users(name, user_role) values('Anton', 1);
insert into items(number, item_categorie, item_state, item_users, item) values('1.1', 1, 1, 1, 'First item');
insert into comments(item_comment, comment) values(1, 'Comment to first item');
insert into attachs(item_attach, file) values(1, 'Photo');
