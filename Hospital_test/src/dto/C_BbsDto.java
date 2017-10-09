package dto;

import java.io.Serializable;

public class C_BbsDto implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	//멤버변수
	private int seq; //게시판번호
	private int orderNum; //글 순서
	private int step; //답글 순서
	private int depth; //답글의 답글인지 파악(0: 댓글, 1:댓댓글, 2:댓댓댓글 ....)
	private String title; //게시판제목
	private String content; //게시판내용
	private int rcount; //조회수
	private int reco; //추천수
	private int secreat; //비밀글 (0:비밀글, 1:공개글)
	private String wdate; //게시일
	private String id; //아이디
	private int del; //게시판의 삭제여부(0:삭제됨, 1:존재함)
	
	//생성자
	public C_BbsDto() {}

	public C_BbsDto(int seq, int orderNum, int step, int depth, String title, String content, int rcount, 
			int reco, int secreat, String wdate, String id, int del) 
	{
		this.seq = seq;
		this.orderNum = orderNum;
		this.step = step;
		this.depth = depth;
		this.title = title;
		this.content = content;
		this.rcount = rcount;
		this.reco = reco;
		this.secreat = secreat;
		this.wdate = wdate;
		this.id = id;
		this.del = del;
	}
	

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}
	
	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}
	
	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getRcount() {
		return rcount;
	}

	public void setRcount(int rcount) {
		this.rcount = rcount;
	}

	public int getReco() {
		return reco;
	}

	public void setReco(int reco) {
		this.reco = reco;
	}

	public int getSecreat() {
		return secreat;
	}

	public void setSecreat(int secreat) {
		this.secreat = secreat;
	}

	public String getWdate() {
		return wdate;
	}

	public void setWdate(String wdate) {
		this.wdate = wdate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "C_BbsDto [seq=" + seq + ", orderNum=" + orderNum + 
		", step=" + step + ", depth=" + depth + ", title="
		+ title + ", content=" + content + ", rcount=" + 
		rcount + ", reco=" + reco + ", secreat=" + secreat
		+ ", wdate=" + wdate + ", id=" + id + ", del=" + del + "]";
	}

	

	

	
	
	
	

}
