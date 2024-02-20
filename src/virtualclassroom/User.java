package virtualclassroom;

public class User
{
	private String name;
       private String mailId;
       private String password;
       private boolean isAdmin=false;;
       private String approvalStatus="Pending";
       private long mobileNo;
       private boolean isStudent=false;
       private boolean isFaculty=false;
       
       
	public User(String name,long mobileNo,String mailId, String password		) 
	{
		this.name=name;
		this.mailId = mailId;
		this.password = password;
		this.mobileNo=mobileNo;
	}

	
	
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}



	public void setStudent(boolean isStudent) {
		this.isStudent = isStudent;
	}



	public void setFaculty(boolean isFaculty) {
		this.isFaculty = isFaculty;
	}



	public boolean isStudent() {
		return isStudent;
	}



	public boolean isFaculty() {
		return isFaculty;
	}



	public String getMailId() {
		return mailId;
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

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	public String getSubject() {
		return null;
	}

	public void setSubject(String subject) {
	}

	@Override
	public String toString() 
	{
		return  name + "\t\t" + mailId + "\t\t" + password + "\t\t" + isAdmin
				+ "\t\t" + approvalStatus + "\t\t" + mobileNo+"\n" ;
	}
       
       
	
       
       
       
}
