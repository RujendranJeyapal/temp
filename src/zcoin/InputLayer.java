package zcoin;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class InputLayer
{
     public static void main( String[] args )
     {
    	 
    	 Scanner scan=new Scanner( System.in );
    	 
    	 ZCoinLogic callLogic=new ZCoinLogic();
    	 
    	 
    	 boolean condition=true;
    	 
    	 
    	 while( condition )
    	 {
    	       int option=Utility.getInt("Press 1->SignIn\nPress 2->SignUp\nPress others->exit", scan);
    	 
    	       
    	       switch( option )
    	       {
    	          case 1: 
    	          
    	        	   String userMailId=Utility.getString( "Enter mailId" , scan);
    	        	   
    	        	   
    	        	         String userPassword=Utility.getString("Enter Password", scan);
    	        	   
    	        	   
    	        	         User user= callLogic.getUser(userMailId);
    	        	  
			             	try 
			             	{
					          Utility.objectCheck(user, "User");
					          callLogic.passwordCheck(user, userPassword);
				            }
			             	catch (CustomException ex) 
			             	{
					             System.out.println(ex.getMessage());
					             break;
				            }
				
			             	if( callLogic.isAdmin(userMailId) )
			             	{
			             		adminLogIn( scan,callLogic );
			             	}
			             	else
			             	{
			             		if( user.getApprovalStatus().equals("Approved"))
			             		{
			             		   userLogIn( scan,callLogic,user );
			             		}
			             		else
			             		{
			             			System.out.println("Admin doesnot approve");
			             		}
			             	}
			             	
    	        	  
    	        	  
    	        	  break;
    	        	  
    	          case 2:
    	        	  
    	        	  String name=Utility.getString("Enter name", scan);
    	        	  
    	        	  String mailID="";
    	        	  
    	        	  while( true )
    	        	  {
    	        	      mailID=Utility.getString("Enter mailId", scan);
    	        	   
    	        	   
    	        	     if( callLogic.getUser(mailID)!=null )
    	        	     {
    	        	    	 System.out.println("Email Id already exist");
    	        	     }
    	        	     else
    	        	     {
    	        	    	 break;
    	        	     }
    	        	      
    	        	     
    	        	  }
    	        	  long mobileNo=Utility.getLong("Enter mobile Number", scan);
    	        	  String h_id=Utility.getString("Enter human Id", scan);
    	        	  String password="";
    	        	  
    	        	  while( true )
    	        	  {
    	        	        password=Utility.getString("Enter password", scan);
    	        	       
    	        	       try 
    	        	       {
							    callLogic.isPasswordConditionCorrect(password, name, mailID, mobileNo);
							    break;
						   }
    	        	       catch (CustomException ex) 
    	        	       {
                             System.out.println( ex.getMessage() );						   
                           }
    	        	       
    	        	  }
    	        	  
    	        	  double irc=Utility.getDouble("Enter your initial RC amount", scan);
    	        	  
    	        	  
    	        	  callLogic.addUser(  new User(  name,  mailID,  mobileNo,  h_id,  password,  irc, false,"Waiting" ) );
    	        	  
    	        	  System.out.println("User added Successfully");
    	        	  
    	        	  break;
    	        	  
    	          default:	
    	        	  
    	        	  condition=false;
    	       
    	       }
    	 
    	 }
    	 
    	 scan.close();
    	 
     }

	  static void userLogIn(Scanner scan, ZCoinLogic callLogic, User user) 
	  {
		
		boolean condition=true;
		
		
		String zId=null;
		
		   while( true )
		   {
			   zId=Utility.getString("Enter your zId", scan);
		          try 
		          {
			         callLogic.accountCheck( zId,user.getMailId() );
			         break;
		          }
		          catch (CustomException ex) 
		          {
 			          System.out.println(ex.getMessage());
		          }
		   }
		   
		while( condition )
		{
			
			int option=Utility.getInt("Press 1->Show Account Details\nPress 2->Show RC Transaction History\n"
					+ "Press 3->Show ZC Transaction History\nPress 4->RC Transaction\nPress 5->ZC Transaction\nPress 6->Change Password\n", scan);
					
			
			
			switch( option )
			{
			
			        case 1:
			        	
			        	 System.out.println(  callLogic.getAccount(zId) );
			        	
			        	  break;
			
			        case 2:
			        	
			        	List<RCTransaction> rcTransList= callLogic.getRCTrans(zId);
			        	
			        	if( rcTransList==null || rcTransList.isEmpty())
			        	{
			        		System.out.println("No Transaction");
			        	}
			        	else
			        	{
			        		System.out.println("ZID\tDescription\tAmount\tClosing Rate\n");
			        		
			        		for( int i=0;i<rcTransList.size();i++ )
			        		{
			        			System.out.println( rcTransList.get(i) );
			        		}
			        	}
			        	
			        	break;
			        	
			        	
                    case 3:
			        	
			        	List<ZCTransaction> zcTransList= callLogic.getZCTrans(zId);
			        	
			        	if( zcTransList==null || zcTransList.isEmpty())
			        	{
			        		System.out.println("No Transaction");
			        	}
			        	else
			        	{
			        		System.out.println("ZID\tDescription\tAmount\tClosing Rate\n");
			        		
			        		for( int i=0;i<zcTransList.size();i++ )
			        		{
			        			System.out.println( zcTransList.get(i) );
			        		}
			        	}
			        	
			        	break;	
			        	
                    case 4:
                    	
                    	rcTransaction(zId,scan,callLogic);
                    	
			        	break;
			        	
                    case 5:
                    	
                    	zcTransaction(zId,scan,callLogic);
                    	break;
                    	
                    case 6:
                    	
                    	
                    	String pass="";
                    	
                    	 while( true )
       	        	     {
                    		 pass=Utility.getString("Enter password", scan);
       	        	       
       	        	       try 
       	        	       {
   							    callLogic.isPasswordConditionCorrect(pass, user.getName(),user.getMailId(), user.getMobileNo());
   							    break;
   						   }
       	        	       catch (CustomException ex) 
       	        	       {
                                System.out.println( ex.getMessage() );						   
                              }
       	        	       
       	        	    }
                    	 
                    	 callLogic.passwordChange(pass, user.getMailId());
                    	
                    	break;
			        	
			       default:
			    	   
			    	   condition=false;
			        	  
			}
			
			
			
		}
		
		
	  }

	 static void zcTransaction(String zId, Scanner scan, ZCoinLogic callLogic) 
	 {
		
		 boolean condition=true;
			
			
			while( condition )
			{
				
				    int option=Utility.getInt("Press 1->ZCoin Transfer\nPress 2->ZC to RC Convert\nPress others->exit\n", scan);
				
				    
				    
				    switch( option )
				    {
				    
				        case 1:
				    	
				        	
				        while( true )
				        {
						    try 
						    {
							       if(  callLogic.transferZCoin(zId, Utility.getString("Enter receiver Id", scan)  ,  Utility.getDouble("Enter ZC zmount", scan)) )
				    	           {
				    	    	      System.out.println("Transfer Successfully");
				    	    	      break;
				    	           }
						    }
						    catch (CustomException ex) 
						    {
                                     System.out.println(ex.getMessage());			
                            }
				        }
				               break;
				    	
				        case 2:
				    	
				        	while( true )
					        {
				        	
				        	      try 
						          {
				                        if( callLogic.ZCToRC(zId, Utility.getDouble( "Enter ZC Value" , scan) ))
				                        {
				            	             System.out.println("Convert Successfully");
				            	             break;
				                        }
				                  }
				                  catch (CustomException ex) 
							      {
	                                     System.out.println(ex.getMessage());			
	                              }
				        	 }
				    	
				        	break;
				    	
				        default:
				    	
				    	        condition=false;
				    	
				    }
			}
	}

	 static void rcTransaction(String zId, Scanner scan, ZCoinLogic callLogic) 
	 {
		boolean condition=true;
		
		
		while( condition )
		{
			
			    int option=Utility.getInt("Press 1->Deposit\nPres 2->Withdraw\nPress 3->RC to ZC Convert\nPress others->exit\n", scan);
			
			    
			    
			    switch( option )
			    {
			    
			    case 1:
			    	
			    	
			    	 if  (callLogic.depositeRC(Utility.getDouble("Enter amount", scan), zId) )
                     {
                    	 System.out.println("Deposit Successfully");
                     }
			    	
			    	break;
			    
			    	
			    case 2:
			    	
			       while( true )
			       {
			    	 try 
			    	 {
					     	if( callLogic.withDrawRC( Utility.getDouble("Enter amount", scan) , zId) )
						    {
							    System.out.println("Withdraw Successfully");
							    break;
						    }
					 } 
			    	 catch (CustomException ex) 
			    	 {
						      System.out.println(ex.getMessage());
					 }
			    	 
			       }
			    	
			    	break;
			    	
			    case 3:
			    	
				       while( true )
				       {
				    	 try 
				    	 {
						     	if( callLogic.rcToZCoinConvert( Utility.getDouble("Enter amount", scan) , zId) )
							    {
								    System.out.println("Convert Successfully");
								    break;
							    }
						 } 
				    	 catch (CustomException ex) 
				    	 {
							      System.out.println(ex.getMessage());
						 }
				    	 
				       }
				    	
				    	break;
			    	
			    default:
			    	
			    	condition=false;
			    	
			    }
		}
				
		
	}

	static void adminLogIn(Scanner scan, ZCoinLogic callLogic) 
	 {
		 boolean condition=true;
		 
		 while(  condition )
		 {
		           int option=Utility.getInt("Press 1->Requests List to Approve\nPress 2->All RC Transactions\nPress 3->All ZC Transactions\nPress 4->set RC to ZC Rate\nPress others->exit", scan);
		
	            	switch( option )
		            {
	            	
	            	        case 1:
	            	        
	            	        boolean flag1=true;
	            	        
	            	        while( flag1 )
	            	        {
	            	        	List<User> allRequests=callLogic.getAllRequests();
	            	        	
	            	        	if( allRequests.isEmpty() )
	            	        	{
	            	        		System.out.println("No Waiting Lists");
         	        			   flag1=false;

	            	        	}
	            	        	else
	            	        	{
	            	        		for( int i=0;i<allRequests.size();i++ )
	            	        		{
	            	        			System.out.println("Press "+i+"->"+allRequests.get(i));
	            	        		}
	            	        		
	            	        		System.out.println("Press others->exit");
	            	        		
	            	        		
	            	        		
	            	        		int choose=Utility.getInt("", scan);
	            	        		
	            	        		   if( choose>=allRequests.size( ))
	            	        		   {
	            	        			   flag1=false;
	            	        			   break;
	            	        		   }
	            	        		  
	            	        		
	            	        		
	            	        		User user=allRequests.get(choose);
	            	        		
	            	        		boolean flag=true;
	            	        		
	            	        		while( flag )
	            	        		{
	            	        		   int chooseStatus=Utility.getInt("Press 1->Approved\nPress 2->Rejected\n", scan);
	            	        		
	            	        		   switch( chooseStatus )
	            	        		   {
	            	        		       case 1:
	            	        		    	   user.setApprovalStatus("Approved");
	            	        		    	   Account account=new Account( user.getMailId(),  user.getIrc(), 0 ) ;
	            	        		    	   callLogic.addAccount( account );
	       	            	        		   System.out.println("Approved Successfully");
	       	            	        		   System.out.println("Your ZID is "+account.getZ_id());
	            	        		    	   flag=false;
	            	        		    	   break;
	            	        		    	   
	            	        		       case 2:
	            	        		    	   
	            	        		    	   user.setApprovalStatus("Rejected");
	       	            	        		   System.out.println("Rejected Successfully");
	            	        		    	   flag=false;
	            	        		    	   break;
	            	        		    	
	            	        		    	default:
	            	        		    		
	            	        		    		System.out.println("Press 1 and 2 only");
	            	        		    	
	            	        			   
	            	        		   }
	            	        	   }
	            	        	}
	            	        }
		                        break;
		                   
		                        
	            	        case 2:
	            	        	
	            	        	
	            	        	Map<String,List<RCTransaction>>  rcTransHistory=callLogic.getRcTransHistory();
	            	        	
	            	        	
	            	        	if( rcTransHistory.isEmpty() )
	            	        	{
	            	        		System.out.println("NO RC Transaction");
	            	        	}
	            	        	else
	            	        	{
	            	        	      for( String mailId:rcTransHistory.keySet() )
	            	        	      {
	            	        		                System.out.println(mailId+"\n\n\n");
	            	        		                
	            	        		               List<RCTransaction> rcTransList= rcTransHistory.get(mailId);
	            	        		               
	            	        		               for( int i=0;i<rcTransList.size();i++ )
	            	        		               {
	            	        		            	   System.out.println( rcTransList.get(i)) ;
	            	        		               }
	            	        	      }
	            	        	}
	            	        	break;
	            	        	
	            	        	
                            case 3:
	            	        	
	            	        	
	            	        	Map<String,List<ZCTransaction>>  zcTransHistory=callLogic.getZcTransHistory();
	            	        	
	            	        	
	            	        	if( zcTransHistory.isEmpty() )
	            	        	{
	            	        		System.out.println("NO RC Transaction");
	            	        	}
	            	        	else
	            	        	{
	            	        	      for( String mailId:zcTransHistory.keySet() )
	            	        	      {
	            	        		                System.out.println(mailId+"\n\n\n");
	            	        		                
	            	        		               List<ZCTransaction> zcTransList= zcTransHistory.get(mailId);
	            	        		               
	            	        		               for( int i=0;i<zcTransList.size();i++ )
	            	        		               {
	            	        		            	   System.out.println( zcTransList.get(i)) ;
	            	        		               }
	            	        	      }
	            	        	}
	            	        	break;	
		                        
                            case 4:
                            	
                            	callLogic.setRcToZCoinRate( Utility.getInt("Enter rate", scan) );
                            	
                            	break;
	            	        	
		                    default:
		                    	
		                    	condition=false;
		
		            }
		 }
		
	}
}
