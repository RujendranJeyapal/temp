package railway;

public class Seat 
{
	
	private String berth;
	private int seatNo;
	public Seat(String berth, int seatNo) {
		this.berth = berth;
		this.seatNo = seatNo;
	}
	public String getBerth() {
		return berth;
	}
	public int getSeatNo() {
		return seatNo;
	}
	@Override
	public String toString() {
		return  berth + "\t\t" + seatNo;
	}
	
	
	
	

}
