package parking;
import java.util.Date;


public class Ticket 
{

	private long cusMobileNo;
	private Spot spot;
	private String vehicleNo;
	private long parkingTime;
	
	public Ticket(long cusMobileNo, Spot spot, String vehicleNo, long parkingTime) 
	{	
		this.cusMobileNo = cusMobileNo;
		this.spot = spot;
		this.vehicleNo = vehicleNo;
		this.parkingTime = parkingTime;
	}




	public long getCusMobileNo() {
		return cusMobileNo;
	}




	public Spot getSpot() {
		return spot;
	}




	public String getVehicleNo() {
		return vehicleNo;
	}




	public long getParkingTime() {
		return parkingTime;
	}




	public String toString() {
		return "Customer Mobile Number :" + cusMobileNo + "\n"
				+ "Floor No :" + spot.getFloorNo() +"\n"
				+"Vehicle Type :"+spot.getVehicleType()+"\n"
				+"Spot Number :"+spot.getSpotNo()+"\n"
				+ "Vehicle Number :" + vehicleNo+"\n"
				+ "Parking Time :"+new Date(parkingTime) ;
	}
	
	
	
	
}
