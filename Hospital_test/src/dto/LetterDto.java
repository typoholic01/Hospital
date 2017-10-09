package dto;

public class LetterDto {

	private int seq;
	private String WDATE;
	private String CONTENT;
	private String WRITER_ID;
	private String USER_ID;
	private int ISREAD;
	private int DEL;
	
	public LetterDto() {
		
	}

	public LetterDto(int seq, String wDATE, String cONTENT, String wRITER_ID, String uSER_ID, int iSREAD, int dEL) {
		super();
		this.seq = seq;
		WDATE = wDATE;
		CONTENT = cONTENT;
		WRITER_ID = wRITER_ID;
		USER_ID = uSER_ID;
		ISREAD = iSREAD;
		DEL = dEL;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getWDATE() {
		return WDATE;
	}

	public void setWDATE(String wDATE) {
		WDATE = wDATE;
	}

	public String getCONTENT() {
		return CONTENT;
	}

	public void setCONTENT(String cONTENT) {
		CONTENT = cONTENT;
	}

	public String getWRITER_ID() {
		return WRITER_ID;
	}

	public void setWRITER_ID(String wRITER_ID) {
		WRITER_ID = wRITER_ID;
	}

	public String getUSER_ID() {
		return USER_ID;
	}

	public void setUSER_ID(String uSER_ID) {
		USER_ID = uSER_ID;
	}

	public int getISREAD() {
		return ISREAD;
	}

	public void setISREAD(int iSREAD) {
		ISREAD = iSREAD;
	}

	public int getDEL() {
		return DEL;
	}

	public void setDEL(int dEL) {
		DEL = dEL;
	}
	
	@Override
	public String toString() {
		return "LetterDto [seq=" + seq + ", WDATE=" + WDATE + ", CONTENT=" + CONTENT + ", WRITER_ID=" + WRITER_ID
				+ ", USER_ID=" + USER_ID + ", ISREAD=" + ISREAD + ", DEL=" + DEL + "]";
	}

}
