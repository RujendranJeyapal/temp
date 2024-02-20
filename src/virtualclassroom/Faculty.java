package virtualclassroom;

public class Faculty  extends User{

	
	
	public Faculty(String name, long mobileNo, String mailId, String password) {
		super(name, mobileNo, mailId, password);
		// TODO Auto-generated constructor stub
	}

	private String  subject;

	@Override
	public String getSubject() {
		return subject;
	}

	@Override
	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Override
	public String toString() {
		return subject + "\t\t" + super.toString() + "\n";
	}
	
	

}
