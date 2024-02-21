package parking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Storage 
{

	  private Map<String,List<Spot> > spots=new HashMap<>();
 	  private Map<Long,Ticket> tickets=new HashMap<>();
	  private Map<Long,CustomerInfo> customers=new HashMap<>();
	
	  
	  public void addCustomer( CustomerInfo cusInfo )
	  {
		  customers.put( cusInfo.getMobileNo(),cusInfo );
	  }
	  
	  
	  
	  public CustomerInfo getCusInfo( long mobileNo )
	  {
		  return customers.get(mobileNo);
	  }
	  
	  public Ticket bookTicket(long cusMobileNo, String vehicleType, String vehicleNo  )
	  {
		  
		  List<Spot> spotLists=spots.get(vehicleType);
		  
		  Ticket ticket=new Ticket(  cusMobileNo,spotLists.get(0),vehicleNo,System.currentTimeMillis() );
		  
		  
		  tickets.put(cusMobileNo, ticket);
		  
		  spotLists.remove(0);
		  
		  return ticket;
	  }
	  
	
	  
	  public double getAmount(  long mobileNo ) throws CustomException
	  {
		  Ticket ticket=tickets.get(mobileNo);
		  
		  Utility.nullCheck(ticket, "Ticket");
		  
		 Spot spot=  ticket.getSpot() ;
		  
		 List<Spot> spotsList=spots.get(spot.getVehicleType()  );
		 
		 if( spotsList==null )
		 {
			 spotsList=new ArrayList<>();
			 spots.put( spot.getVehicleType() , spotsList);
		 }
		 
		 spotsList.add(spot);
		 
		 long secondsInMilli= System.currentTimeMillis()- ticket.getParkingTime();
		 
		 long mins=   secondsInMilli/60000;
		 int hour=(int) Math.ceil( mins/60 );
		 
		 double amount=4;
		 
		 for( int i=1;i<=hour;i++ )
		 {
			 if( i==1 )
			 {
				 amount=4;
			 }
			 
			 if( i==2 || i==3 )
			 {
				 amount+=3.5;
			 }
			 
			 else
			 {
				 amount+=2.5;
			 }
		 }
		 
		 
		 CustomerInfo cusInfo=getCusInfo( mobileNo );
		 
		 if( cusInfo.getWallet()>=amount)
		 {
			 cusInfo.setWallet( cusInfo.getWallet()-amount   );
			 amount=0;
		 }
		 else
		 {
			 amount-=cusInfo.getWallet();
			 cusInfo.setWallet( 0   );
		
		 }
		 
		 tickets.remove(mobileNo);
		 return amount;
	  }
	  
	  public String getAllSpots()
	  {
		  
		  System.out.println("SpotNumner\tFloor No\tVehicle No");
		  
		  String temp="";
		  
		  for(  String vehicleType : spots.keySet() )
		  {
			  List<Spot> spot=spots.get(vehicleType);
			  
			  for(  int i=0;i<spot.size();i++ )
			  {
				  temp+=spot.get(i)+"\n";
			  }
			  
			  
		  }
		  
		  return temp;
	  }
	  
	  public Storage(  )
	  {
             
		 
		  
		  
		  
		  for( int i=0;i<10;i++ )
		  {
			  
			  for(  int j=1;j<=10;j++ )
			  {
				  if( j<=2 )
				  {
					  
					   List<Spot>  spot= spots.get( "HandiCapped" );
					
					   if(  spot==null )
					   {
						   spot=new ArrayList<>();
						   spots.put("HandiCapped", spot);
					   }
					  
					   spot.add(  new Spot( j,i,"HandiCapped" ) );
					  
				  }
				  
				  else if( j<=4 )
				  {
					  
					   List<Spot>  spot= spots.get( "Large" );
					
					   if(  spot==null )
					   {
						   spot=new ArrayList<>();
						   spots.put("Large", spot);
					   }
					  
					   spot.add(  new Spot( j,i,"Large" ) );
					  
				  }
				  
				  else if( j<=6 )
				  {
					  
					   List<Spot>  spot= spots.get( "MotorCycle" );
					
					   if(  spot==null )
					   {
						   spot=new ArrayList<>();
						   spots.put("MotorCycle", spot);
					   }
					  
					   spot.add(  new Spot( j,i,"MotorCycle" ) );
					  
				  }
				  
				  else if( j<=8 )
				  {
					  
					   List<Spot>  spot= spots.get( "E-Vehicle" );
					
					   if(  spot==null )
					   {
						   spot=new ArrayList<>();
						   spots.put("E-Vehicle", spot);
					   }
					  
					   spot.add(  new Spot( j,i,"E-Vehicle" ) );
					  
				  }
				  
				  else 
				  {
					  
					   List<Spot>  spot= spots.get( "Compact" );
					
					   if(  spot==null )
					   {
						   spot=new ArrayList<>();
						   spots.put("Compact", spot);
					   }
					  
					   spot.add(  new Spot( j,i,"Compact" ) );
					  
				  }
			  }
		  }
		  
		  
	  }
	  
}
