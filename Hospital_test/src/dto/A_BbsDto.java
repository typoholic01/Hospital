package dto;

import java.io.Serializable;


public class A_BbsDto implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	//멤버변수
	private int seq; //게시판번호
	private String title; //게시판제목
	private String content; //게시판내용
	private int rcount; //조회수
	private int reco; //추천수
	private String wdate; //게시일
	private String id; //아이디
	private int del; 
	
	
	//생성자
	public A_BbsDto() {}

	public A_BbsDto(int seq, String title, String content, 
			int rcount,int reco, String wdate, String id, int del) 
	{
		this.seq = seq;
		this.title = title;
		this.content = content;
		this.rcount = rcount;
		this.reco = reco;
		this.wdate = wdate;
		this.id = id;
		this.del = del;
	}

	//setter와 getter
	public int getSeq()
	{
		return seq;
	}

	public void setSeq(int seq) 
	{
		this.seq = seq;
	}

	public String getTitle() 
	{
		return title;
	}

	public void setTitle(String title) 
	{
		this.title = title;
	}

	public String getContent() 
	{
		return content;
	}

	public void setContent(String content) 
	{
		this.content = content;
	}

	public int getRcount() 
	{
		return rcount;
	}

	public void setRcount(int rcount) 
	{
		this.rcount = rcount;
	}

	
	public int getReco() {
		return reco;
	}

	public void setReco(int reco) {
		this.reco = reco;
	}

	public String getWdate() 
	{
		return wdate;
	}

	public void setWdate(String wdate) 
	{
		this.wdate = wdate;
	}

	public String getId() 
	{
		return id;
	}

	public void setId(String id) 
	{
		this.id = id;
	}

	public int getDel() 
	{
		return del;
	}

	public void setDel(int del) 
	{
		this.del = del;
	}

	//toString
	@Override
	public String toString() 
	{
		return "A_BbsDto [seq=" + seq + ", title=" + title + 
				", content=" + content + ", rcount=" + rcount +
				", reco="
				+ reco + ", wdate=" + wdate + ", id=" + id + 
				", del=" + del + "]";
	}

	

	
	
	
	
	
	
	
	
}

