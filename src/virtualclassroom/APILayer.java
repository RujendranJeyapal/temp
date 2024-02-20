package virtualclassroom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class APILayer 
{

	//private Map<String,Student> allStudents=new HashMap<>();
	//private Map<String,Faculty> allFaculties=new HashMap<>();
	private Map<String,User> allUser=new HashMap<>();
	private Map<String,Map<String,Doubt>> doubts=new HashMap<>();
	private Map<String,Map<String,Doubt>> answers=new HashMap<>();
	private Map<String,Map<Integer,PdfOrVideo>> pdfAndVideos=new HashMap<>();
	
	private int doubtId=1;
	private int pdfOrVideoId=1;
	
	

    public Map<String, Map<Integer, PdfOrVideo>> getPdfAndVideos() throws CustomException {
    	
    	if( pdfAndVideos==null || pdfAndVideos.isEmpty() )
    	{
    		throw new CustomException( "No ppts" );
    	}
    	
		return pdfAndVideos;
	}

	public Map<String,Doubt> getDoubts( String facultyMailId ) throws CustomException
    {
    	Map<String,Doubt> doubt=doubts.get(facultyMailId);
    	
    	if( doubt==null || doubt.isEmpty() )
    	{
    		throw new CustomException( "No Doubts" );
    	}
    	
    	return doubt;
	}
	
    public Map<String,Doubt> getAnswers( String studentMailId ) throws CustomException
    {
    	Map<String,Doubt> answer=answers.get(studentMailId);;
    	
    	if( answer==null || answer.isEmpty() )
    	{
    		throw new CustomException( "No Answers" );
    	}
    	
    	return answer;
	}
    
	public Map<String, User> getAllUser() {
		return allUser;
	}

	public Map<String, Map<String, Doubt>> getDoubts() throws CustomException {
		
		if( doubts==null || doubts.isEmpty() )
		{
			throw new CustomException( "No Doubts" );
		}
		
		return doubts;
	}

	public Map<String, Map<String, Doubt>> getAnswers() {
		return answers;
	}

	public User getUser( String mailId ) throws CustomException
	{
		User user= allUser.get(mailId);
		
		Utility.ObjectCheck(user, "User");
		
		return user;
	}
	
	private int pdfOrVideoId()
	{
		return pdfOrVideoId++;
	}
	
	public String generateDoubtId()
	{
		return "Doubt "+doubtId++;
	}
	
	public void passwordCheck( String mailId,String password )throws CustomException
	{
		if( !allUser.get(mailId).getPassword().equals(password) )
		{
			throw new CustomException( "Wrong Password" );
		}
	}
	
	public void addPdfOrVideos(String subject,PdfOrVideo pdfOrVideoLink)
	{
		pdfOrVideoLink.setId( pdfOrVideoId() );
		
	Map<Integer,PdfOrVideo>	pdfOrVideoMap=pdfAndVideos.get(subject);
	
	if( pdfOrVideoMap==null )
	{
		pdfOrVideoMap=new HashMap<>();
		pdfAndVideos.put(subject, pdfOrVideoMap);
	}
	pdfOrVideoMap.put( pdfOrVideoLink.getId() , pdfOrVideoLink);
		
	}
	
	public void removePdfOrVideos(String subject,int id) throws CustomException
	{
		Map<Integer,PdfOrVideo>	pdfOrVideoMap=pdfAndVideos.get(subject);
		
		Utility.ObjectCheck(pdfOrVideoMap, "Subject");
		
		if( pdfOrVideoMap.isEmpty() )
		{
			throw new CustomException( "No Pdfs" );
		}
		
		Utility.ObjectCheck( pdfOrVideoMap.get(id) , "Pdf");
		
		pdfOrVideoMap.remove(id);
	}
	
	public void addAnswer(Doubt doubt)
	{
		String studentMailId=doubt.getStudentMailId();
		
		Map<String,Doubt> answersMap=answers.get(studentMailId);
		
		
		if( answersMap==null )
		{
			answersMap=new HashMap<>();
			answers.put(studentMailId, answersMap);
		}
		answersMap.put(doubt.getQuestionId(), doubt);
		
		doubts.get( doubt.getFacultyMailId() ).remove( doubt.getQuestionId() );
	}
	
	public void addDoubt(Doubt doubt)
	{
		String facultyMailId= doubt.getFacultyMailId();
		
		Map<String,Doubt> doubtMap=doubts.get(facultyMailId);
		
		if( doubtMap==null )
		{
			doubtMap=new HashMap<>();
			doubts.put(facultyMailId, doubtMap);
		}
		
		String id=generateDoubtId();
		
		doubt.setQuestionId( id  );
		
		doubtMap.put( id , doubt);
	}
	
	/*public void addStudent(Student student)
	{
		allStudents.put( student.getMailId() , student);
	}
	
	public void addFaculty(Faculty faculty)
	{
		allFaculties.put( faculty.getMailId() , faculty);
	}
	*/
	public void addUser(User user)
	{
		allUser.put(user.getMailId(), user);
	}
	
	public List<User> getPendingUsers()
	{
		List<User> pendingStudents=new ArrayList<>();
		
		
		for( String mailId:allUser.keySet() )
		{
			User user=allUser.get(mailId);
			
			if(  user.getApprovalStatus().equals( "Pending" ) )
			{
				pendingStudents.add( allUser.get(mailId) );
			}
		}
		return pendingStudents;
	}
	
	
	
	
	public APILayer()
	{
		User user=new User( "Admin",7550332449l,"admin@zoho.com","admin2000!");
		user.setAdmin(true);
		user.setApprovalStatus("Approved");
		
		addUser(user  );
	}
}
