create table delivery(
modulID varchar(100),
email varchar(100),
deliveredDate varchar(100),
comment varchar(9999),
foreign key(modulID) references modul(modulID),
foreign key(email) references user(email)
);

create table files(
name varchar(100),
email varchar(100),
modulID varchar(100),
foreign key(email) references user(email),
foreign key(modulID) references modul(modulID)
);

create table user(
firstname varchar(100),
lastname varchar(100),
email varchar(100),
rights int,
primary key(email)
);

