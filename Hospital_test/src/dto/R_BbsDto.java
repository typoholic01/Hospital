package dto;

import java.io.Serializable;

//추천 dto
public class R_BbsDto implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	//멤버변수
	private int BbsCheck; //0:BBS_A,  1:BBS_C
	private String id; //id
	private int seq; //글번호
	private int bool; //추천버튼활성(1), 추천취소버튼활성(0)
	
	public R_BbsDto() {}

	public R_BbsDto(int BbsCheck, String id, int seq, int bool) 
	{
		this.BbsCheck =BbsCheck;
		this.id = id;
		this.seq = seq;
		this.bool = bool;
	}

	
	public int getCheck() {
		return BbsCheck;
	}

	public void setCheck(int check) {
		this.BbsCheck = check;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getBool() {
		return bool;
	}

	public void setBool(int bool) {
		this.bool = bool;
	}

	@Override
	public String toString() {
		return "R_BbsDto [BbsCheck=" + BbsCheck + ", id="
	+ id + ", seq=" + seq + ", bool=" + bool + "]";
	}

	


	
	

	
	
	

}
