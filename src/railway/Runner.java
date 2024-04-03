package railway;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Runner 
{

	public static void main(String[] args) 
	{
		Scanner scan=new Scanner( System.in );
		RailwayLogic callLogic=new RailwayLogic();
		
		boolean condition=true;
		
		while( condition )
		{
		   int option=Utility.getInt(scan, "Press 1->Book\nPress 2->Cancel\nPress 3->Print Booked Tickets\nPress 4->Print available Tickets\nPress others->exit");
 
		
		   switch( option )
		   {
		
		        case 1:
		        	
		        	
		          int count=Utility.getInt(scan, "Howmany tickets you want");
		        	
		          List<Passenger> passengers=new ArrayList<>();
		          
		          for( int i=0;i<count;i++ )
		          {
		        	String name=Utility.getString(scan, "Enter your name");
		        	int age=Utility.getInt(scan,"Enter your age");
		        	String gender=getGender(scan);
		        	String berth=getBerth(scan);
		        	String passId=Utility.getString(scan, "Enter your aadhar number");
		        	
		        	passengers.add( new Passenger(passId,name,gender,age,berth) );
		        	
		        	
		          }
		
		          System.out.println(	callLogic.book(passengers) );
		          
		          break;
		          
		        case 2:
		        	
		        	String ticketId=Utility.getString(scan, "Enter your Ticket id");
		        	
		        	int choose=getCancelType(scan);
		        	
		        	if( choose==1 )
		        	{
		        		try {
							callLogic.cancelAllTicket(ticketId);
						} catch (CustomException e) {
							System.out.println(e.getMessage());
						}
		        	}
		        	if( choose==2 )
		        	{
		        		 String passengerId=Utility.getString(scan, "Enter passenger id");
		        		 
		        		 try 
		        		 {
		        		     callLogic.cancelTicket(ticketId, passengerId);
		        		 } 
		        		 catch (CustomException e) 
		        		 {
								System.out.println(e.getMessage());
						 }
		        		 
		        	}
		        	
		        	System.out.println("Cancel Successfully");
		
		        	break;
		        	
		        case 3:
		        	
		        	Map<String,Ticket> bookedTickets=callLogic.getBookedTickets();
		        	
		        	if( bookedTickets.size()==0 )
		        	{
		        		System.out.println("No bookings");
		        	}
		        	else
		        	{
		        		for( String tickId:bookedTickets.keySet() )
		        		{
		        			System.out.println(bookedTickets.get(tickId));
		        		}
		        	}
		        	
		        	break;
		        	
		        case 4:
		        	
		        	Map<String,List<Seat>> allSeats=callLogic.getAllSeats();
		        	
		        	if( allSeats.size()==0 )
		        	{
		        		System.out.println("No availables");
		        	}
		        	else
		        	{
		        		for( String berth:allSeats.keySet() )
		        		{
		        			System.out.println(berth+"--->"+allSeats.get(berth));
		        		}
		        	}
		        	
		        	break;
		        	
		        default:
		        	condition=false;
		
		   }
		}
		
	}
	
	static String getGender(Scanner scan)
	{
		boolean condition=true;
		
		while( condition )
		{
		   int option=Utility.getInt(scan, "Press 1->Male\nPress 2->Female");
		   
		 
		   switch( option )
		   {
		   
		       case 1:
		    	   return "MALE";
 	   
		       case 2:
		    	   return "FEMALE";
		    	 	    
		    	 default:
		    		 System.out.println("Press 1 or 2 only");
		   
		   }
		   
		   
		   
		} 
		
		return null;
		
	}
	
	
	static String getBerth(Scanner scan)
	{
		boolean flag=true;
		
		while( flag )
		{
		   int option=Utility.getInt(scan, "Press 1->Lower\nPress 2->Middle\nPress 3->Upper\nPress 4->Side-Upper");
		   
		 
		   switch( option )
		   {
		   
		       case 1:
		    	   return "Lower";
		    	   
		       case 2:
		    	   return "Middle";
		    	 
		       case 3:
		    	   return "Upper";
		    	   
		       case 4:
		    	   return "Side-Upper";
		      	   
		    	 default:
		    		 System.out.println("Press 1 or 2 or 3 or 4 only");
		   
		   }
		   
		   
		   
		} 
		
		return null;
		
	}

	
	static int getCancelType(Scanner scan)
	{
		boolean condition=true;
	
		
		while( condition )
		{
		   int option=Utility.getInt(scan, "Press 1->All\nPress 2->Individual");
		   
		 
		   switch( option )
		   {
		   
		       case 1:
		    	   return 1;
		    	   
		       case 2:
		    	   return 2;
		    	   
		    	 default:
		    		 System.out.println("Press 1 or 2 or 3 or 4 only");
		   
		   }
		   
		   
		   
		} 
		
		return 0;
		
	}

	
}
