create table user ( idUser varchar(40) primary key, name varchar(30) not null, surname varchar(30) not null, patronymic varchar(30), sex varchar(30));
create table account ( idAccount varchar(40) primary key, number long not null, balance long not null, idUser varchar(40), foreign key (idUser) references user(idUser) on delete cascade);
