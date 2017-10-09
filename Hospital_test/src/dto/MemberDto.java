package dto;

import java.io.Serializable;

public class MemberDto implements Serializable{
	
/*	
 drop table member
cascade constraints;

create table member(
			  ID VARCHAR2(15) primary key,
			  PW VARCHAR2(20) NOT NULL,
			  NAME VARCHAR2(10) NOT NULL,
			  EMAIL VARCHAR2(30) NOT NULL,
			  PHONE VARCHAR2(20) NOT NULL,
			  AUTH NUMBER(1) NOT NULL);
	
	ALTER TABLE MEMBER
	ADD( CONSTRAINT PW_MEMBER_CHK CHECK(LENGTH(PW)>=8 ));
*/
	private String id;
	private String pw;
	private String name;
	private String email;
	private String phone;
	private int auth;
	
	
	public MemberDto() {}
	
	public MemberDto(String id, String pw, String name, String email, String phone, int auth) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.auth = auth;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getPw() {
		return pw;
	}


	public void setPw(String pw) {
		this.pw = pw;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public int getAuth() {
		return auth;
	}


	public void setAuth(int auth) {
		this.auth = auth;
	}

	@Override
	public String toString() {
		return "memberdto [id=" + id + ", pw=" + pw + ", name=" + name + ", email=" + email + ", phone=" + phone
				+ ", auth=" + auth + "]";
	}
	
	
	
	

	

}
