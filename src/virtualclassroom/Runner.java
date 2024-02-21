package virtualclassroom;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Runner 
{
   public static void main( String args[] )
   {
	   Scanner scan=new Scanner( System.in );
	   
	   APILayer callAPI=new APILayer();
	   
	   boolean condition=true;
	   
	   while( condition )
	   {
	      int option=Utility.getInt("Press 1->SignIn\nPress 2->Sign Up\nPress others->exit",scan  );
	    
	      switch( option )
	      {
	      
	             case 1:
	
	            	 String eMailId=Utility.getString("Enter mailID", scan);
	            	 
	            	 String userPassword=Utility.getString("Enter password", scan);
	            	 
	            	 User user=null;
	            			
	            	 
	            	
			             try 
			             {
			            	 user=callAPI.getUser(eMailId);
			            	 callAPI.passwordCheck(eMailId, userPassword);
			             }
			             catch (CustomException ex) 
			             {
				             System.out.println(ex.getMessage());
					            break;

			             }
	            	 
			          if( user.isAdmin() )
			          {
			        	  adminLogIn( scan,callAPI );
			          }
	            	 
			          else if( user.isStudent() )
			          {
			        	  if( user.getApprovalStatus().equals("Approved") )
			        	  {
			        		  studentLogIn( scan,callAPI,user );
			        	  }
			        	  else
			        	  {
			        		  System.out.println("Admin doesnot approve");
			        	  }
			          }
			          else
			          {
			        	  if( user.getApprovalStatus().equals("Approved") )
			        	  {
			        		  facultyLogIn( scan,callAPI,user );
			        	  }
			        	  else
			        	  {
			        		  System.out.println("Admin doesnot approve");
			        	  }
			          }
			             
			             
	            	 break;
	            	 
	            	 
	             case 2:
	            	 
	            	 String mailId=null;
	            	 
	            	 while( true )
	            	 {
	            		 mailId=Utility.getString("Enter mailID", scan);
	            		 
	            		 try
	            		 {
	            	        callAPI.getUser(mailId);
	            	        System.out.println("Already Exist User");
	            		 }
	            		 catch( CustomException e )
	            		 {
	            			 break;
	            		 }
	            	    	 
	            	     
	            	     
	            	 }
	            	 
	            	 String password=Utility.getString("Enter password", scan);
	            	 
	            	 String name=Utility.getString("Enter name", scan);
	            	 
	            	 long mobileNo=Utility.getLong("Enter mobile number", scan);
	            	 
	            	 boolean isStudent=isStudent( scan );
	            	 
	            	 if( isStudent )
	            	 {
	            		 Student student=new Student( name, mobileNo, mailId, password );
	            		 student.setStudent(true);
	            		 callAPI.addUser(student);
	            	 }
	            	 else
	            	 {
	            		 Faculty faculty=new Faculty( name, mobileNo, mailId, password );
	            		 faculty.setFaculty(true);
	            		 faculty.setSubject( Utility.getString("Enter your Subject", scan) );
	            		 callAPI.addUser(faculty);
	            	 }
	            	 
	            	 System.out.println("Sign up successfully");
	            	 
	            	 break;
	            	 
	             default:
	            	 condition=false;
	            		 
	            	 
	            	 
	      
	      }
	      
	   } 
   }

   private static void facultyLogIn(Scanner scan, APILayer callAPI,User user) 
   {
	
	   
	   boolean condition=true;
	   
	   while( condition )
	   {
		   int option=Utility.getInt("Press 1->Edit Profile\nPress 2->Add study Material\nPress 3->Remove Study Material\n"
		   		+ "Press 4->see Doubts\n"
		   		+ "Press 5->Tell Answer\n"
		   		+ "Press others->exit", scan);
		   
		   switch( option )
		   {
		        case 1:
		        	
		        	editProfile( user ,scan );
		        	
		        	break;
		        	
		        case 2:
		        	
		        	callAPI.addPdfOrVideos( user.getSubject() , new PdfOrVideo( Utility.getString("Enter pdf link", scan) ) );
		        	
		        	break;
		        	
		        case 3:
		        	
			        try 
			        {
				         callAPI.removePdfOrVideos( user.getSubject()  ,  Utility.getInt("Enter id", scan) );
			        }
			        catch (CustomException ex) 
			        {
                         System.out.println(ex.getMessage());				
			        }
		   
			        break;
			        
		        case 4:
		        	    try 
				        {
		        		 Map<String,Doubt> doubt=callAPI.getDoubts(user.getMailId());
		        		 
		        		 for( String questionId:doubt.keySet() )
		        		 {
		        			 System.out.println( doubt.get(questionId) );
		        		 }
		        		 
		                }
		        	    catch (CustomException ex) 
				        {
	                         System.out.println(ex.getMessage());				
				        }
		        	
		        	break;
		        	
		        case 5:
		        	
		        	
		        	  try 
				        {
		        		 Map<String,Doubt> doubtMap=callAPI.getDoubts(user.getMailId());
		        		 
		        		 Doubt doubt=null;
		        		 
		        		 while( true )
		        		 {
		        		     String quesID=Utility.getString("Enter questionID", scan);
		        		   
		        		      doubt=doubtMap.get(quesID);
		        		 
		        		     try
		        		     {
		        		        Utility.ObjectCheck(doubt, "Doubt");
		        		        break;
		        		     }
		        		     catch (CustomException ex) 
						     {
			                         System.out.println(ex.getMessage());				
						     }
		        		     
		        		     
		        		 }
		        		 
		        		 System.out.println(doubt.getQuestion());
		        		 
		        		 doubt.setAnswer( Utility.getString("Answer:", scan) );
		        		 callAPI.addAnswer(doubt);
		        		 
		        		 
		                }
		        	    catch (CustomException ex) 
				        {
	                         System.out.println(ex.getMessage());				
				        }
		        	
		        	break;
			     default:
			    	 
			    	 condition=false;
		   }
		   
	   }
	   
	   
	   
   }

    private static void editProfile(User user,Scanner scan)
    {
	
    	
    	boolean condition=true;
    	
    	while( condition )
    	{
    		int option=Utility.getInt("Press 1->Edit Name\nPress 2->Edit Password\nPress 3->Edit mobileNo\n"
    				+ "Press others->exit", scan);
    		
    		switch( option )
    		{
    		
    		   case 1:
    			   
    			    user.setName( Utility.getString("Enter name", scan) );
    			    
    			    break;
    			    
    		   case 2:
    			   
    			   user.setPassword( Utility.getString("Enter Password", scan) );
    			    
    			   break;
    			   
    		   case 3:
    			   
    			   user.setMobileNo( Utility.getLong("Enter mobile number", scan) );
    			   
    			   break;
    			   
    			   
    			 default:
    				 
    				 condition=false;
    			    
    		
    		}
    	}
	
   }

	private static void studentLogIn(Scanner scan, APILayer callAPI,User user) 
    {
	
		boolean condition=true;
		
		while( condition )
		{
			
			int option =Utility.getInt( "Press 1->Edit Profile\nPress 2->Show Material\nPress 3->Ask Question\nPress 4->See Answer\n"
					+ "Press others->exit" , scan);
		
			
			switch( option )
			{
			
			    case 1:
			    	
			    	 editProfile( user,scan);
				 
			    	 break;
			    	 
			    case 2:
			    	
			    	try
			    	{
			    		Map<String,Map<Integer,PdfOrVideo>> pdfAndVideos= callAPI.getPdfAndVideos();
			    		
			    		
			    		for( String subject:pdfAndVideos.keySet() )
			    		{
			    			Map<Integer,PdfOrVideo> ppt=pdfAndVideos.get(subject);
			    			
			    			
			    			if( ppt==null || ppt.isEmpty() )
			    			{
			    				System.out.println(subject +" has No ppts");
			    			}
			    			else
			    			{
			    				for( Integer id:ppt.keySet() )
			    				{
			    					System.out.println( ppt.get(id) );
			    				}
			    			}
			    			
			    		}
			    		
			    	}
			    	catch( CustomException ex )
			    	{
			    		System.out.println(ex.getMessage());
			    	}
			    	break;
			    	
			    case 3:
			    	
			    	String facultyId=null;
			    	
			    	 
			    	 while( true )
			    	 {	 
			    		 facultyId=Utility.getString( "Enter Faculty" , scan);
			    		 try
			    		 {
			    		    callAPI.getUser(facultyId);
			    		     break;
			    		 }
			    		 catch( CustomException ex )
			    		 {
			    			 System.out.println(ex.getMessage());
			    		 }
			    	
			    	 }
			    	 
			    	 callAPI.addDoubt( new Doubt(  facultyId,user.getMailId(), Utility.getString("Enter Question", scan) ) );
			    	 
			    	break;
			    	
			    case 4:
			    	
			    	try
			    	{
			    	     Map<String,Doubt> answer=callAPI.getAnswers( user.getMailId() );
			    	     
			    	     for( String id:answer.keySet() )
			    	     {
			    	    	 System.out.println( answer.get(id) );
			    	     }
			    	     
			    	}
			    	 catch( CustomException ex )
		    		 {
		    			 System.out.println(ex.getMessage());
		    		 }
			    	
			    	break;
			    	
			     default:
			    	 condition=false;	
			    		
			    	 
			
			}
			
		}
		
		
    } 

    private static void adminLogIn(Scanner scan, APILayer callAPI) 
    {
	    boolean condition=true;
	    
	    while( condition )
	    {
	    	int option=Utility.getInt("Press 1->Show All Users\nPress 2->Show Requests to approve\n"
	    			+ "Press 3->Show all question and answers\nPress 4->Delete questions\n"
	    			+ "\nPress others->exit", scan);
	    	
	    	
	    	switch( option )
	    	{
	    	
	    	   case 1:
	    		   
	    		   Map<String,User> allUser=callAPI.getAllUser();
	    		   
	    		   if( allUser==null || allUser.isEmpty() )
	    		   {
	    			   System.out.println("No Users");
	    		   }
	    		   else
	    		   {
	    			   System.out.println( "Name\tMailId\tPassword\tisAdmin/tapprovalStatus\tmobileNo\n");
	    			   for( String mailId:allUser.keySet() )
	    			   {
	    				   
	    				   System.out.println( allUser.get(mailId) );
	    				   
	    			   }
	    		   }
	    		   
	    		   break;
	    		   
	    	   case 2:
	    		   
	    		   while( true )
	    		   {
	    		      List<User> pendingUsers= callAPI.getPendingUsers();
	    		    
	    		      if( pendingUsers==null || pendingUsers.isEmpty() )
	    		      {
	    		    	  System.out.println("No Requests");
	    		    	  break;
	    		      }
	    		     
	    		    	  
	    		    	  for( int i=0;i<pendingUsers.size();i++ )
	    		    	  {
	    		    		  System.out.println("Press "+i+" "+pendingUsers.get(i));
	    		    	  }
	    		    	  
	    		    	  System.out.println("Press others->exit");
	    		    	  
	    		    	  int posi=Utility.getInt("", scan);
	    		    	  
	    		    	  if(  posi>=pendingUsers.size() )
	    		    	  {
	    		    		  break;
	    		    	  }
	    		    	
	    		    	  User user=pendingUsers.get(posi);
	    		    	  
	    		    	  
	    		    	      int choose=Utility.getInt("Press 1->Approved\nPress 2->Rejected", scan);
	    		    	  
	    		    	    boolean flag=true;
	    		    	    
	    		    	    while( flag )
	    		    	    {
	    		    	      switch( choose )
	    		    	      {
	    		    	         case 1:
	    		    	        	 
	    		    	        	 user.setApprovalStatus("Approved");
	    		    	        	 System.out.println("Approved Successfully");
	    		    	        	 flag=false;
	    		    	        	 break;
	    		    	        	 
	    		    	         case 2:
	    		    	        	 
	    		    	        	 user.setApprovalStatus("Rejected");
	    		    	        	 System.out.println("Rejected Successfully");
	    		    	        	 flag=false;
	    		    	             break;
	    		    	             
	    		    	         default:
	    		    	          System.out.println("Press 1 0r 2 only...!");  	
	    		    	      }
	    		    	  
	    		    	    }
	    		   }
	    		    
	    		   
	    		   break;
	    		   
	    	   case 3:
	    		   
	    		   printAllDoubts( callAPI );
	    		   
	    		   
	    		    
                   Map<String,Map<String,Doubt>> answers=callAPI.getAnswers();
	    		    
	    		    if( answers==null || answers.isEmpty() )
	    		    {
	    		    	System.out.println("No answers");
	    		    }
	    		    else
	    		    {
	    		    for( String mailId:answers.keySet() )
	    		    {
	    		    	Map<String,Doubt> answer= answers.get(mailId);
	    		    	
	    		    	for( String quesId:answer.keySet() )
	    		    	{
	    		    		System.out.println(answer.get(quesId));
	    		    	}
	    		    }
	    		    }
	    		   break;
	    		   
	    		   
	    	   case 4:
	    		   
	    		   
	    		   
	    		   boolean flag1=true;
	    		   
	    		   while( flag1 )
	    		   {
		    		   int choose=Utility.getInt("Press 1->Delete Question\nPress 2->Delete Answer\nPress others->exit", scan);

	    			   
	    		   switch( choose )
	    		   {
	    		     case 1:
	    		    	 
	    		    	 
	    		    	 
	    		    	 try
	    		    	 {
	    		    		 String facultyId= Utility.getString("Enter faculty mailId",scan);
	    		    		 callAPI.getUser(facultyId);
	    		    	 Map<String,Doubt> doubtsMap=callAPI.getDoubts(facultyId);
	    		    	 
	    		    	 
	    		    		 String questionId=null;
	    		    		while( true ) 
	    		    		{
	    		    		  questionId= Utility.getString( "Enter questionId" , scan)  ;
	    		    		 
	    		    		 Doubt doubt=doubtsMap.get(questionId);
	    		    		 
	    		    		 try 
	    		    		 {
								 Utility.ObjectCheck(doubt, "Doubt");
								 break;
							 }
	    		    		 catch (CustomException ex) 
	    		    		 {
	    		    			 System.out.println(ex.getMessage());
							 }
	    		    		 }
	    		    		
	    		    		doubtsMap.remove(questionId);
	    		    		
	    		    		System.out.println("Remove Successfully");
	    		    		 
	    		    	 }
	    		    	 catch( CustomException ex )
	    		    	 {
	    		    		 System.out.println(ex.getMessage());
	    		    	 }
	    		    	 
	    		    	 break;
	    		    	 
	    		     case 2:
	    		    	 
	    		    	 
                        try 
                        {
                        	String studentId=Utility.getString("Enter Student mailId",scan);
                        	 callAPI.getUser(studentId);
	    		    	 Map<String,Doubt> answersMap=callAPI.getAnswers(studentId);
	    		    	 
	    		    	 
	    		    	 
	    		    		 String questionId=null;
	    		    		while( true ) 
	    		    		{
	    		    		  questionId= Utility.getString( "Enter questionId" , scan)  ;
	    		    		 
	    		    		 Doubt doubt=answersMap.get(questionId);
	    		    		 
	    		    		 try 
	    		    		 {
								 Utility.ObjectCheck(doubt, "Answer");
								 break;
							 }
	    		    		 catch (CustomException ex) 
	    		    		 {
	    		    			 System.out.println(ex.getMessage());
							 }
	    		    		 }
	    		    		
	    		    		answersMap.remove(questionId);
	    		    		
	    		    		System.out.println("Remove Successfully");
	    		    		 
                        }
                        catch( CustomException ex )
                        {
                        	System.out.println(ex.getMessage());
                        }
                        
                        
	    		    	 
	    		    	 break;
	    		    	 
	    		    default:	 
	    		         flag1=false;
	    		    
	    		   }
	    		   }
	    		   
	    		   break;
	    		   
	    	   default:
	    		   
	    		   condition=false;
	    	
	    	
	    	}
	    	
	    }
    	
    	
    }

private static void printAllDoubts(APILayer callAPI) {
		// TODO Auto-generated method stub
	try
	   {
	   
	    Map<String,Map<String,Doubt>> doubts=callAPI.getDoubts();
	    
	   
	    for( String mailId:doubts.keySet() )
	    {
	    	Map<String,Doubt> question= doubts.get(mailId);
	    	
	    	for( String quesId:question.keySet() )
	    	{
	    		System.out.println(question.get(quesId));
	    	}
	    }
	   }
	   catch( CustomException ex )
	   {
		   System.out.println(ex.getMessage());
	   }
	}

private static boolean isStudent(Scanner scan) 
   {
	   
	   boolean condition=true;
	   
	   while( condition )
	   {
		   
		   int option=Utility.getInt( "Press 1->Faculty\nPress 2->Student" , scan);
		   
		   
		   switch( option )
		   {
		       case 2:
		    	   
		    	   return true;
		    	   
		       case 1:
		    	   
		    	   return false;
		       
		    	default:
		    		
		    		System.out.println("Press 1 and 2 only");
		   }
		   
	   }
	   
	      return false;
   }
}
