create table member(
	id varchar2(15) primary key,
	pw varchar2(20),
	name varchar2(10),
	email varchar2(30),
	phone varchar2(20),
	AUTH number(1)
);

drop table member
cascade constraint;

ALTER TABLE member
ADD(CONSTRAINT ID_PK PRIMARY KEY(ID));

ALTER TABLE MEMBER
ADD( CONSTRAINT PW_MEMBER_CHK CHECK(LENGTH(PW)>=8 ));

Select * from member;

SELECT ID,PW FROM MEMBER
WHERE ID = '가'
AND PW = '다';

-- insert 3개

-- delete -> 25살 이하 삭제

-- 8자 이상 제한
ALTER TABLE MEMBER
ADD( CONSTRAINT PW_MEMBER_CHK CHECK(LENGTH(PW)>=8 ));

ALTER TABLE member
rename column pw to pwd;

alter table member add(AUTH number(10));
alter table member ADD(EMAIL VARCHAR2(30));

delete from member where id='seung' and pw='123123123';


update member 
set auth = 0 
where id = 'doct1';
update member 
set auth = 0 
where id = 'doct2';
update member 
set auth = 0 
where id = 'doct3';
update member 
set auth = 0 
where id = 'doct4';
