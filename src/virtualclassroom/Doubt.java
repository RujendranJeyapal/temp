package virtualclassroom;

public class Doubt
{
	private String questionId;
     private String facultyMailId;
     private String studentMailId;
     private String question;
     private String answer;
     
	public Doubt(String facultyMailId, String studentMailId, String question) {
		this.facultyMailId = facultyMailId;
		this.studentMailId = studentMailId;
		this.question = question;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	
	
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getFacultyMailId() {
		return facultyMailId;
	}

	public String getQuestionId() {
		return questionId;
	}

	public String getQuestion() {
		return question;
	}

	public String getStudentMailId() {
		return studentMailId;
	}

	@Override
	public String toString() 
	{
		return  questionId + "\t\t" + facultyMailId + "\t\t"
				+ studentMailId + "\t\t" + question + "\t\t" + answer + "\n";
	}
     
     
     
     
}
