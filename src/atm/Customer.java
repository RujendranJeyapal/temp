package atm;

public class Customer 
{

	   private long accountNumber;
	   private String name;
	   private int pinNumber;
	   private double accountBalance;
	   
	public Customer(long accountNumber, String name, int pinNumber, double accountBalance)
	{
		this.accountNumber = accountNumber;
		this.name = name;
		this.pinNumber = pinNumber;
		this.accountBalance = accountBalance;
	}
	

	public long getAccountNumber() {
		return accountNumber;
	}

	

	public int getPinNumber() {
		return pinNumber;
	}

	
	

	public double getAccountBalance() {
		return accountBalance;
	}


	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}


	@Override
	public String toString() 
	{
		return  accountNumber + "\t\t" + name + "\t\t" + pinNumber
				+ "\t\t" + accountBalance+"\n";
	}
	   
	   
	   
	   
	   
	
	
}
