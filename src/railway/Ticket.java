package railway;
import java.util.*;

public class Ticket 
{
     private String ticketrId;
     private int totalCount=0;
     private Map<String,Seat> bookings=new HashMap<>();
	public Ticket(String ticketrId, int totalCount, Map<String, Seat> bookings) {
		super();
		this.ticketrId = ticketrId;
		this.totalCount += totalCount;
		this.bookings = bookings;
	}
	public String getTicketrId() {
		return ticketrId;
	}
	public void setTicketrId(String ticketrId) {
		this.ticketrId = ticketrId;
	}
	public int getTotalCounnt() {
		return totalCount;
	}
	public void setTotalCounnt(int totalCounnt) {
		this.totalCount += totalCounnt;
	}
	public Map<String, Seat> getBookings() {
		return bookings;
	}
	public void setBookings(Map<String, Seat> bookings) {
		this.bookings = bookings;
	}
	@Override
	public String toString() 
	{
		String output="TicketrId=" + ticketrId + "\ntotalCount" + totalCount+"\n\n";
		
		for(  String passId:bookings.keySet() )
		{
			output+=passId+"[passenger id]-->"+bookings.get(passId)+"\n";
		}
		
		return output;
		
		
		
	}
     
     
     
     
}
