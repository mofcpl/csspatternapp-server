insert into user(name, email, password, homePage) values ('Jan', 'jan@jan.pl', 'r4rwefs', 'jan.pl')
insert into user(name, email, password, homePage) values ('Adam', 'adam@adam.pl', 'trerwe', 'adam.pl')


insert into project(author, title, publishDate, data) values (select id from user where name='Jan', 'adam@adam.pl', 'trerwe', 'adam.pl')