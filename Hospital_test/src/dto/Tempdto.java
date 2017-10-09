package dto;

public class Tempdto {
	private int day;
	private int time;
	public Tempdto(int day, int time) {
		super();
		this.day = day;
		this.time = time;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "tempdto [day=" + day + ", time=" + time + "]";
	}
	
	

}
