package zcoin;

public class Transaction {
	 private String z_id;
     private String description;
     private double amount;
     private double closingRate;
     
	public Transaction(String z_id, String description,  double amount, double closingRate) {
		this.z_id = z_id;
		this.description = description;
		this.amount = amount;
		this.closingRate = closingRate;
	}
	
	

	public String getZ_id() {
		return z_id;
	}



	@Override
	public String toString() 
	{
		return  z_id + "\t\t" + description
				+ "\t\t" + amount + "\t\t" + closingRate + "\n";
	}
}
