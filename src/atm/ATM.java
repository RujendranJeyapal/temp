package atm;

enum ATM 
{
	
	OPERATEATM;
	
	private int noOf2000;
    private int noOf500;
    private int noOf100;
    

	public int getNoOf2000() {
		return noOf2000;
	}

	public void setNoOf2000(int noOf2000) {
		this.noOf2000 += noOf2000;
	}

	public int getNoOf500() {
		return noOf500;
	}

	public void setNoOf500(int noOf500) {
		this.noOf500 += noOf500;
	}


	
	
	
	public int getTotalamount() {
		return (noOf2000*2000)+(noOf500*500)+(noOf100*100);
	}

	public int getNoOf100() {
		return noOf100;
	}

	public void setNoOf100(int noOf100) {
		this.noOf100 += noOf100;
	}


	@Override
	public String toString() {
		return "2000\t\t"+noOf2000+"\t\t"+(noOf2000*2000)+"\n"+
				"500\t\t"+noOf500+"\t\t"+(noOf500*500)+"\n"+
				"100\t\t"+noOf100+"\t\t"+(noOf100*100)+"\n\n\n"
						+ "Total Amount available in the ATM ="+getTotalamount();
	                
	}

	
      
      
      
      
}
