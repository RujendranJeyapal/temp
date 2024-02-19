package zcoin;

public class Account 
{
        private String mailId;
        private String z_id;
        private double rcWallet;
        private double zcoinWallet;
        
		public Account(String mailId,  double rcWallet, double zcoinWallet) {
			this.mailId = mailId;
			this.rcWallet = rcWallet;
			this.zcoinWallet = zcoinWallet;
		}

		
		
		public void setZ_id(String z_id) {
			this.z_id = z_id;
		}



		public String getZ_id() {
			return z_id;
		}

		public double getRcWallet() {
			return rcWallet;
		}

		public void setRcWallet(double rcWallet) {
			this.rcWallet = rcWallet;
		}

		public double getZcoinWallet() {
			return zcoinWallet;
		}

		public void setZcoinWallet(double zcoinWallet) {
			this.zcoinWallet = zcoinWallet;
		}

		public String getMailId() {
			return mailId;
		}



		@Override
		public String toString() 
		{
			return  mailId + "\t\t" + z_id + "\t\t" + rcWallet + "\t\t"
					+ zcoinWallet + "\n";
		}
        
        
        
        
}
