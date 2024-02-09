package atm;

import java.util.*;

public class ATMAccess
{

	public static void main( String[] args)
	{
		Scanner scan=new Scanner( System.in );
		
		APILayer callAPI=new APILayer();
		
		boolean condition=true;
		
		
		
		while( condition )
		{
			
			
			int option=Utility.getInt( "Press 1->Load Cash to ATM\n"
					+ "Press 2->Show Customer Details\n"
					+ "Press 3->Show ATM Operations\n"
					+ "Press others->exit",scan);
			
			
			
			
			
			switch( option )
			{
			
			      case 1:
			    	  
			    	  int noOf2000=Utility.getInt("Enter no of 2000", scan);
			    	  int noOf500=Utility.getInt("Enter no of 500", scan);
			    	  int noOf100=Utility.getInt("Enter no of 100", scan);
			    	  
			    	  callAPI.feedATM(noOf2000, noOf500, noOf100);
			    	  
			    	  break;
			    	  
			      case 2:
			    	  
			    	  Map<Long,Customer> customers=callAPI.getCustomers();
			    	  
			    	  if( customers!=null && !customers.isEmpty() )
			    	  {
			    	  
			    		  System.out.println("Acc No\t\tAccount Holder\tPin Number\tAccount Balance\n");
			    		  
			    	      for( Long accNo:customers.keySet() )
			    	      {
			    		         System.out.println(  customers.get(accNo)  );
			    	      }
			    	         
			    	  }
			    	  else
			    	  {
			    		  System.out.println("No customers");
			    	  }
			    	  
			    	  break;
			    	  
			      case 3:
			    	  
			    	  long accNo=Utility.getLong( "Enter account Number" , scan);
			    	  int pinNo=Utility.getInt( "Enter PIN number" , scan);
			    	  
			    	        try
				            {
					             if(  callAPI.validateAccountAndPin(accNo, pinNo) )
			    	             {			    		                
					            	 atmOperations( accNo,pinNo,scan,callAPI );					            	 
			    	             }
				            }             
				            catch (CustomException ex) 
			    	        {
				            	   System.out.println(ex.getMessage());
 				            }
			    	  
			    	  break;
			    	  
			      default:
			    	  
			    		  condition=false;
			
			}
		}
		
		
		scan.close();
	}

	private static void atmOperations(long accNo, int pinNo,Scanner scan,APILayer callAPI) 
	{
		boolean condition=true;
		
		while( condition )
		{
		
		    int option=Utility.getInt("Press 1->Check Balance\n"
				+ "Press 2->Withdraw Money\n"
				+ "Press 3->Transfer Money\n"
				+ "Press 4->Check ATM Balance\n"
				+ "Press 5->Mini Statement\n"
				+ "Press others->exit", scan);
		
		    
		    switch( option )
		    {
		        
		           case 1:
		        	   
		        	   System.out.println("Balance :"+callAPI.getBalance( accNo ));
		        	   
		        	   break;
		         
		           case 2:
		        	   
				       try 
				       {
					            if( callAPI.withDrawAmount(accNo, Utility.getDouble("Enter amount", scan)  ) )
		        	            {
		        		                System.out.println("Withdraw Successfully");
		        	            }
				       }               
				       catch (CustomException ex) 
				       {
					            System.out.println(ex.getMessage());
				       }
		        	   
		              break;
		              
		              
		           case 3:
		        	   
		        	   try 
				       {
					            if( callAPI.moneyTransfer(  accNo,Utility.getLong( "Receiver accountNumber"  , scan) , Utility.getDouble("Enter amount", scan)   ) )
		        	            {
		        		                System.out.println("Amount Transfer Successfully");
		        	            }
				       }               
				       catch (CustomException ex) 
				       {
					            System.out.println(ex.getMessage());
				       }
		        	   
		        	   break;
		           case 4:
		        	   
		        	   
		        	   System.out.println(ATM.OPERATEATM);
		        	   
		        	   break;
		        	   
		        	   
		           case 5:
		        	   
		        	   
		        	   List<Transaction> transactions=callAPI.getTransaction(accNo);
		        	   
		        	   if( transactions.isEmpty() )
		        	   {
		        		   System.out.println("No TRansactions");
		        	   }
		        	   
		        	   if( transactions.size()<10 )
		        	   {
		        		   System.out.println ("Transaction Number\tDescription\tCredit / Debit\tAmount\tClosing Balance\n");
		        		   
		        		   for( int i=0;i<transactions.size();i++ )
		        		   {
		        			   System.out.println( transactions.get(i)  );
		        		   }
		        		   
		        	   }
		        	   else
		        	   {
		        		   System.out.println ("Transaction Number\tDescription\tCredit / Debit\tAmount\tClosing Balance\n");
		        		   
		        		   for( int i=transactions.size()-10;i<transactions.size();i++ )
		        		   {
		        			   System.out.println( transactions.get(i)  );
		        		   }
		        		   
		        	   }
		        	   
		        	   break;
		        
		           default:
		        	   condition=false;
		        	    break;
		    }
		    
		    
		} 
	}
	
	
}
