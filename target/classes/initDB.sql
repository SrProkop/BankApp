create table user ( idUser varchar(40) primary key, firstName varchar(30) not null, secondName varchar(30) not null, lastName varchar(30), sex varchar(30));
create table account ( idAccount varchar(40) primary key, number long not null, balance Decimal not null, idUser varchar(40), foreign key (idUser) references user(idUser) on delete cascade);
