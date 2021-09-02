-- 创建数据库
create database if not exists sgg_spring5_user_db
default character set utf8mb4
default collate utf8mb4_general_ci;

use sgg_spring5_user_db;

create table if not exists t_account(
	id int not null auto_increment,
	username varchar(100),
	money int,
	primary key(id)
);

truncate t_account;
INSERT INTO `t_account`(`id`, `username`, `money`) VALUES (null, 'lucy', 1000);
INSERT INTO `t_account`(`id`, `username`, `money`) VALUES (null, 'mary', 1000);
