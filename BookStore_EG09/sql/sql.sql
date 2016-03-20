CREATE DATABASE bookstore_1022;

CREATE TABLE bs_user(
	id INT(11) PRIMARY KEY AUTO_INCREMENT,
	username VARCHAR(120) NOT NULL UNIQUE,
	PASSWORD VARCHAR(120) ,
	email VARCHAR(120)
);

create table bs_book(
	id int(11) primary key auto_increment,
	title varchar(120),
	author varchar(120),
	price double(11,2),
	sales int(11),
	stock int(11),
	img_path varchar(200)
);

CREATE TABLE bs_order(
	id VARCHAR(100) PRIMARY KEY ,
	order_time DATETIME ,
	total_count INT(11),
	total_amount DOUBLE(11,2),
	state INT(2),
	user_id INT(11),
	FOREIGN KEY (user_id) REFERENCES bs_user(id)
);

CREATE TABLE bs_order_item(
	id INT(11) PRIMARY KEY AUTO_INCREMENT,
	COUNT INT(11) ,
	amount DOUBLE(11,2),
	title VARCHAR(100),
	author VARCHAR(100),
	price DOUBLE(11,2),
	img_path VARCHAR(200),
	order_id VARCHAR(100),
	FOREIGN KEY (order_id) REFERENCES bs_order(id)
);