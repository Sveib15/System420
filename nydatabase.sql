create table module(
module_id int not null,
description varchar(1000),
rights int,
primary key(module_id)
);

create table resource(
resource_id int not null,
module_id int, 
description varchar(100),
primary key(resource_id),
foreign key(module_id) references module(module_id)
);

create table requirement(
requirement_id int not null,
module_id int,
description varchar(100),
primary key(requirement_id),
foreign key(module_id) references module(module_id)
);

create table blog(
blog_id int not null,
primary key(blog_id)
);

create table blog_post(
blog_post_id int not null,
blog_id int,
date date,
title varchar(100),
content varchar(1000),
primary key(blog_post_id),
foreign key(blog_id) references blog(blog_id)
);

create table semester_plan(
semester_plan_id int not null,
primary key(semester_plan_id)
);

create table module_plan(
module_plan_id int not null,
semester_plan_id int,
module_id int,
planned_date date,
primary key(module_plan_id),
foreign key(semester_plan_id) references semester_plan(semester_plan_id),
foreign key(module_id) references module(module_id)
);

create table user(
user_id int,
first_name varchar(100),
last_name varchar(100),
email varchar(100),
phone_number int,
rights int,
blog_id int,
semester_plan_id int,
title varchar(100),
primary key(user_id),
foreign key(blog_id) references blog(blog_id),
foreign key(semester_plan_id) references semester_plan(semester_plan_id)
);

create table delivery(
delivery_id int not null,
module_id int,
user_id int,
status boolean,
comment varchar(500),
date_delivered date,
date_approved date,
que_number int,
primary key(delivery_id),
foreign key(module_id) references module(module_id),
foreign key(user_id) references user(user_id)
);

create table file(
file_id int not null,
delivery_id int,
content longblob,
primary key(file_id),
foreign key(delivery_id) references delivery(delivery_id)
);

#TESTDATA
insert into module(module_id, description, rights) values(1, "Testmodul", 2);
insert into resource(resource_id, module_id, description) values(1, 1, "www.test.no");
insert into requirement(requirement_id, module_id, description) values(1, 1, "Læremål1");
insert into blog(blog_id) values(1);
insert into blog_post(blog_post_id, blog_id, date, title, content) values(1, 1, 20161005, "Bloginnlegg 1", "Dette er en test");
insert into semester_plan(semester_plan_id) values(1);
insert into module_plan(module_plan_id, semester_plan_id, module_id, planned_date) values(1, 1, 1, 20161005);
insert into user(user_id, first_name, last_name, email, phone_number, rights, blog_id, semester_plan_id, title) values(1, "Ådne", "Pådne", "pådne@mail.com", 911, 1, 1, 1, null);
insert into delivery(delivery_id, module_id, user_id, status, comment, date_delivered, date_approved, que_number) values(1, 1, 1, false, "Test", 20161005, 20161005, 1);
insert into file(file_id, delivery_id, content) values(1, 1, 1110010110100011100101);








