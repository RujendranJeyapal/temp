package atm;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileStorage 
{

	private File atmFile=new File( "ATM.txt" );
	private File customersFile=new File( "Customer.txt" );
	private File transNumberFile=new File( "lastTransNo.txt" );
	
	public void writeCustomerFile(Map<Long,Customer> customers)
	{
		
		
		try(
				  FileWriter writer=new FileWriter( customersFile );
				  BufferedWriter buffer=new BufferedWriter( writer );)
				  {
			          buffer.write(  "Acc No\t\tAccount Holder\tPin Number\tAccount Balance\n"  );
					  
					  for(  Long accNo:customers.keySet() )
					  {
						  
						  buffer.write( customers.get(accNo).toString()  );
						  
					  }
					  
				  }
				  catch (IOException e) 
				  {
					e.printStackTrace();
				}
		
	}
	
	public void addTransaction( Transaction transaction )
	{
	  File transFile=new File( transaction.getAccNo()+".txt" );
	  
	  createTransFile(transFile);
	  
	  try
	  (
	      BufferedWriter writer=new BufferedWriter( new FileWriter( transFile,true ) );
      BufferedWriter transNoWriter=new BufferedWriter( new FileWriter( transNumberFile) );
     )
	  {
		  writer.append(transaction.toString());
		  transNoWriter.write( ""+transaction.getTransactionNumber() );
	  } 
	  catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	  }
	  
	  
	  
	  
	}
	
	private void createTransFile(File transFile)
	{
		  
		  
		  if( ! transFile.exists() )
		  {
			  try 
			  {
				transFile.createNewFile();
				
				FileWriter writer=new FileWriter(transFile);
				BufferedWriter buffer=new BufferedWriter( writer );
				buffer.write(  "Transaction Number\tDescription\tCredit / Debit\tAmount\tClosing Balance\n" );
				buffer.close();
				writer.close();
			  } 
			  catch (IOException e) 
			  {
				// TODO Auto-generated catch block
				e.printStackTrace();
			 }
		  }
	}
	
	public int readLastTransNumber()
	{
		int number=1001;
		
		if( transNumberFile.exists() )
		{
			try(   BufferedReader transNoReader=new BufferedReader( new FileReader( transNumberFile)); )
			{
			       number=Integer.parseInt(	transNoReader.readLine() );
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return number;
	}
	
	public List<Transaction> readTransaction(long accNo)
	{
		File transFile=new File( accNo+".txt" );
		 List<Transaction> trans=new ArrayList<>();
		if( transFile.exists() )
		{
		       try( BufferedReader reader=new BufferedReader( new FileReader( transFile )) )
		       {
			
			
			       String temp=  reader.readLine();
			
			       temp= reader.readLine();
			
			        while( temp!=null )
			        {
				
				          String array[]=temp.split("\t\t");
		
				          trans.add(   new Transaction( accNo,Integer.parseInt(array[0] ),array[1],array[2],Double.parseDouble( array[3] ),Double.parseDouble(array[4]))   ) ; 
				          temp= reader.readLine();
				
			        }
			
		       }
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		  }
		
		}
		return trans;
		
	}
	
	public Map<Long,Customer> readCustomerFile()
	{
		Map<Long,Customer> customers=new HashMap<>();
		
		try(
				  FileReader writer=new FileReader( customersFile );
				  BufferedReader buffer=new BufferedReader( writer );)
				  {
					  String temp=buffer.readLine();
					  
					  temp=buffer.readLine();
					
			          while( temp!=null )
			          {
			        	 String array[]= temp.split( "\t\t" );
			        	 
			        	 Customer customer=new Customer( Long.parseLong( array[0] ) ,  array[1],Integer.parseInt(array[2]), Double.parseDouble( array[3] ) );
			        	  
			        	 customers.put( Long.parseLong( array[0] )   , customer);
			        	  
			        	 temp= buffer.readLine();
			          }
			          
					  
				  }
				  catch (IOException e) 
				  {
					e.printStackTrace();
				}
		return customers;
	}
	
	  public void writeATMFile()
	  {
		  try(
		  FileWriter writer=new FileWriter( atmFile );
		  BufferedWriter buffer=new BufferedWriter( writer );)
		  {
			  buffer.write(  "Denomination\tNumber\tValue\n"  );
			  
			  buffer.write( ATM.OPERATEATM.toString() );
			  
			  
		  }
		  catch (IOException e) 
		  {
			e.printStackTrace();
		}
		  
	  }
	  
	  public void readATMFile()
	  {
		  
		  
		  
		  
		        try(
		              FileReader reader=new FileReader( atmFile );
		              BufferedReader buffer=new BufferedReader( reader );)
		           {
			  
			  
		        	
		        	
			           buffer.readLine();
			           
			           for( int i=0;i<3;i++ )
			           {
			        	   String line=buffer.readLine();
			        	   
			        	   String array[]=line.split("\t\t");
			        	   
			        	   if(  i==0 )
			        	   {
			        		   ATM.OPERATEATM.setNoOf2000( Integer.parseInt( array [1]) );
			        	   }
			        	   if(  i==1 )
			        	   {
			        		   ATM.OPERATEATM.setNoOf500( Integer.parseInt( array [1] ) );
			        	   }
			        	   if(  i==2 )
			        	   {
			        		   ATM.OPERATEATM.setNoOf100( Integer.parseInt( array [1] ) );
			        	   }
			        	   
			        	   
			           }
			           
			         
			  
		           }
		           catch (IOException e) 
		           {
			             e.printStackTrace();
		           }
		  }
		  
		
		  
	  
	  
	  public boolean isATMFileExist()
	  {
		 return    atmFile.exists(); 
		  
	  }
	  
	  public boolean isCusFileExist()
	  {
		 return    customersFile.exists(); 
		  
	  }
	
	
      
}
