package zcoin;

public class ZCTransaction extends Transaction{

	public ZCTransaction(String z_id, String description,  double amount, double closingRate) {
		super(z_id, description,  amount, closingRate);
		// TODO Auto-generated constructor stub
	}
/*	 private String z_id;
     private String description;
     private String credit_debit;
     private double amount;
     private double closingRate;
     
	public ZCTransaction(String z_id, String description, String credit_debit, double amount, double closingRate) {
		this.z_id = z_id;
		this.description = description;
		this.credit_debit = credit_debit;
		this.amount = amount;
		this.closingRate = closingRate;
	}

	@Override
	public String toString() 
	{
		return  z_id + "\t\t" + description + "\t\t" + credit_debit
				+ "\t\t" + amount + "\t\t" + closingRate + "\n";
	}*/
	
}
