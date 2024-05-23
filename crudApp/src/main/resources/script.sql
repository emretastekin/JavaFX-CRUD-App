create database crud;
use crud;

create table students(
id int auto_increment primary key,
BookName varchar(20) not null,
Author varchar(20) not null,
PAGENUMBER varchar(20) not null,
Price varchar(20) not null
);

insert into students(BookName,Author,PAGENUMBER,Price) Values('Babalar ve Oğullar','Ivan Sergeyevic','236','55');