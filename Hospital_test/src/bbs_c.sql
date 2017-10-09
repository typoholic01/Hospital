CREATE TABLE BBS_C(
    SEQ NUMBER(5) PRIMARY KEY,
    ORDERNUM NUMBER(5) NOT NULL,
    STEP NUMBER(5) NOT NULL,
    DEPTH NUMBER(5) NOT NULL,
    TITLE VARCHAR2(50) NOT NULL,
    CONTENT VARCHAR2(100) NOT NULL,
    RCOUNT NUMBER(5) NOT NULL,
    RECO NUMBER(5) NOT NULL,
    SECREAT NUMBER(2) NOT NULL,
    WDATE DATE NOT NULL,
    ID VARCHAR2(15) NOT NULL,
    DEL NUMBER(2) NOT NULL
    
);
--------------------------------------------------------------------------------
create sequence SEQ_BBS_C
start with 1 increment by 1; 

CREATE SEQUENCE STEP_BBS_C
START WITH 1 INCREMENT BY 1;

SELECT STEP_BBS_C.NEXTVAL
FROM DUAL;

SELECT STEP_BBS_C.CURRVAL
FROM DUAL;

alter table BBS_C 
add constraint FK_BBS_C_ID foreign key(ID)
references MEMBER(ID);

alter table BBS_C drop constraint FK_BBS_C_ID

--------------------------------------------------------------------------------
insert into BBS_C(seq,ORDERNUM,STEP,title,content,rcount,secreat,wdate,id,del)
values(SEQ_BBS_C.NEXTVAL,SEQ_BBS_C.NEXTVAL, STEP_BBS_C.NEXTVAL ,'병원 주차 공간에 대해','주차공간이 많이협소합니다',0,1,sysdate,'hi2',1);

--------------------------------------------------------------------------------
DROP TABLE BBS_C
CASCADE CONSTRAINT;

DROP SEQUENCE SEQ_BBS_C;
DROP SEQUENCE STEP_BBS_C;


select * from BBS_C WHERE ID='hi' ORDER BY ORDERNUM DESC, STEP, DEPTH;
SELECT COUNT(*) FROM BBS_C GROUP BY SEQ;

insert into MEMBER (AUTH,PHONE,EMAIL,NAME,PW,ID)
VALUES(1,'010-8619-8323','skk0624@naver.com','기승간','hi','hi');

insert into MEMBER (ID,PW,NAME,EMAIL,PHONE,AUTH)
VALUES('hi2','111111111','111','111','111',0);

SELECT * FROM (SELECT ROWNUM NUMROW , aa.* FROM ( SELECT * FROM BBS_C ORDER BY ORDERNUM DESC, STEP, DEPTH ) aa ) WHERE NUMROW > 17 and NUMROW <= 34;

SELECT SEQ,ORDERNUM,STEP,DEPTH, TITLE, CONTENT, RCOUNT, 
	   RECO, SECREAT, WDATE, ID, DEL
		FROM (SELECT ROWNUM NUMROW, AA.* FROM ( SELECT * FROM BBS_C 
		ORDER BY ORDERNUM DESC, STEP ASC, DEPTH ASC ) AA )
		WHERE  NUMROW >= 1 AND NUMROW <= 17;
				
SELECT COUNT(*) AS COUNT FROM BBS_C WHERE id ='hi';

select * from member;
select * from BBS_c;

delete from BBS_A;
delete from BBS_c WHERE SEQ = 3;
delete from BBS_r;
