package dto;

public class PreorderDto {
	
	private String doct_name;
	private int scejul_month;
	private int scejul_day;
	private int scejul_time;
	private String diction;
	private String getPreorder_date;
	private String user_id;
	
	
	public PreorderDto(String doct_name, int scejul_month, int scejul_day, int scejul_time, String diction,
			String getPreorder_date, String user_id) {
		super();
		this.doct_name = doct_name;
		this.scejul_month = scejul_month;
		this.scejul_day = scejul_day;
		this.scejul_time = scejul_time;
		this.diction = diction;
		this.getPreorder_date = getPreorder_date;
		this.user_id = user_id;
	}
	
	
	public String getDoct_name() {
		return doct_name;
	}
	public void setDoct_name(String doct_name) {
		this.doct_name = doct_name;
	}
	public int getScejul_month() {
		return scejul_month;
	}
	public void setScejul_month(int scejul_month) {
		this.scejul_month = scejul_month;
	}
	public int getScejul_day() {
		return scejul_day;
	}
	public void setScejul_day(int scejul_day) {
		this.scejul_day = scejul_day;
	}
	public int getScejul_time() {
		return scejul_time;
	}
	public void setScejul_time(int scejul_time) {
		this.scejul_time = scejul_time;
	}
	
	public String getDiction() {
		return diction;
	}
	public void setDiction(String diction) {
		this.diction = diction;
	}
	public String getGetPreorder_date() {
		return getPreorder_date;
	}
	public void setGetPreorder_date(String getPreorder_date) {
		this.getPreorder_date = getPreorder_date;
	}
	@Override
	public String toString() {
		return "PreorderDto [doct_name=" + doct_name + ", scejul_month=" + scejul_month + ", scejul_day=" + scejul_day
				+ ", scejul_time=" + scejul_time + ", diction=" + diction + ", getPreorder_date=" + getPreorder_date
				+ "]";
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	
	

}
