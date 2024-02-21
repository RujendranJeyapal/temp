package parking;

public class Spot 
{
         private int spotNo;
         private int floorNo;
         private String vehicleType;
         
		public Spot(int spotNo, int floorNo, String vehicleType) {
			this.spotNo = spotNo;
			this.floorNo = floorNo;
			this.vehicleType = vehicleType;
		}
		
		public Spot( Spot spot )
		{
			this.spotNo =spot.getSpotNo();
			this.floorNo = spot.getFloorNo();
			this.vehicleType =spot.getVehicleType();
		}

		public int getSpotNo() {
			return spotNo;
		}

		public int getFloorNo() {
			return floorNo;
		}

		public String getVehicleType() {
			return vehicleType;
		}

		@Override
		public String toString() 
		{
			return  spotNo + "\t\t" + floorNo + "\t\t" + vehicleType ;
		}
		
		
         
         
         
         
}
