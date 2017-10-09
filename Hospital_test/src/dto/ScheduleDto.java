package dto;

public class ScheduleDto {
	private int seq;
	private String user_id;
	private String diction;
	private String doctor_id;
	private String preorder_date;
	private int isend;
	private int del;

	public ScheduleDto() {
	}

	public ScheduleDto(int seq, String user_id, String diction, String doctor_id, String preorder_date, int isend,
			int del) {
		super();
		this.seq = seq;
		this.user_id = user_id;
		this.diction = diction;
		this.doctor_id = doctor_id;
		this.preorder_date = preorder_date;
		this.isend = isend;
		this.del = del;
	}

	@Override
	public String toString() {
		return "ScheduleDto [seq=" + seq + ", user_id=" + user_id + ", diction=" + diction + ", doctor_id=" + doctor_id
				+ ", preorder_date=" + preorder_date + ", isend=" + isend + ", del=" + del + "]";
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getDiction() {
		return diction;
	}

	public void setDiction(String diction) {
		this.diction = diction;
	}

	public String getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(String doctor_id) {
		this.doctor_id = doctor_id;
	}

	public String getPreorder_date() {
		return preorder_date;
	}

	public void setPreorder_date(String preorder_date) {
		this.preorder_date = preorder_date;
	}

	public int getIsend() {
		return isend;
	}

	public void setIsend(int isend) {
		this.isend = isend;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

}
