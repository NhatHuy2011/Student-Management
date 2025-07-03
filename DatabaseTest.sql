Create database studentmanagement;

use studentmanagement;

create table users (
	id int NOT NULL AUTO_INCREMENT,
    username varchar(255) NOT NULL,
    `password` varchar(255) NOT NULL,
    fullname varchar(255) NOT NULL,
    email varchar(255) NOT NULL,
    dob date NOT NULL,
    sex enum('MALE', 'FEMALE') NOT NULL,
    avatar varchar(255),
    roleId int NOT NULL,
    primary key (id),
    foreign key (roleId) references roles(id)
);

insert into users (username, `password`, fullname, email, dob, sex, avatar, roleId) 
values ('admin', 'admin', 'NGUYEN NHAT HUY', 'nhathuy123@gmail.com', '2000-01-01', 'MALE', 'https://cdn-icons-png.freepik.com/512/9703/9703596.png', '1');

insert into users (username, `password`, fullname, email, dob, sex, avatar, roleId) 
values ('minh123', '123456', 'NGUYEN NHAT MINH', 'nhatminh123@gmail.com', '2005-01-01', 'MALE', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRV1Mly7C6D_WWpPXTAO4dF52D9Wd9FKuC9zw&s', '2');


create table roles (
	id int NOT NULL AUTO_INCREMENT,
    `role` varchar(10) NOT NULL,
    `description` varchar(255),
    primary key (id)
);

insert into roles (`role`, `description`) values ('ADMIN', 'Role For ADMIN');
insert into roles (`role`, `description`) values ('USER', 'Role For USER');