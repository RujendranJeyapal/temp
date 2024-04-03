package railway;

public class Passenger 
{
           private String passId; 	
           private String name;
           private String gender;
           private int age;
           private String berthPreference;
		public Passenger(String passId,String name, String gender, int age, String berthPreference) {
			this.name = name;
			this.gender = gender;
			this.age = age;
			this.berthPreference = berthPreference;
			this.passId=passId;
		}
		public String getGender() {
			return gender;
		}
		public int getAge() {
			return age;
		}
		public String getBerthPreference() {
			return berthPreference;
		}
		@Override
		public String toString() {
			return  passId+"\t"+name + "\t" + gender + "\t" + age + "\t"
					+ berthPreference;
		}
		public String getPassId() {
			return passId;
		}
           
		
		
           
           
}
