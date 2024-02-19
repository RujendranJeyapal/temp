package zcoin;

public class User 
{
      private String name;
      private String mailId;
      private long mobileNo;
      private String h_id;
      private String password;
      private double irc;
      private boolean isAdmin;
      private String approvalStatus;
      
	public User(String name, String mailId, long mobileNo, String h_id, String password, double irc, boolean isAdmin,
			String approvalStatus)
	{
		this.name = name;
		this.mailId = mailId;
		this.mobileNo = mobileNo;
		this.h_id = h_id;
		this.password = password;
		this.irc = irc;
		this.isAdmin = isAdmin;
		this.approvalStatus = approvalStatus;
	}
	
	
	

	public long getMobileNo() {
		return mobileNo;
	}




	public String getName() {
		return name;
	}




	public double getIrc() {
		return irc;
	}




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}




	public String getApprovalStatus() {
		return approvalStatus;
	}




	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}




	public String getMailId() {
		return mailId;
	}




	public boolean isAdmin() {
		return isAdmin;
	}




	@Override
	public String toString() {
		return  name + "\t\t" + mailId + "\t\t" + mobileNo + "\t\t" + h_id + "\t\t"
				+ password + "\t\t" + irc + "\t\t" + isAdmin + "\t\t" + approvalStatus + "\n";
	}




	
	
	
      
      
      
	

	
      
	
	
      
      
      
      
}
