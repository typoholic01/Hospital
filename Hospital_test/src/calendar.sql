create table calendar(
	month number primary key,
	x number,	
	last number
);

insert into calendar(month,x,last) values(1, 30, 31);
insert into calendar(month,x,last) values(2, 240, 28);
insert into calendar(month,x,last) values(3, 240, 31);
insert into calendar(month,x,last) values(4, 450, 30);
insert into calendar(month,x,last) values(5, 100, 31);
insert into calendar(month,x,last) values(6, 310, 30);
insert into calendar(month,x,last) values(7, 450, 31);
insert into calendar(month,x,last) values(8, 170, 31);
insert into calendar(month,x,last) values(9, 380, 30);
insert into calendar(month,x,last) values(10, 30, 31);
insert into calendar(month,x,last) values(11, 240, 30);
insert into calendar(month,x,last) values(12, 380, 31);

select * from calendar

