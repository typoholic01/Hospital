CREATE TABLE BBS_A(
	    SEQ NUMBER(5) PRIMARY KEY,
	    TITLE VARCHAR2(50) NOT NULL,
	    CONTENT VARCHAR2(100) NOT NULL,
	    RCOUNT NUMBER(5) NOT NULL,
	    RECO NUMBER(2),
	    WDATE DATE NOT NULL,
	    ID VARCHAR2(15) NOT NULL,
	    DEL NUMBER(2) NOT NULL
	);
create sequence SEQ_BBS_A
start with 1 increment by 1;

drop sequence seq_bbs_a

alter table BBS_A
add constraint FK_BBS_A_ID foreign key(ID)
references MEMBER(ID);

insert into BBS_A(seq,title,content,rcount,wdate,id,del)
values(SEQ_BBS_A.NEXTVAL,'병원 주차 공간에 대해','주차공간이 많이협소합니다',0,sysdate,'1234',1);

create table MEMBER(
  ID VARCHAR2(15) PRIMARY KEY,
  PW VARCHAR2(20) NOT NULL,
  NAME VARCHAR2(10) NOT NULL,
  EMAIL VARCHAR2(30) NOT NULL,
  PHONE VARCHAR2(20) NOT NULL,
  AUTH NUMBER(1) NOT NULL
);

select * FROM 

INSERT INTO MEMBER(ID,PW,NAME,EMAIL,PHONE,AUTH)
VALUES('hi','12345678','12345','12345','1234',3);

--------------------------------------------------------------------------------
ALTER TABLE MEMBER
ADD( CONSTRAINT PW_MEMBER_CHK CHECK(LENGTH(PW)>=8 ));
--------------------------------------------------------------------------------
DROP TABLE BBS_A
CASCADE CONSTRAINT;

DROP TABLE MEMBER
CASCADE CONSTRAINT;

SELECT * FROM MEMBER;
SELECT * FROM BBS_A;

ALTER TABLE member
rename column pwD to PW;

SELECT COLUMN_NAME, DATA_TYPE, DATA_LENGTH, NULLABLE 
FROM USER_TAB_COLUMNS 
WHERE TABLE_NAME='MEMBER' ORDER BY COLUMN_ID;
