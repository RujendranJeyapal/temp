package atm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CacheMemory 
{
       private Map<Long,Customer> customersMap=new HashMap<>();
       private Map<Long,List<Transaction>> transactionMap=new HashMap<>();
       private int transNumber=1001;
       
       public void addCustomer( Customer customer )
       {
    	   customersMap.put(customer.getAccountNumber(), customer);
       }
       
     
       
       public void addTransaction(Transaction transaction)
       {
    	   long accNo=transaction.getAccNo();
    	   
    	   List<Transaction> transactionsList=transactionMap.get(accNo);
    	   
    	   if( transactionsList==null )
    	   {
    		   transactionsList=new ArrayList<>();
    		   transactionMap.put(accNo, transactionsList);
    	   }
    	   transactionsList.add(transaction);
       }
       
       public Customer getCustomer( long accNo )
       {
    	  return customersMap.get(accNo);
       }
       
       public int getTransNumber()
       {
    	   return transNumber++;
       }
       
       
       
       
       public void setTransNumber(int transNumber) {
		this.transNumber = transNumber;
	}

	public Map<Long, Customer> getCustomersMap()
       {
		return customersMap;
	  }

       public List<Transaction> getTransaction( long accNo  )
       {
    	   return transactionMap.get(accNo);
       }


	public void setTransactionMap(long accNo,List<Transaction> transactionList) {
		transactionMap.put(accNo, transactionList);
	}

	public void setCustomersMap(Map<Long, Customer> customersMap) {
		this.customersMap = customersMap;
	}



	public void feedATM( int noOf2000,int noOf500,int noOf100 )
   	{
   		ATM.OPERATEATM.setNoOf2000(noOf2000);
   		ATM.OPERATEATM.setNoOf500(noOf500);
   		ATM.OPERATEATM.setNoOf100(noOf100);
   		
   		
   	}
}
