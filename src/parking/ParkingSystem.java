package parking;

import java.util.Scanner;

public class ParkingSystem 
{

	public static void main(String[] args) 
	{
		Scanner scan=new Scanner( System.in );
		
		Storage callStorage=new Storage();
		
		boolean condition=true;
		
		
		while( condition )
		{
			

		
		int option=Utility.getInt( "Press 1->Entry\nPress 0->Exit"  , scan);
				

			
		    switch( option )
			{
			
			      case 1:
			    	  
			    	    System.out.println( callStorage.getAllSpots( ));

			    	    long mobile=Utility.getLong( "Enter your phone number" , scan);
			    	   
			    	    CustomerInfo cusInfo=callStorage.getCusInfo(mobile);
			    	    
			    	    if( cusInfo==null )
			    	    {
			    	    	cusInfo=new CustomerInfo();
			    	    	cusInfo.setMobileNo(mobile);
			    	    }
			    	    
			    	    
			    	    String vehicleType=getVehicleType( scan);
			   
			    	    
			    	    
				         String vehicleNo=Utility.getString("Enter vehicle Number"   , scan);
				         

				         int choose=Utility.getInt("Your wallet amount is : "+cusInfo.getWallet() +"\n"
	        		             +  "Press 1->add\nPress other->No "   , scan);
				         
				         if( choose==1 )
				         {
				        	 double amount=Utility.getDouble("Enter your amount", scan);
				        			 
				        	 cusInfo.setWallet(amount);
				        	 
				         }
				         callStorage.addCustomer(cusInfo);
				         
				        System.out.println(   "Take your Ticket:\n"+callStorage.bookTicket(mobile, vehicleType, vehicleNo) ) ;
			    	    
			    	    
				        break;
			
			        
			      case 0:
			    	  
			    	  
			    	    
				double amount=0;
				
				   while( true )
				   {
					   long mobileNo=Utility.getLong( "Enter your phone number" , scan);
				        try 
				        {
					         amount = callStorage.getAmount(mobileNo);
					         break;
				        }
				        catch (CustomException ex) 
				        {
					           System.out.println(ex.getMessage());
				        }
				   }
			    	    if( amount==0 )
			    	    {
			    	    	System.out.println("Don't pay");
			    	    }
			    	    else
			    	    {
			    	    	while( true )
			    	    	{
			    	    	    double pay=Utility.getDouble("Pay the amount :"+amount  , scan);
			    	    	
			    	     	    if( pay== amount)
			    	    	    {
			    	    		  System.out.println("Thank you");
			    	    		  break;
			    	    	    }
			    	    	    else
			    	    	    {
			    	    		  System.out.println("Enter correct amount");
			    	    	    }
			    	     	    
			    	    	}    
			    	    }
				
				        break;
			
			     default:
			    	 
			    	 System.out.println("Enter 0 and 1 only");
			    	  //  condition=false;
			
			}
			
		}
		
		
		
		scan.close();

	}
	
	static String getVehicleType( Scanner scan )
	{
		
	
		
		String vehicleType="";
		
		
		boolean condition=true;
		
		while( condition )
		{
			
			int option=	Utility.getInt(  "Choose Vehicle Type\n"
		    		+ "Press 1->Handicapped\n"
		    		+ "Press 2->Large\n"
		    		+ "Press 3->MotorCycle\n"
		    		+ "Press 4->E-Vehicle\n"
		    		+ "Press 5->Compact" , scan);
		
		switch( option )
		{
		    case 1:
		    	
		    	vehicleType="HandiCapped";
		    	condition=false;
		    	break;
		    	
           case 2:
		    	
		    	vehicleType="Large";
		    	condition=false;
		    	break; 	
		   
           case 3:
		    	
		    	vehicleType="MotorCycle";
		    	condition=false;
		    	break;
		    	
           case 4:
		    	
		    	vehicleType="E-Vehicle";
		    	condition=false;
		    	break;
		    	
           case 5:
		    	
		    	vehicleType="Compact";
		    	condition=false;
		    	break;
		    	
		  default:
			  System.out.println("Choose correct number");
			// scan.nextLine();
		    	
		}
			
		}	  
		
		
		return vehicleType;
	}

}
