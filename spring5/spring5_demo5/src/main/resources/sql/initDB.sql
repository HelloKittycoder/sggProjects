-- 创建数据库
create database if not exists sgg_spring5_user_db
default character set utf8mb4
default collate utf8mb4_general_ci;

create table t_book(
	bookId int,
	bookName varchar(100),
	bookStatus varchar(100)
);