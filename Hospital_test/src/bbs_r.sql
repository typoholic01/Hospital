--------------------------------------------------------------------------------
CREATE TABLE BBS_R(
	BBSCHECK NUMBER(2),
	ID VARCHAR2(15) NOT NULL,
	SEQ NUMBER(5) NOT NULL,
	BOOL NUMBER
);
ALTER TABLE BBS_R
ADD( CONSTRAINT CHK_BBS_R CHECK(BOOL IN (0,1) ) );

DROP TABLE BBS_R
CASCADE CONSTRAINT;

ALTER TABLE BBS_R DROP CONSTRAINT FK_BBS_R_ID;



---------------------------------------------------------------------------------
insert into BBS_A(seq,title,content,rcount,wdate,id,del)
values(SEQ_BBS_A.NEXTVAL,'º´¿ø ÁÖÂ÷ °ø°£¿¡ ´ëÇØ','ÁÖÂ÷°ø°£ÀÌ ¸¹ÀÌÇù¼ÒÇÕ´Ï´Ù',0,sysdate,'hi',1);
--------------------------------------------------------------------------------


select * FROM 

INSERT INTO MEMBER(ID,PW,NAME,EMAIL,PHONE,AUTH)
VALUES('hi','12345678','12345','12345','1234',3);

--------------------------------------------------------------------------------

--------------------------------------------------------------------------------
DROP TABLE BBS_A
CASCADE CONSTRAINT;

DROP TABLE MEMBER
CASCADE CONSTRAINT;

