create table modul(
modulID varchar(100),
description varchar(9999),
rights int,
primary key(modulID)
);

create table requirement(
reqID int,
description varchar(9999),
modulID varchar(100),
primary key(reqID),
foreign key(modulID) references modul(modulID)
);

create table resource(
resID int,
description varchar(9999),
modulID varchar(100),
primary key(resID),
foreign key(modulID) references modul(modulID)
);