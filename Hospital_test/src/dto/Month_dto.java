package dto;

public class Month_dto {
	
	private int month;
	private int x;
	private int last;
	public Month_dto(int month, int x, int last) {
		super();
		this.month = month;
		this.x = x;
		this.last = last;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getLast() {
		return last;
	}
	public void setLast(int last) {
		this.last = last;
	}
	@Override
	public String toString() {
		return "dal_dto [dal=" + month + ", x=" + x + ", last=" + last + "]";
	}
	
	

}
