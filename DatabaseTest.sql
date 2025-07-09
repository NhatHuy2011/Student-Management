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
    publicId varchar(255),
    roleId int NOT NULL,
    primary key (id),
    foreign key (roleId) references roles(id)
);

insert into users (username, `password`, fullname, email, dob, sex, avatar, publicId, roleId) 
values ('admin', 'admin', 'NGUYEN NHAT HUY', 'nhathuy123@gmail.com', '2000-01-01', 'MALE', 'https://res.cloudinary.com/dvyvp4n4p/image/upload/v1734758414/gjpj6uznhm9olwo67esd.png', 'gjpj6uznhm9olwo67esd', '1');

insert into users (username, `password`, fullname, email, dob, sex, avatar, roleId) 
values ('minh123', '123456', 'NGUYEN NHAT MINH', 'nhatminh123@gmail.com', '2005-01-01', 'MALE', 'https://res.cloudinary.com/dvyvp4n4p/image/upload/v1752047224/uploads/1752047688835.png','uploads/1752047688835', '2');

create table roles (
	id int NOT NULL AUTO_INCREMENT,
    `role` varchar(10) NOT NULL,
    `description` varchar(255),
    primary key (id)
);

insert into roles (`role`, `description`) values ('ADMIN', 'Role For ADMIN');
insert into roles (`role`, `description`) values ('USER', 'Role For USER');

##################    TRIGGER   ##########################
DELIMITER //

CREATE TRIGGER before_insert_user
BEFORE INSERT ON users
FOR EACH ROW
BEGIN
    IF EXISTS (SELECT 1 FROM users WHERE username = NEW.username) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Username đã tồn tại!';
    END IF;

    IF EXISTS (SELECT 1 FROM users WHERE email = NEW.email) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Email đã tồn tại!';
    END IF;
END //

DELIMITER ;

DELIMITER //

CREATE TRIGGER before_update_user
BEFORE UPDATE ON users
FOR EACH ROW
BEGIN
    IF EXISTS (
        SELECT 1 FROM users 
        WHERE username = NEW.username AND id != OLD.id
    ) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Username đã tồn tại!';
    END IF;

    IF EXISTS (
        SELECT 1 FROM users 
        WHERE email = NEW.email AND id != OLD.id
    ) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Email đã tồn tại!';
    END IF;
END //

DELIMITER ;

