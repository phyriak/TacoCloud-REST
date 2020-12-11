insert into type (type) values ('WRAP');
insert into type (type) values ('PROTEIN');
insert into type (type) values ('VEGGIES');
insert into type (type) values ('CHEESE');
insert into type (type) values ('SAUCE');


insert into ingredient ("name", type_id) values ('pszenna', 1);
insert into ingredient ("name", type_id) values ('kukurydziana', 1);
insert into ingredient ("name", type_id) values ('mielona wołowina', 2);
insert into ingredient ("name", type_id) values ('kawałki mięsa', 2);

insert into ingredient ("name", type_id) values ('krojona pomidory', 3);
insert into ingredient ("name", type_id) values ('sałata', 3);
insert into ingredient ("name", type_id) values ('cheddar', 4);
insert into ingredient ("name", type_id) values ('Monterey Jack', 4);
insert into ingredient ("name", type_id) values ('pikantny sos pomidorowy', 5);
insert into ingredient ("name", type_id) values ('śmietana', 5);