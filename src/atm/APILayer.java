package atm;

import java.util.List;
import java.util.Map;

public class APILayer 
{

	private FileStorage callFile=new FileStorage();
	private CacheMemory callCache=new CacheMemory();
	
	public void feedATM( int noOf2000,int noOf500,int noOf100 )
	{
		callCache.feedATM(noOf2000, noOf500, noOf100);
		callFile.writeATMFile();
	}
	
	 public List<Transaction> getTransaction( long accNo  )
     {
  	   return callCache.getTransaction(accNo);
     }
	
	public void addCustomer( Customer customer )
	{
		callCache.addCustomer(customer);
		callFile.writeCustomerFile(  callCache.getCustomersMap()  );
	}
	
	public Map<Long,Customer> getCustomers()
	{
		return callCache.getCustomersMap();
	}
	
	public boolean validateAccountAndPin( long accountNumber,int pinNo ) throws CustomException
	{
	       Customer customer=callCache.getCustomer(accountNumber);
	       
	       if( customer ==null || customer.getPinNumber()!=pinNo )
	       {
	    	   throw new CustomException( "Customer doesnot exist" );
	       }
	       
	       return true;
	}
	
	public double getBalance( long accNo )
	{
		
		return callCache.getCustomer(accNo).getAccountBalance();
		
	}
	
	private void denominationCheck( int noOf2000,int noOf500,int noOf100 ) throws CustomException
	{
		
		if( ATM.OPERATEATM.getNoOf2000()<noOf2000  || ATM.OPERATEATM.getNoOf500()<noOf500 || ATM.OPERATEATM.getNoOf100()<noOf100 )
		{
			throw new CustomException( "ATM doesnot has sufficient money"  );
		}
		
		
	}
	
	public boolean moneyTransfer( long fromAccNo,long toAccNo,double amount )throws CustomException
	{
		
		if( fromAccNo==toAccNo )
		{
			throw new CustomException("Selt transfer was not possible");
		}
		
		  
		
		
		Customer fromCustomer=callCache.getCustomer(fromAccNo);
		Customer toCustomer=callCache.getCustomer(toAccNo);

		if( toCustomer==null )
		{
			throw new CustomException( "Enter Correct Account Number" );
		}
		
		if( amount>fromCustomer.getAccountBalance()  )
		{
			throw new CustomException( "Amount too large....!" );
		}
		
		if( amount<1000 || amount>10000 )
		{
			throw new CustomException( "Enter 1000 to 10000 only....!" );
		}
		
		fromCustomer.setAccountBalance(   fromCustomer.getAccountBalance()-amount  );
		toCustomer.setAccountBalance(   toCustomer.getAccountBalance()+amount  );
	
		
		Transaction transaction1=new Transaction( toAccNo,callCache.getTransNumber()," Transfer from"+fromAccNo,"Credit", amount ,toCustomer.getAccountBalance() );
		
		Transaction transaction2=new Transaction( fromAccNo,callCache.getTransNumber()," Transfer to"+toAccNo,"Debit", amount ,fromCustomer.getAccountBalance() );

		
		callCache.addTransaction(transaction1);
		callFile.addTransaction(transaction1);
		
		callCache.addTransaction(transaction2);
		callFile.addTransaction(transaction2);
		
		
		return true;
	}
	
	public boolean withDrawAmount( long accNo,double amount ) throws CustomException
	{
		
		if( amount%100!=0 )
		{
			throw new CustomException("Does not multiplies of 100"  );
		}
		
		Customer customer=callCache.getCustomer(accNo);
		
		if( amount>ATM.OPERATEATM.getTotalamount()  )
		{
			throw new CustomException("ATM has low amount"  );
		}
		
		if( amount<100 || amount>10000 )
		{
			throw new CustomException( "Enter 100 to 10000 only....!" );
		}
		
		if( customer.getAccountBalance()<amount  )
		{
			throw new CustomException( "Amount too large....!" );
		}
		
		
		double temp=amount;
		
		int noOf2000=0;
		int noOf500=0;
		int noOf100=0;
		
		if(  temp<=5000 )
		{
			while( temp>=3000 )
			{
				temp-=2000;
				noOf2000++;
			}
			
			while( temp>=1000 )
			{
				temp-=500;
				noOf500++;
			}
			
			while( temp>0 )
			{
				temp-=100;
				noOf100++;
			}
			
			
			
		}
		else
		{
			
			   for( int i=0;i<2;i++ )
			   {
				   temp-=2000;
				   noOf2000++;
			   }
			   for( int i=0;i<2;i++ )
			   {
				   temp-=500;
				   noOf500++;
			   }
			   for( int i=0;i<10;i++ )
			   {
				  
				   
				   temp-=100;
				   noOf100++;
				   
				   if( temp==0 )
				   {
					   break;
				   }
			   }
			   while( temp>0 )
			   {
				   temp-=500;
				   noOf500++;
			   }
			
			
		}
		
		denominationCheck( noOf2000,noOf500,noOf100 );
		
		feedATM( -noOf2000,-noOf500,-noOf100 );
		
		customer.setAccountBalance( customer.getAccountBalance()-amount  );
		
		Transaction transaction=new Transaction( accNo,callCache.getTransNumber(),"Cash Withdrawal","Debit",amount,customer.getAccountBalance() );
		
	//	start();
		
		callCache.addTransaction(transaction);
		callFile.addTransaction(transaction);
		
		return true;
	}
	

	
	public APILayer()
	{
		 if( callFile.isATMFileExist() )
		{
			callFile.readATMFile();
		}
		
		
		 if(! callFile.isCusFileExist() )
		 {
			addCustomer( new Customer( 101 ,"Suresh" ,2343 ,25234   ) );
			addCustomer( new Customer( 102 ,"Ganesh" ,5432 ,34123   ) );
			addCustomer( new Customer( 103 ,"Magesh" ,7854 ,26100   ) );
			addCustomer( new Customer( 104 ,"Naresh" ,2345 ,80000   ) );
			addCustomer( new Customer( 105 ,"Harish" ,1907 ,103400   ) );
		 }
		 else
		 {
			 
			 Map<Long,Customer> customerMap=callFile.readCustomerFile();
			  callCache.setCustomersMap( customerMap  );
			  
			  for(  Long accNo:customerMap.keySet() )
			  {
				  
				  callCache.setTransactionMap(accNo,  callFile.readTransaction(accNo)   );
				  
			  }
			  
			  callCache.setTransNumber(  callFile.readLastTransNumber() );
		 }
		 
	}
	
}
