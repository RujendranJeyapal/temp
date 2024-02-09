package atm;

public class Transaction 
{
	  private long accNo;
      private int transactionNumber;
      private String description;
      private String type;
      private double amount;
      private double closingBalance;
      
	public Transaction(long accNo,int transactionNumber, String description, String type, double amount, double closingBalance) 
	{
		this.accNo=accNo;
		this.transactionNumber = transactionNumber;
		this.description = description;
		this.type = type;
		this.amount = amount;
		this.closingBalance = closingBalance;
	}

	
	
	
	public long getAccNo() {
		return accNo;
	}




	public int getTransactionNumber() {
		return transactionNumber;
	}




	@Override
	public String toString()
	{
		return  transactionNumber + "\t\t" + description + "\t\t" + type
				+ "\t\t" + amount + "\t\t" + closingBalance + "\n";
	}
      
	
      
      
	
	
	
}
